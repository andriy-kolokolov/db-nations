package experis.lessons.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetPrinter {
    public static void printResultSet(ResultSet rs) {
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();

            int columnsNumber = metaData.getColumnCount();

            // print column names (header)
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(metaData.getColumnName(i));
            }
            System.out.println();

            // print row data
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(" | ");
                    System.out.print(rs.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
