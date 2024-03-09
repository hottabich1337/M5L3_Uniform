package org.example.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static HikariDataSource ds;

    public ConnectionPool() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/MrSQL");
        config.setUsername("postgres");
        config.setPassword("123");
        ds = new HikariDataSource(config);
    }




    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //private HikariCPDataSource(){}
}
