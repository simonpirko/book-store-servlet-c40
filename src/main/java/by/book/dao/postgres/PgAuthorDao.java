package by.book.dao.postgres;

import by.book.config.ConnectionPool;
import by.book.dao.AuthorDao;
import by.book.entity.Author;
import by.book.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.book.util.DbUtil.getLengthResultSet;

public class PgAuthorDao implements AuthorDao {
    @Override
    public void save(Author author) {

        try (Connection connection = ConnectionPool.getConnection()) {
            String sql = "INSERT INTO author (first_name, last_name, description) VALUES (?, ?, ?);";
            PreparedStatement createAuthorPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            createAuthorPreparedStatement.setString(1, author.getFirstName());
            createAuthorPreparedStatement.setString(2, author.getLastName());
            createAuthorPreparedStatement.setString(3, author.getDescription());

            createAuthorPreparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();


        try (Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("SELECT *  FROM author");
            while (resultSet.next()) {
                authors.add(parseAuthor(resultSet));
            }

        } catch (SQLException e) {
            throw new DaoException();
        }

        return authors;

    }


    @Override
    public void deleteById(long id) {

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM author WHERE id=?;");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException();
        }

    }

    @Override
    public Author getById(long id) {
        Author author = new Author();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            author = parseAuthor(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void updateFirstName(long id, String name) {

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE author SET first_name=? WHERE id=?;");
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLastName(long id, String name) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE author SET last_name = ? WHERE id=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateDescription(long id, String description) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE author SET description = ? WHERE id=?;");
            preparedStatement.setString(1, description);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(String fName, String lName) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT FROM author WHERE first_name=? AND last_name=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getLengthResultSet(preparedStatement.executeQuery()) != 0;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean containsId(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM author WHERE id=?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getLengthResultSet(resultSet) != 0;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    private Author parseAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("description"));
    }
}




