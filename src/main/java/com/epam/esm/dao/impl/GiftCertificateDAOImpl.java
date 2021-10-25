package com.epam.esm.dao.impl;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.bean.TagBean;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.handler.DBHandler;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateDAOImpl.class);

    private final String INSERT_QUERY = "insert into gift_certificate(name, description, price, duration, create_date, last_update_date)" +
            " values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "delete from gift_certificate where id = ?";
    private final String SELECT_ALL_QUERY = "select * from gift_certificate";

    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String PRICE = "price";
    private final String DURATION = "duration";
    private final String CREATE_DATE = "create_date";
    private final String LAST_UPDATE_DATE = "last_update_date";



    @Override
    public void save(GiftCertificateBean giftCertificate) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setDouble(3, giftCertificate.getPrice());
            ps.setInt(4, giftCertificate.getDuration());
            ps.setDate(5, giftCertificate.getCreateDate());
            ps.setDate(6, giftCertificate.getLastUpdateDate());

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

    @Override
    public List<GiftCertificateBean> getAllCertificates() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        List<GiftCertificateBean> certificates = new ArrayList<>();

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = ps.executeQuery();

            while (rs.next()){
                GiftCertificateBean certificate = new GiftCertificateBean();

                certificate.setId(Integer.parseInt(rs.getString(ID)));
                certificate.setName(rs.getString(NAME));
                certificate.setDescription(rs.getString(DESCRIPTION));
                certificate.setPrice(Double.parseDouble(rs.getString(PRICE)));
                certificate.setDuration(Integer.parseInt(rs.getString(DURATION)));
                certificate.setCreateDate(Date.valueOf(rs.getString(CREATE_DATE)));
                certificate.setLastUpdateDate(Date.valueOf(rs.getString(LAST_UPDATE_DATE)));

                certificates.add(certificate);
            }

            connection.close();
            ps.close();

            return certificates;

        } catch (SQLException e){
            LOGGER.error("some problems with extracting tags");
            throw new DAOException(e);
        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public void updateCertificate(HashMap<String, String> updateParams) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            for(String key: updateParams.keySet()) {
                for(String value: updateParams.values()) {
                    ps = connection.prepareStatement("update gift_certificate set" + key + " = " + value + " where id =" +
                            updateParams.get("id"));

                    ps.executeUpdate();
                    ps.close();
                }
            }
            connection.close();

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
