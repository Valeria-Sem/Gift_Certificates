package com.epam.esm.dao.handler;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
    private final Logger log = Logger.getLogger(GiftCertificateDAOImpl.class);

//    private static HikariConfig config = new HikariConfig("db.properties");
//    private static HikariDataSource ds = new HikariDataSource(config);
    private static HikariDataSource ds;
    private static HikariConfig config = new HikariConfig();

    static {
        config.setDataSourceClassName(null);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/gift_certificates?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false");
        config.setUsername("root");
        config.setPassword("Lera");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public DBHandler() {
    }

    public static Connection getConn() throws SQLException{
        return ds.getConnection();
    }

    public static HikariDataSource getDs() {
        return ds;
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
