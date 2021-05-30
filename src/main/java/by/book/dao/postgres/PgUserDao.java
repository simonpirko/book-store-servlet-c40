package by.book.dao.postgres;

import by.book.config.ConnectionPool;
import by.book.dao.ConnectionDB;
import by.book.dao.UserDao;
import by.book.entity.Address;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static by.book.util.DbUtil.getLengthResultSet;

public class PgUserDao implements UserDao {
    private static final String INSERT_USER = "INSERT INTO \"user\"(" +
            "address_id, username, password, role, first_name, last_name, date_birth)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_FIRST_NAME = "UPDATE \"user\" SET first_name=? WHERE id=?;";
    private static final String UPDATE_LAST_NAME = "UPDATE \"user\" SET last_name=? WHERE id=?;";
    private static final String UPDATE_PASSWORD = "UPDATE \"user\" SET password=? WHERE id=?;";
    private static final String UPDATE_BIRTHDAY = "UPDATE \"user\" SET date_birth=? WHERE id=?;";
    private static final String UPDATE_ROLE = "UPDATE \"user\" SET role=? WHERE id=?;";

    private static final String SELECT_ROLE = "SELECT id FROM role WHERE LOWER(name) LIKE LOWER(?);";
    private static final String SELECT_ALL_USERS = "SELECT " +
            "u.id, u.username, u.password, u.first_name, " +
            "u.last_name, u.date_birth,r.name, " +
            "ad.id, ad.street, ad.home " +
            "FROM \"user\" AS u " +
            "INNER JOIN role AS r ON u.role = r.id " +
            "INNER JOIN address AS ad ON ad.id = u.address_id " +
            "ORDER BY u.id;";

    private static final String SELECT_USER_BY_ID = "SELECT " +
            "u.id, u.username, u.password, u.first_name, " +
            "u.last_name, u.date_birth,r.name, " +
            "ad.id, ad.street, ad.home " +
            "FROM \"user\" AS u " +
            "INNER JOIN role AS r ON u.role = r.id " +
            "INNER JOIN address AS ad ON ad.id = u.address_id " +
            "WHERE u.id=?;";

    private static final String SELECT_USER_BY_USERNAME = "SELECT " +
            "u.id, u.username, u.password, u.first_name, " +
            "u.last_name, u.date_birth,r.name, " +
            "ad.id, ad.street, ad.home " +
            "FROM \"user\" AS u " +
            "INNER JOIN role AS r ON u.role = r.id " +
            "INNER JOIN address AS ad ON ad.id = u.address_id " +
            "WHERE u.username LIKE ?;";

    private static final String DELETE_BY_USERNAME = "DELETE FROM public.\"user\" WHERE username LIKE ?;";
    private static final String DELETE_BY_ID = "DELETE FROM public.\"user\" WHERE id=?;";

    private static final String SELECT_CONTAINS_USERNAME = "SELECT username FROM \"user\" WHERE username LIKE ?;";
    private static final String SELECT_CONTAINS_PASSWORD = "SELECT password FROM \"user\" WHERE password LIKE ?;";

    @Override
    public void save(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            //TODO change address id
            statement.setLong(1, 1);
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setLong(4, getRole(user.getRole(), connection));
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            java.sql.Date date = (user.getBirthDate() != null ? Date.valueOf(user.getBirthDate()) : null);
            statement.setDate(7, date);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateFirstName(long id, String name) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FIRST_NAME)) {

            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateLastName(long id, String name) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LAST_NAME)) {

            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updatePassword(long id, String password) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {

            statement.setString(1, password);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateBirthday(long id, LocalDate localDate) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BIRTHDAY)) {

            statement.setDate(1, Date.valueOf(localDate));
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void updateRole(long id, Role role) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE)) {

            long roleId = getRole(role, connection);
            statement.setLong(1, roleId);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery();
            users = parseBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
        return users;
    }

    @Override
    public User getById(long id) throws DaoException {
        User user;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            user = parseUser(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }

        return user;
    }

    @Override
    public User getByUserName(String userName) {
        User user;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            user = parseUser(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }

        return user;
    }

    @Override
    public void delete(String userName) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_USERNAME)) {

            statement.setString(1, userName);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean containsByName(String userName) {
       return containsBy(userName, SELECT_CONTAINS_USERNAME);
    }

    @Override
    public boolean containsByPassword(String password) {
        return containsBy(password, SELECT_CONTAINS_PASSWORD);
    }

    private void getUserData(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setFirstName(resultSet.getString(4));
        user.setLastName(resultSet.getString(5));

        Date date = resultSet.getDate(6);
        LocalDate lDate = (date != null ? date.toLocalDate() : null);
        user.setBirthDate(lDate);
    }

    private void setRole(User user, ResultSet resultSet) throws SQLException {
        Role role = Role.valueOf(resultSet.getString(7).toUpperCase(Locale.ROOT));
        user.setRole(role);
    }

    private void getAddress(User user, ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getLong(8));
        address.setStreet(resultSet.getString(9));
        address.setHome(resultSet.getInt(10));
        user.setAddress(address);
    }

    private long getRole(Role role, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ROLE);

        statement.setString(1, role.name());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong(1);
    }

    private Boolean containsBy(String paramName, String query) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setString(1, paramName);
            ResultSet resultSet = statement.executeQuery();
            return getLengthResultSet(resultSet) != 0;

        } catch (SQLException e) {
           throw new DaoException();
        }
    }

    private List<User> parseBookList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        if (getLengthResultSet(resultSet) == 0) return users;

        while (resultSet.next()) {
            User user = new User();
            getUserData(user, resultSet);
            setRole(user, resultSet);
            getAddress(user, resultSet);
            users.add(user);
        }

        return users;
    }

    private User parseUser(ResultSet resultSet) throws SQLException {
        List<User> userList = parseBookList(resultSet);
        if (userList.size() == 0) return null;
        return userList.get(0);
    }
}
