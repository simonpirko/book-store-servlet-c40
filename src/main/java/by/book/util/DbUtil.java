package by.book.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
    /**
     * For this method to work, it is necessary to pass the constant data in the statement parameters: ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
     */
    public static long getLengthResultSet(ResultSet resultSet) throws SQLException {
        long rowCount = 0;
        if (resultSet.last()) {
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        }
        return rowCount;
    }
}
