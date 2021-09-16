package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mybase";

    private static final String USER = "root";
    private static final String PASSWORD = "A04r01t20e00m";

    public Connection getConnection() {
        Connection connection = null;

        try {
            // Registering JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Creating database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Соединение не активно");
        }
        return connection;
    }
}
