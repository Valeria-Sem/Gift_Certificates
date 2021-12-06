package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.util.CertificateMapper;
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

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            timeout = 360,
            rollbackFor = Exception.class)
    @Override
    public GiftCertificateEntity save(GiftCertificateEntity giftCertificate) throws DAOException {
        try {
            jdbcTemplate.update(INSERT_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                    giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate());

            return jdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e.getLocalizedMessage() + " Liaise to the admin", e);
        }
    }

    @Override
    public GiftCertificateEntity getCertificateById(int id) throws DAOException {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new CertificateMapper(), id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("No such certificate with id = " + id, e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            jdbcTemplate.update(DELETE_QUERY, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with deleting certificate", e);
        }
    }

    @Override
    public List<GiftCertificateEntity> getAllCertificates() throws DAOException {
        try {
            return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with extracting certificates", e);
        }
    }

    @Override
    public void updateCertificate(GiftCertificateEntity updateParams) throws DAOException {
        try {
            jdbcTemplate.update(UPDATE_QUERY, updateParams.getName(), updateParams.getDescription(), updateParams.getPrice(),
                    updateParams.getDuration(), updateParams.getLastUpdateDate(), updateParams.getId());

        } catch (Exception e) {
            LOGGER.error("some problems with updating Certificates");
            e.printStackTrace();
            throw new DAOException("Some problems with updating Certificates", e);
        }
    }
}
