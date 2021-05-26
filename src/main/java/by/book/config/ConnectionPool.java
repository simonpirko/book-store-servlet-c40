package by.book.config;

import by.book.dao.ConnectionDB;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final static BasicDataSource dataSource;

    static {
        if(Boolean.parseBoolean(Config.getProperty(Config.DB_REST))) {
            try(Connection connection = ConnectionDB.getConnection()) {
                new CreatingTableDB(connection).run();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(Config.getProperty(Config.DB_URL));
        dataSource.setUsername(Config.getProperty(Config.DB_LOGIN));
        dataSource.setPassword(Config.getProperty(Config.DB_PASSWORD));

        // Начальное количество соедеднений
        dataSource.setInitialSize(2);

        // Максимум коннектов которые будут в пуле
        dataSource.setMaxTotal(20);

        // Максимум соедениний которые могут простаивать
        dataSource.setMaxIdle(5);
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
