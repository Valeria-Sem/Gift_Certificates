package com.epam.esm.dao.impl;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificateEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class,
        loader = AnnotationConfigContextLoader.class)
class GiftCertificateDAOImplTest {
    private final String INSERT_QUERY = "insert into gift_m(name, description, price, duration, create_date, last_update_date)" +
            " values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "delete from gift_m where id = ?";
    private final String SELECT_BY_ID = "Select * from gift_m where id = ?";
    private final String SELECT_ALL_QUERY = "select * from gift_m";
    private final String SELECT_BY_PART_OF_NAME = "select * from gift_m where name like CONCAT( '%',?,'%')";
    private final String SELECT_SORT = "select * from gift_m order by name ";
    private final String SELECT_QUERY = "select * from gift_m ORDER BY id DESC LIMIT 1";
    private final String SELECT_BY_PART_AND_SORT = "select * from gift_m where name like CONCAT( '%',?,'%') order by name ";

    private static final int id = 11;
    private static final String name = "new";
    private static final String description = "10";
    private static final double price = 10;
    private static final int duration = 10;
    private static final Date createDate = Date.valueOf("2021-10-10");
    private static final Date lastUpdateDate = Date.valueOf("2021-10-20");

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/gift-table.sql"));

        DatabasePopulatorUtils.execute(tables, dataSource);
    }


    @AfterEach
    public void tearDown() {
        JdbcTestUtils.dropTables(jdbcTemplate, "gift_m");
    }

    @Test
    void save() throws DAOException {
        jdbcTemplate.update(INSERT_QUERY, name, description,
                price, duration, createDate,
                lastUpdateDate);

        GiftCertificateEntity newCertificate = jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class))
                .stream().findAny().orElse(null);

        assert newCertificate != null;
        assertEquals(id, newCertificate.getId());
    }

    @Test
    void getAllCertificates() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class));

        assertNotNull(certificates);
    }

    @Test
    void updateCertificate() {
        jdbcTemplate.update("update gift_certificate set name = 'newName' where id = "+ id);

        GiftCertificateEntity newCertificate = jdbcTemplate.query(SELECT_BY_ID, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), id)
                .stream().findAny().orElse(null);

        assert newCertificate != null;
        assertEquals("newName", newCertificate.getName());
    }

    @Test
    void searchByPartOfCertificateName() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_BY_PART_OF_NAME, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), "d");

        assertNotNull(certificates);
    }

    @Test
    void sort() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_SORT + "desc", new BeanPropertyRowMapper<>(GiftCertificateEntity.class));

        assertNotNull(certificates);
    }

    @Test
    void searchAndSortByPartOfCertificateName() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_BY_PART_AND_SORT + "asc", new BeanPropertyRowMapper<>(GiftCertificateEntity.class), "d");

        assertNotNull(certificates);
    }

    @Test
    void delete() {
        jdbcTemplate.update(DELETE_QUERY, id);

        assertNull(jdbcTemplate.query(SELECT_BY_ID, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), id)
                .stream().findAny().orElse(null));

    }


}
