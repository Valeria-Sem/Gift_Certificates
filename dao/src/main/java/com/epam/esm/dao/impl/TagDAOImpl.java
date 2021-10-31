package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TagDAOImpl implements TagDAO {
    private final Logger LOGGER = Logger.getLogger(TagDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "insert into tag(name) values(?)";
    private final String DELETE_QUERY = "delete from tag where id = ?";
    private final String SELECT_ALL_QUERY = "select * from tag";
    private final String SELECT_BY_NAME_QUERY = "select * from tag where name = ?";
    private final String SELECT_QUERY = "select * from tag ORDER BY id DESC LIMIT 1";

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 360, rollbackFor = Exception.class)
    @Override
    public TagEntity save(TagEntity tag) throws DAOException {
        jdbcTemplate.update(INSERT_QUERY, tag.getName());

        return jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(TagEntity.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void delete(int id) throws DAOException {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    @Override
    public List<TagEntity> getAllTags() throws DAOException {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(TagEntity.class));
    }

    @Override
    public TagEntity getTagByName(String name) throws DAOException {
        return jdbcTemplate.query(SELECT_BY_NAME_QUERY, new BeanPropertyRowMapper<>(TagEntity.class), name)
                .stream().findAny().orElse(null);
    }
}
