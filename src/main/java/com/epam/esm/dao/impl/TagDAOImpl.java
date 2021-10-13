package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.DBHandler;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDAOImpl implements TagDAO {
    private final Logger LOGGER = Logger.getLogger(TagDAOImpl.class);

    private final String INSERT_QUERY = "insert into tag(name) values(?)";
    private final String DELETE_QUERY = "delete from tag where id = ?";

    @Override
    public void save(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, name);
            ps.executeUpdate();

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

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(DELETE_QUERY);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.close();
            ps.close();

        } catch (SQLException e){
            LOGGER.error("some problems with deleting tag");
            throw new DAOException(e);

        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }
}
