package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificateEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "insert into gift_certificate(name, description, price, duration, create_date, last_update_date)" +
            " values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "delete from gift_certificate where id = ?";
    private final String SELECT_ALL_QUERY = "select * from gift_certificate";
    private final String SELECT_QUERY = "select * from gift_certificate ORDER BY id DESC LIMIT 1";
    private final String SELECT_BY_ID_QUERY = "select * from gift_certificate where id = ?";
    private final String UPDATE_QUERY = "update gift_certificate set name = ?, description = ?, price = ?, duration = ?," +
            " last_update_date = ? where id = ?";


    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 360, rollbackFor = Exception.class)
    @Override
    public GiftCertificateEntity save(GiftCertificateEntity giftCertificate) throws DAOException {
        try {
            jdbcTemplate.update(INSERT_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                    giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate());

            return jdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
        } catch (Exception e) {
            throw new DAOException("Some problems with saving certificate");
        }

    }

    @Override
    public GiftCertificateEntity getCertificateById(int id) throws DAOException {
        try {
            return jdbcTemplate.query(SELECT_BY_ID_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), id)
                .stream().findAny().orElse(null);
        } catch (Exception e) {
            throw new DAOException("Some problems with saving certificate");
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            jdbcTemplate.update(DELETE_QUERY, id);
        } catch (Exception e) {
            throw new DAOException("Some problems with deleting certificate");
        }
    }

    @Override
    public List<GiftCertificateEntity> getAllCertificates() throws DAOException {
        try {
            return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
        } catch (Exception e) {
            throw new DAOException("Some problems with extracting certificates");
        }
    }

    @Override
    public void updateCertificate(GiftCertificateEntity updateParams) throws DAOException {
        try {
            jdbcTemplate.update(UPDATE_QUERY, updateParams.getName(), updateParams.getDescription(), updateParams.getPrice(),
                    updateParams.getDuration(), updateParams.getLastUpdateDate(), updateParams.getId());

        } catch (Exception e) {
            LOGGER.error("some problems with updating Certificates");
            throw new DAOException("some problems with updating Certificates");
        }
    }

//    @Override
//    public void updateCertificate(HashMap updateParams) throws DAOException {
//        try {
//            updateParams.forEach((key, value) -> {
//                try {
//                    if (key.equals("id") || value == null) {
//                        return;
//                    }
//
//                    jdbcTemplate.update("update gift_certificate set " + key + " = '" + value + "' where id = "
//                            + updateParams.get("id"));
//                } catch (Exception e) {
//                    LOGGER.error("some problems with updateParams");
//                    e.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            LOGGER.error("some problems with updating Certificates");
//            throw new DAOException("some problems with updating Certificates");
//        }
//    }
}
