package by.book.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_REST = "db.reset";

    private static Properties properties = new Properties();

    public static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream stream = Config.class.getClassLoader()
                    .getResourceAsStream("dao.properties")) {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(name);
    }

}
