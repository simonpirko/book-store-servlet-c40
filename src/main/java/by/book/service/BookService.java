package by.book.service;

import by.book.dao.AuthorDao;
import by.book.dao.BookDao;
import by.book.dao.inmemory.InMemoryAuthorDao;
import by.book.dao.postgres.PgBookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class BookService {

    private final BookDao bookDao = new PgBookDao();
    private final AuthorDao authorDao = new InMemoryAuthorDao();

    public List<Book> getBook(String genre, String author, int yearMin, int yearMax) {

        List<Book> resBookList = new ArrayList<>();

        //Если ни один фильтр не установлен просто отдаем все книги
        if (genre == null && author == null && yearMin == 0 && yearMax == 0) return bookDao.getAll();

        // Если нет, идем по циклу и все проверяем
        for (Book item : bookDao.getAll()) {
            boolean isOk = false;

            // Проверка по жанру
            if (genre != null) {
                if (genre.equals(item.getGenre())) {
                    isOk = true;
                } else {
                    // если по жанру не подошла, значит нет смысла проверять по автору и дате издания и можно идти на следущую итерацию
                    continue;
                }
            }

            // Проверка по автору
            if (author != null && item.getAuthors() != null) {
                // идем циклом по всем авторам текущей книги
                boolean isAdd = false;

                for (Author authorItem : item.getAuthors()) {
                    String name = String.format("%s %s", authorItem.getFirstName(), authorItem.getLastName());
                    if (author.equals(name)) {
                        // если автор совпадает значит можно добавлять в лист
                        isAdd = true;
                        break;
                    }
                }

                if (isAdd) {
                    // если хоть один автор совпал, значит по автору книга подошла и можно добавлять в список
                    isOk = true;
                } else {
                    // если по автору не подошла, значит нет смысла проверять по дате издания и можно идти на следущую итерацию
                    continue;
                }
            }

            // Проверка по дате издания
            if (yearMin != 0) {
                if (item.getPublicationDate().getYear() >= yearMin) {
                    isOk = true;
                } else {
                    continue;
                }

            }
            if (yearMax != 0) {
                if (item.getPublicationDate().getYear() <= yearMax) {
                    isOk = true;
                } else {
                    continue;
                }
            }

            if (isOk) resBookList.add(item);
        }

        return resBookList;
    }

    public List<Set<String>> getFilter() {
        List<Set<String>> filter = new ArrayList<>();

        filter.add(bookDao.getGenre());

        Set<String> authorSet = new HashSet<>();
        for (Author item : authorDao.getAll()) {
            String name = String.format("%s %s", item.getFirstName(), item.getLastName());
            authorSet.add(name);
        }

        filter.add(authorSet);

        return filter;
    }

    public Book getBook(int id) throws NotFoundException {
        Book bookById = bookDao.getBookById(id);
        if (bookById == null) throw new NotFoundException();
        return bookById;
    }

    public List<Book> getRandomBooks() throws NotFoundException {
        List<Book> bookRandList = bookDao.getAll();
        Collections.shuffle(bookRandList);
        return (List<Book>) bookRandList.stream().limit(10).collect(Collectors.toList());
    }
}
