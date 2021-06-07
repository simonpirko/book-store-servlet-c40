package by.book.dao.postgres;

import by.book.config.ConnectionPool;
import by.book.dao.AddressDao;
import by.book.entity.Address;
import by.book.entity.Store;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.book.util.DbUtil.getLengthResultSet;

public class PgAddressDao implements AddressDao {
    @Override
    public List<Address> getAll() {
        try(Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM address;");
            return parseAddressList(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Address getById(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM address WHERE id=?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return parseAddress(resultSet);
        }catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public Address getByUser(User user) {
        return null;
    }

    @Override
    public Address getByStore(Store store) {
        return null;
    }

    @Override
    public void delete(long id) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM address WHERE id=?;");
            preparedStatement.setLong(1, 2);
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void save(Address address) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO address (street, home) VALUES (?,?);");
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getHome());
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void updateStreet(long id, String street) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE address SET street=? WHERE id=?;");
            preparedStatement.setString(1,street);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void updateHome(long id, int home) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE address SET home=? WHERE id=?;");
            preparedStatement.setInt(1,home);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public boolean contains(Address address) {
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM address WHERE street = ? AND home = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getHome());
            return getLengthResultSet(preparedStatement.executeQuery()) != 0;
        }catch (SQLException e){
            throw new DaoException();
        }
    }

    @Override
    public long saveReturnId(Address address) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO address (street, home) VALUES (?, ?);",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            if(preparedStatement.executeUpdate() != 1) throw  new DaoException("Create address");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id = 0;
            if(resultSet.next()){
                return id = resultSet.getLong("id");
            }else{
                return id;
            }
        }catch (SQLException e){
            throw new DaoException();
        }
    }

    @Override
    public long containsReturnId(Address address) {
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM address WHERE street = ? AND home = ?;");
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getHome());
            ResultSet resultSet = preparedStatement.executeQuery();
            long id = 0;
            if(resultSet.next()){
                id = resultSet.getLong("id");
            }
            return id;
        }catch (SQLException e){
            throw new DaoException();
        }
    }

    private List<Address> parseAddressList(ResultSet resultSet) throws SQLException {
        List<Address> addressList = new ArrayList<>();
        if(getLengthResultSet(resultSet) == 0)
            return addressList;
        while (resultSet.next()){
            addressList.add(parseAddress(resultSet));
        }
        return addressList;

    }

    private Address parseAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setHome(resultSet.getInt("home"));
        return address;
    }
}
