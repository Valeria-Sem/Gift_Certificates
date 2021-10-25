package com.epam.esm.dao.impl;

import com.epam.esm.bean.TagBean;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.handler.DBHandler;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private final Logger LOGGER = Logger.getLogger(TagDAOImpl.class);

    private final String INSERT_QUERY = "insert into tag(name) values(?)";
    private final String DELETE_QUERY = "delete from tag where id = ?";
    private final String SELECT_ALL_QUERY = "select * from tag";

    private final String ID ="id";
    private final String NAME ="name";


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
            LOGGER.error("some problems with save tag. Perhaps a tag with the same name already exists in the database");
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

    @Override
    public List<TagBean> getAllTags() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        List<TagBean> tags = new ArrayList<>();

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = ps.executeQuery();

            while (rs.next()){
                TagBean tag = new TagBean();

                tag.setId(Integer.parseInt(rs.getString(ID)));
                tag.setName(rs.getString(NAME));

                tags.add(tag);
            }

            connection.close();
            ps.close();

            return tags;

        } catch (SQLException e){
            LOGGER.error("some problems with extracting tags");
            throw new DAOException(e);
        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }
}
