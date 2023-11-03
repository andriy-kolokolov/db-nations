package experis.lessons.jdbc;

import java.sql.*;

public class DB {
    private static final String DB = "db_nations";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void searchCountries(String str) {
        // initialize the base SQL query
        String sql = """
            SELECT
                countries.name AS country_name,
                countries.country_id AS country_id,
                regions.name AS region_name,
                continents.name AS continent_name
            FROM
                countries
                    INNER JOIN regions ON countries.region_id = regions.region_id
                    INNER JOIN continents ON regions.continent_id = continents.continent_id
            """;

        // add the WHERE clause if the search string is not null or empty
        if (str != null && !str.trim().isEmpty()) {
            sql += " WHERE countries.name LIKE ? ";
        }

        sql += " ORDER BY countries.name;";

        try (Connection conn = getConnection()) {

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            // Set the search string parameter if it's not null or empty
            if (str != null && !str.trim().isEmpty()) {
                preparedStmt.setString(1, "%" + str + "%");
            }

            ResultSetPrinter.printResultSet(preparedStmt.executeQuery());


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
