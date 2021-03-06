package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftTagDAO;
import com.epam.esm.entity.GiftTagEntity;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.util.GiftTagMapper;
import com.epam.esm.util.SqlQueryBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class GiftTagDAOImpl implements GiftTagDAO {
    private final Logger LOGGER = Logger.getLogger(GiftTagDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "insert into gift_tag(gift_id, tag_id) values(?, (select id from tag where name = ?))";
    private final String DELETE_QUERY = "delete from gift_tag where id = ?";
    private final String SELECT_BY_TAG_NAME_QUERY = "select gift_certificate.* from gift_certificate, tag, gift_tag where " +
            "gift_tag.gift_id = gift_certificate.id and gift_tag.tag_id = tag.id and tag.name = ? order by gift_certificate.id asc";
    private final String SELECT_QUERY = "select * from gift_tag ORDER BY id DESC LIMIT 1";
    private final String GET_TAG_BY_GIFT_ID_QUERY = "select tag.* from gift_certificate, tag, gift_tag where " +
            "gift_tag.gift_id = gift_certificate.id and gift_tag.tag_id = tag.id and gift_certificate.id = ? ";


    @Autowired
    public GiftTagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            timeout = 360,
            rollbackFor = Exception.class)
    @Override
    public GiftTagEntity save(int idCertificate, String tagName) throws DAOException {
        try {
            jdbcTemplate.update(INSERT_QUERY, idCertificate, tagName);

            return jdbcTemplate.queryForObject(SELECT_QUERY, new GiftTagMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with maintaining the connection between the certificate and the tag", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            jdbcTemplate.update(DELETE_QUERY, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with deleting the connection between the certificate and the tag", e);
        }
    }

    @Override
    public List<GiftCertificateEntity> getCertificatesByTagName(String name) throws DAOException {
        try {
            return jdbcTemplate.query(SELECT_BY_TAG_NAME_QUERY,
                    new BeanPropertyRowMapper<>(GiftCertificateEntity.class), name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with extracting certificates by tag name = " + name, e);
        }
    }

    @Override
    public List<TagEntity> getTagsByCertificateId(int id) throws DAOException {
        try {
            return jdbcTemplate.query(GET_TAG_BY_GIFT_ID_QUERY, new BeanPropertyRowMapper<>(TagEntity.class), id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with extracting tags by certificate id = " + id, e);
        }
    }

    @Override
    public List<GiftCertificateEntity> search(Map<String, String> properties) throws DAOException {
        try {
            return jdbcTemplate.query(SqlQueryBuilder.createSqlSearchQuery(properties),
                    new BeanPropertyRowMapper<>(GiftCertificateEntity.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Some problems with extracting certificates", e);
        }
    }
}
