package com.nvhien.db;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mbh";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            log.warn("Connect DB successfully!");
        } catch (SQLException sqlException) {
            log.warn("Connect DB failure. {}", sqlException.getMessage());
        }
        return connection;
    }
}
