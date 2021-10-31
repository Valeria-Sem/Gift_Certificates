package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Component
public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "insert into gift_certificate(name, description, price, duration, create_date, last_update_date)" +
            " values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "delete from gift_certificate where id = ?";
    private final String SELECT_ALL_QUERY = "select * from gift_certificate";
    private final String SELECT_BY_PART_OF_NAME = "select * from gift_certificate where name like '%' + ? + '%'";
    private final String SELECT_SORT_ASC = "select * from gift_certificate order by name asc";
    private final String SELECT_QUERY = "select * from gift_certificate ORDER BY id DESC LIMIT 1";

    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 360, rollbackFor = Exception.class)
    @Override
    public GiftCertificateEntity save(GiftCertificateEntity giftCertificate) throws DAOException{
        jdbcTemplate.update(INSERT_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate());

        return jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void delete(int id) throws DAOException {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    @Override
    public List<GiftCertificateEntity> getAllCertificates() throws DAOException {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
    }

    @Override
    public void updateCertificate(HashMap updateParams) throws DAOException {
        try{
            updateParams.forEach((key, value) -> {
                try {
                    if(key.equals("id")){
                        return;
                    }
                    jdbcTemplate.update("update gift_certificate set " + key + " = '" + value + "' where id = " + updateParams.get("id"));
                } catch (Exception e) {
                    LOGGER.error("some problems with updateParams");
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            LOGGER.error("some problems with updating Certificates");
            throw new DAOException(e);
        }
    }

    @Override
    public List<GiftCertificateEntity> searchByPartOfCertificateName(String part) throws DAOException {
       return jdbcTemplate.query(SELECT_BY_PART_OF_NAME, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), part);
    }

    @Override
    public List<GiftCertificateEntity> sortByASC() throws DAOException {
        return jdbcTemplate.query(SELECT_SORT_ASC, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
    }
}
