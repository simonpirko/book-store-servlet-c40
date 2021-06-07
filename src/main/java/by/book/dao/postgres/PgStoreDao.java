package by.book.dao.postgres;

import by.book.config.ConnectionPool;
import by.book.dao.AddressDao;
import by.book.dao.StoreDao;
import by.book.entity.Address;
import by.book.entity.Book;
import by.book.entity.Store;
import by.book.exception.DaoException;

import java.util.List;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static by.book.util.DbUtil.getLengthResultSet;

public class PgStoreDao implements StoreDao {
    private AddressDao addressDao = new PgAddressDao();

    @Override
    public List<Store> getAll() {
        try (Connection connection = ConnectionPool.getConnection()){
           Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ResultSet resultSet = statement.executeQuery("SELECT * FROM public.store t LIMIT 501");
           return parseStoreList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Arrays.asList(new Store(1,new Address(),"Hello",new ArrayList<>()));
    }

    @Override
    public Store getByAddress(Address address) throws DaoException {
        return null;
    }

    @Override
    public Store getById(long id) throws DaoException {
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM store WHERE id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println(parseStore(resultSet));
                return parseStore(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getByName(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(long id, String name) throws DaoException {
        try (Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE store SET name=? WHERE id=?;");
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(long id, Address address) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE store SET address_id=? WHERE id=?;");
            preparedStatement.setLong(2, id);
            if(addressDao.contains(address)){
                preparedStatement.setLong(1, addressDao.containsReturnId(address));
            }else{
                preparedStatement.setLong(1,addressDao.saveReturnId(address));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.store WHERE id=?;");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void save(Store store) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.store (name,address_id) VALUES (?,?);");
            statement.setString(1,store.getName());
            if(addressDao.contains(store.getAddress())){
                statement.setLong(2, addressDao.containsReturnId(store.getAddress()));
            }else{
                statement.setLong(2,addressDao.saveReturnId(store.getAddress()));
            }
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean contains(Address address) {
        try (Connection connection = ConnectionPool.getConnection()) {
            return addressDao.contains(address);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Store parseStore(ResultSet resultSet) throws SQLException {
        List<Store> storeList = parseStoreList(resultSet);
        if(storeList.size() != 0){
            return storeList.get(0);
        }
        return null;
    }

    private List<Store> parseStoreList(ResultSet resultSet) throws SQLException {
        List<Store> storeList = new ArrayList<>();
        if(getLengthResultSet(resultSet) == 0) return storeList;
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Store store = new Store();
            store.setId(id);
            store.setName(resultSet.getString("name"));
            store.setAddress(addressDao.getById(resultSet.getLong("address_id")));
            store.setOrders(new ArrayList<>());
            storeList.add(store);
        }
        return storeList;
    }
}
