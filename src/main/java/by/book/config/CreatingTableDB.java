package by.book.config;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;

public class CreatingTableDB {
    private Connection connection;

    public CreatingTableDB(Connection connection) {
        this.connection = connection;
    }

    public void run() throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(connection);
        String path = getClass().getClassLoader().getResource(".").getPath();
        Reader reader = new BufferedReader(new FileReader(path + "../../../../table.sql"));
        sr.runScript(reader);
    }
}
