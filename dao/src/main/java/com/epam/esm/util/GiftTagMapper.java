package com.epam.esm.util;

import com.epam.esm.entity.GiftTagEntity;
import com.epam.esm.entity.TagEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftTagMapper implements RowMapper<GiftTagEntity> {
    private final String ID = "id";
    private final String GIFT_ID = "gift_id";
    private final String TAG_ID = "tag_id";

    @Override
    public GiftTagEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftTagEntity giftTag;

        try{
            giftTag = new GiftTagEntity();
            giftTag.setId(resultSet.getInt(ID));
            giftTag.setId(resultSet.getInt(GIFT_ID));
            giftTag.setId(resultSet.getInt(TAG_ID));

            return giftTag;

        } catch (Exception e){
            throw new SQLException(e.getLocalizedMessage(), e);
        }
    }
}
