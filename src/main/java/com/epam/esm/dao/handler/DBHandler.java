package com.epam.esm.dao.handler;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
    private static HikariDataSource ds;
    private static HikariDataSource config;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/gift_certificates?serverTimezone=Europe/Moscow&useSSL=false");
        config.setUsername("root");
        config.setPassword("Lera");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
    }

    public static Connection getConn() throws SQLException{
        return ds.getConnection();
    }

    public static void closeConnection(Connection conn, Statement st, ResultSet rs){
        try {
            conn.close();
        } catch (SQLException e) {      //ToDo Handle Exception
            //logger.log(Level.ERROR, "Connection isn't return to the pool.");
        }

        try {
            rs.close();
        } catch (SQLException e) {      //ToDo Handle Exception
        }

        try {
            st.close();
        } catch (SQLException e) {      //ToDo Handle Exceptions

        }
    }

    public static void closeConnection(Connection conn, Statement st){
        try {
            conn.close();
        } catch (SQLException e) {      //ToDo Handle Exception
            //logger.log(Level.ERROR, "Connection isn't return to the pool.");
        }

        try {
            st.close();
        } catch (SQLException e) {      //ToDo Handle Exceptions

        }
    }

    public static void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {      //ToDo Handle Exception
            //logger.log(Level.ERROR, "Connection isn't return to the pool.");
        }
    }
}
