package com.epam.esm.dao.impl;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftTagDAO;
import com.epam.esm.dao.handler.DBHandler;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiftTagDAOImpl implements GiftTagDAO {
    private final Logger LOGGER = Logger.getLogger(GiftTagDAOImpl.class);

    private final String INSERT_QUERY = "insert into gift_tag(gift_id, tag_id) values(?, ?)";
    private final String DELETE_QUERY = "delete from gift_tag where id = ?";
    private final String SELECT_BY_NAME_QUERY = "select gift_certificate.* from gift_certificate, tag, gift_tag where " +
            "gift_tag.gift_id = gift_certificate.id and gift_tag.tag_id = tag.id and tag.name = (?)";

    private final String ID = "id";
    private final String GIFT_ID = "gift_id";
    private final String TAG_ID = "tag_id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String PRICE = "price";
    private final String DURATION = "duration";
    private final String CREATE_DATE = "create_date";
    private final String LAST_UPDATE_DATE = "last_update_date";

    @Override
    public void save(int giftId, int tagId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, String.valueOf(giftId));
            ps.setString(2, String.valueOf(tagId));

            ps.executeUpdate();

            connection.close();
            ps.close();

        } catch (SQLException e){
            LOGGER.error("some problems with save gift_tag");
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
            LOGGER.error("some problems with deleting gift_tag");
            throw new DAOException(e);

        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public List<GiftCertificateBean> getCertificatesByTagName(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        List<GiftCertificateBean> certificates = new ArrayList<>();

        try{
            connection = DBHandler.getConn();

            ps = connection.prepareStatement(SELECT_BY_NAME_QUERY);
            ps.setString(1, name);

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
            LOGGER.error("some problems with extracting gifts");
            throw new DAOException(e);
        } finally {
            if(connection != null){
                DBHandler.closeConnection(connection, ps);
            }
        }
    }
}
