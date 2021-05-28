package by.book.dao.postgres;

import by.book.config.ConnectionPool;
import by.book.dao.BookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.entity.Comment;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static by.book.util.DbUtil.getLengthResultSet;

public class PgBookDao implements BookDao {

    @Override
    public List<Book> getAll() {
        try(Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id;");
            return parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Book getBookById(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE b.id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBook(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Book getBookByName(String name) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE b.name=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBook(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<Book> getBookByData(LocalDate date) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE b.published_date=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<Book> getBookByGenre(String genre) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE g.name=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<Book> getBookByGenreAndData(String genre, LocalDate date) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE g.name=? AND b.published_date=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, genre);
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<Book> getBookByAuthor(Author author) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.*, g.name AS genre, a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.description AS a_description, c.id AS c_id, c.text AS c_text, u.id AS u_id, u.last_name AS u_last_name, u.first_name AS u_first_name, u.username AS u_username FROM book b LEFT OUTER JOIN genre g ON b.genre_id=g.id LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id LEFT OUTER JOIN comment c ON b.id=c.book_id LEFT OUTER JOIN \"user\" u ON c.user_id=u.id WHERE a.id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, author.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void save(Book book) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);

            // находим или создаем жанр
            long idGenre = findOrCreateGenreTransaction(connection, book.getGenre());

            // создание книги
            PreparedStatement createBookPreparedStatement = connection.prepareStatement("INSERT INTO book (name, price, description, genre_id, published_date) VALUES (?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            createBookPreparedStatement.setString(1, book.getName());
            createBookPreparedStatement.setInt(2, book.getPrice());
            createBookPreparedStatement.setString(3, book.getDescription());
            createBookPreparedStatement.setLong(4, idGenre);
            createBookPreparedStatement.setDate(5, Date.valueOf(book.getPublicationDate()));
            if(createBookPreparedStatement.executeUpdate() != 1) throw  new DaoException("Create book");
            ResultSet resultSetCreateBook = createBookPreparedStatement.getGeneratedKeys();
            long bookId;
            if(resultSetCreateBook.next()) {
                bookId = resultSetCreateBook.getLong("id");
            } else {
                throw new DaoException("Create book");
            }

            // Привязываем авторов к книге
            for (Author author : book.getAuthors()) {
                PreparedStatement addAuthorsBookPrepareStatement = connection.prepareStatement("INSERT INTO book_author VALUES (?, ?)");
                addAuthorsBookPrepareStatement.setLong(1, bookId);
                addAuthorsBookPrepareStatement.setLong(2, author.getId());
                addAuthorsBookPrepareStatement.execute();
            }

            connection.commit();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id=?;");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateName(long id, String name) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name=? WHERE id=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateDescription(long id, String description) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET description=? WHERE id=?;");
            preparedStatement.setString(1, description);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateAuthors(long id, List<Author> authors) {
        System.out.println("RUN");
        try(Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);

            // получаем всех авторов данной книги
            PreparedStatement findOldAuthorsStatement = connection.prepareStatement("SELECT author_id FROM book_author WHERE book_id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            findOldAuthorsStatement.setLong(1, id);
            ResultSet findResultSet = findOldAuthorsStatement.executeQuery();

            // id текущих авторов в базе
            // и авторы которых нужно будет удалить
            Set<Long> setIdDelAuthors = new HashSet<>();

            // авторы которых нужн добавить
            Set<Long> setIdAddAuthors = new HashSet<>();

            if(getLengthResultSet(findResultSet) != 0) {
                while (findResultSet.next()) {
                    setIdDelAuthors.add(findResultSet.getLong("author_id"));
                }
            }

            for(Author author : authors) {
                if(setIdDelAuthors.contains(author.getId())) {
                    setIdDelAuthors.remove(author.getId());
                } else {
                    setIdAddAuthors.add(author.getId());
                }
            }
            System.out.println(setIdDelAuthors);
            System.out.println(setIdAddAuthors);
            // удаляем авторов
            for (long idAuthorDel : setIdDelAuthors) {
                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM book_author WHERE book_id=? AND author_id=?");
                deleteStatement.setLong(1, id);
                deleteStatement.setLong(2, idAuthorDel);
                deleteStatement.execute();
            }

            // добавляем авторов
            for (long idAuthorAdd : setIdAddAuthors) {
                PreparedStatement deleteStatement = connection.prepareStatement("INSERT INTO book_author VALUES (?, ?)");
                deleteStatement.setLong(1, id);
                deleteStatement.setLong(2, idAuthorAdd);
                deleteStatement.execute();
            }

            connection.commit();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updatePrice(long id, int price) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET price=? WHERE id=?;");
            preparedStatement.setInt(1, price);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateGenre(long id, String genre) {
        try(Connection connection = ConnectionPool.getConnection()) {
            // Отключаем автофиксацию
            connection.setAutoCommit(false);

            // находим или создаем жанр
            long idGenre = findOrCreateGenreTransaction(connection, genre);

            // Теперь обновляем книгу
            PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE book SET genre_id=? WHERE id=?;");
            updateBookStatement.setLong(1, idGenre);
            updateBookStatement.setLong(2, id);
            if(updateBookStatement.executeUpdate() != 1) {
                throw new DaoException("Update genre book");
            }

            // завершаем транзакцию
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updatePublicationDate(long id, LocalDate date) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET published_date=? WHERE id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean contains(String name, List<Author> author) {

        List<Long> list = new ArrayList<>();

        for(Author item : author) {
            list.add(item.getId());
        }

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.id FROM book b LEFT OUTER JOIN book_author ba ON b.id=ba.book_id LEFT OUTER JOIN author a ON ba.author_id=a.id WHERE b.name=? AND a.id=ALL(ARRAY ?);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, name);
            preparedStatement.setArray(2, (Array) list);
            return getLengthResultSet(preparedStatement.executeQuery()) != 0;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Set<String> getGenre() {
        try(Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT name FROM genre;");

            Set<String> set = new HashSet<>();

            if(getLengthResultSet(resultSet) == 0) return set;

            while (resultSet.next()) {
                set.add(resultSet.getString("name"));
            }

            return set;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean containsId(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM book WHERE id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getLengthResultSet(resultSet) != 0;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    private long findOrCreateGenreTransaction(Connection connection, String name) throws SQLException {
        // Проверяем наличие данного жанра в базе
        PreparedStatement findGenreStatement = connection.prepareStatement("SELECT id FROM genre WHERE name=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        findGenreStatement.setString(1, name);
        ResultSet resultSet = findGenreStatement.executeQuery();

        long idGenre;
        if(resultSet.next()) {
            // если жанр существует в базе выбераем его
            idGenre = resultSet.getLong("id");
        } else {
            // если не находим жанр создаем его
            PreparedStatement insertGenreStatement = connection.prepareStatement("INSERT INTO genre (name) VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
            insertGenreStatement.setString(1, name);
            if(insertGenreStatement.executeUpdate() != 1) throw new DaoException("Create new genre");
            ResultSet resultInsertGenre = insertGenreStatement.getGeneratedKeys();

            if(resultInsertGenre.next()) {
                idGenre = resultInsertGenre.getLong("id");
            } else {
                throw new DaoException("Create new genre");
            }
        }
        return idGenre;
    }

    private List<Book> parseBookList(ResultSet resultSet) throws SQLException {

        if(getLengthResultSet(resultSet) == 0) return new ArrayList<>();

        Map<Long, Book> map = new HashMap<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            Book book;
            if(map.get(id) == null) {
                book = new Book();

                book.setId(id);
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getInt("price"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setPublicationDate(Date.valueOf(String.valueOf(resultSet.getDate("published_date"))).toLocalDate());
                book.setComments(new ArrayList<>());
                book.setAuthors(new ArrayList<>());

                map.put(id, book);
            } else {
                book = map.get(id);
            }

            if(resultSet.getLong("a_id") != 0 && !book.getAuthors().contains(new Author(resultSet.getLong("a_id")))) {
                book.getAuthors().add(new Author(resultSet.getLong("a_id"), resultSet.getString("a_first_name"), resultSet.getString("a_last_name"), resultSet.getString("a_description")));
            }
            if(resultSet.getLong("c_id") != 0 && !book.getComments().contains(new Comment(resultSet.getLong("c_id")))) {
                User user = new User();
                user.setId(resultSet.getLong("u_id"));
                user.setUsername(resultSet.getString("u_username"));
                user.setLastName(resultSet.getString("u_last_name"));
                user.setFirstName(resultSet.getString("u_first_name"));
                book.getComments().add(new Comment(resultSet.getLong("c_id"), user, resultSet.getString("c_text")));
            }
        }
        return new ArrayList<>(map.values());
    }

    private Book parseBook(ResultSet resultSet) throws SQLException {
        List<Book> bookList = parseBookList(resultSet);
        if(bookList.size() == 0) return null;
        return bookList.get(0);
    }
}
