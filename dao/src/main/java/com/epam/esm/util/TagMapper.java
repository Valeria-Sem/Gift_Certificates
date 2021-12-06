package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.TagEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<TagEntity> {
    private final String ID = "id";
    private final String NAME = "name";

    @Override
    public TagEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        TagEntity tag;

        try {
            tag = new TagEntity();
            tag.setId(resultSet.getInt(ID));
            tag.setName(resultSet.getString(NAME));

            return tag;
        } catch (Exception e) {
            throw new SQLException(e.getLocalizedMessage(), e);
        }
    }
}
