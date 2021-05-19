package by.book.service;

import by.book.dao.AuthorDao;
import by.book.dao.BookDao;
import by.book.dao.inmemory.InMemoryAuthorDao;
import by.book.dao.inmemory.InMemoryBookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookService {

    private final BookDao bookDao = new InMemoryBookDao();
    private final AuthorDao authorDao = new InMemoryAuthorDao();

    public List<Book> getBook(String genre, String author, int yearMin, int yearMax) {

        List<Book> resBookList = new ArrayList<>();

        //Если ни один фильтр не установлен просто отдаем все книги
        if(genre == null && author == null && yearMin == 0 && yearMax == 0) return bookDao.getAll();

        // Если нет, идем по циклу и все проверяем
        for(Book item : bookDao.getAll()) {

            // Проверка по жанру
            if(genre != null && genre.equals(item.getGenre())) {
                resBookList.add(item);
                continue;
            }

            // Проверка по автору
            if(author != null && item.getAuthors() != null) {
                // идем циклом по всем авторам текущей книги
                boolean isAdd = false;

                for (Author authorItem : item.getAuthors()) {
                    String name = String.format("%s %s", authorItem.getFirstName(), authorItem.getLastName());
                    if (author.equals(name)) {
                        // если авторы совпадают добвляем в лист и выходим из цикла
                        resBookList.add(item);
                        isAdd = true;
                        break;
                    }
                }
                // если книга добавлена в список, нет смылса проверять дальше, поэтому идем на следщую итерацию
                if(isAdd) continue;
            }

            // Проверка по дате издания
            if(yearMin != 0 && yearMax == 0 && item.getPublicationDate().getYear() >= yearMin) {
                resBookList.add(item);
                continue;
            }
            if(yearMax != 0 && yearMin == 0 && item.getPublicationDate().getYear() <= yearMax) {
                resBookList.add(item);
                continue;
            }
            if(yearMax != 0 && yearMin != 0 && item.getPublicationDate().getYear() <= yearMax && item.getPublicationDate().getYear() >= yearMin) {
                resBookList.add(item);
            }
        }

        return resBookList;
    }

    public List<Set<String>> getFilter() {
        List<Set<String>> filter = new ArrayList<>();

        filter.add(bookDao.getGenre());

        Set<String> authorSet = new HashSet<>();
        for(Author item : authorDao.getAll()) {
            String name = String.format("%s %s", item.getFirstName(), item.getLastName());
            authorSet.add(name);
        }

        filter.add(authorSet);

        return filter;
    }

    public Book getBook(int id) throws NotFoundException {
        Book bookById = bookDao.getBookById(id);
        if(bookById == null) throw new NotFoundException();
        return bookById;
    }
}
