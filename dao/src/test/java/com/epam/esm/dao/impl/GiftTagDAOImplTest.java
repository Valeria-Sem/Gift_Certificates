package com.epam.esm.dao.impl;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class,
        loader = AnnotationConfigContextLoader.class)
class GiftTagDAOImplTest {
    private final String INSERT_QUERY = "insert into gift_tag_m(gift_id, tag_id) values(?, (select id from tag where name = ?))";
    private final String DELETE_QUERY = "delete from gift_tag_m where id = ?";
    private final String SELECT_BY_ID = "Select * from gift_tag_m where id = ?";
    private final String SELECT_BY_TAG_NAME_QUERY = "select gift_m.* from gift_m, tag_m, gift_tag_m where " +
            "gift_tag_m.gift_id = gift_m.id and gift_tag_m.tag_id = tag_m.id and tag_m.name = ? order by gift_m.id asc";
    private final String SELECT_QUERY = "select * from gift_tag_m ORDER BY id DESC LIMIT 1";
    private final String SELECT_QUERY_BY_PART_AND_TAG_AND_SORT = "select gift_m.* from gift_m, tag_m, gift_tag_m where " +
            "gift_tag_m.gift_id = gift_m.id and gift_tag_m.tag_id = tag_m.id and tag_m.name = ? " +
            "and gift_m.name like CONCAT( '%',?,'%') order by gift_m.name ";
    private final String SELECT_QUERY_BY_PART_AND_TAG = "select gift_m.* from gift_m, tag_m, gift_tag_m where "+
            "gift_tag_m.gift_id = gift_m.id and gift_tag_m.tag_id = tag_m.id and tag_m.name = ? " +
            "and gift_m.name like CONCAT( '%',?,'%')";
    private final String SELECT_QUERY_BY_TAG_AND_SORT = "select gift_m.* from gift_m, tag_m, gift_tag_m where " +
            "gift_tag_m.gift_id = gift_m.id and gift_tag_m.tag_id = tag_m.id and tag_m.name = ? " +
            "order by gift_m.name ";

    private static final int id = 18;
    private static final String tagName = "rose";
    private static final String part = "e";
    private static final int idC = 11;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/gift-table.sql"));
        tables.addScript(new ClassPathResource("/tag-table.sql"));
        tables.addScript(new ClassPathResource("/gift_tag-table.sql"));

        DatabasePopulatorUtils.execute(tables, dataSource);
    }


    @Test
    void save() {
        jdbcTemplate.update(INSERT_QUERY, idC, tagName);

        GiftTagEntity newGiftTag = jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(GiftTagEntity.class))
                .stream().findAny().orElse(null);

        assert newGiftTag != null;
        assertEquals(id, newGiftTag.getId());
    }


    @Test
    void getCertificatesByTagName() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_BY_TAG_NAME_QUERY, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), tagName);

        assertNotNull(certificates);
    }

    @Test
    void searchAndSortByPartOfCertificateNameAndTag() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_QUERY_BY_PART_AND_TAG_AND_SORT + "desc", new BeanPropertyRowMapper<>(GiftCertificateEntity.class), tagName, part);

        assertNotNull(certificates);
    }

    @Test
    void searchByPartAndTag() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_QUERY_BY_PART_AND_TAG, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), tagName, part);

        assertNotNull(certificates);
    }

    @Test
    void searchAndSortByTag() {
        List<GiftCertificateEntity> certificates = jdbcTemplate.query(SELECT_QUERY_BY_TAG_AND_SORT + "asc", new BeanPropertyRowMapper<>(GiftCertificateEntity.class), tagName);

        assertNotNull(certificates);
    }

    @Test
    void delete() {
        jdbcTemplate.update(DELETE_QUERY, id);

        assertNull(jdbcTemplate.query(SELECT_BY_ID, new BeanPropertyRowMapper<>(GiftCertificateEntity.class), id)
                .stream().findAny().orElse(null));
    }

    @AfterEach
    public void tearDown() {
        JdbcTestUtils.dropTables(jdbcTemplate, "gift_m");
        JdbcTestUtils.dropTables(jdbcTemplate, "tag_m");
        JdbcTestUtils.dropTables(jdbcTemplate, "gift_tag_m");
    }
}
