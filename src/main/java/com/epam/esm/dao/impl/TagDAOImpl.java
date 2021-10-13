package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.handler.DBHandler;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDAOImpl implements TagDAO {
    private final Logger LOGGER = Logger.getLogger(TagDAOImpl.class);

    private final String INSERT_QUERY = "insert into tag (name) values (?)";
    @Override
    public void save(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(INSERT_QUERY);

            ps.executeQuery();

            connection.close();
            ps.close();

        } catch (SQLException e){
            LOGGER.error("some problems with save tag");
            throw new DAOException(e);
        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }
}
