package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificateEntity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateMapper implements RowMapper<GiftCertificateEntity> {
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String PRICE = "price";
    private final String DURATION = "duration";
    private final String CREATE_DATE = "create_date";
    private final String LAST_UPDATE_DATE = "last_update_date";

    @Override
    public GiftCertificateEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftCertificateEntity certificate;

        try {
            certificate = new GiftCertificateEntity();
            certificate.setId(resultSet.getInt(ID));
            certificate.setName(resultSet.getString(NAME));
            certificate.setDescription(resultSet.getString(DESCRIPTION));
            certificate.setPrice(resultSet.getDouble(PRICE));
            certificate.setDuration(resultSet.getInt(DURATION));
            certificate.setCreateDate(resultSet.getDate(CREATE_DATE));
            certificate.setLastUpdateDate(resultSet.getDate(LAST_UPDATE_DATE));

            return certificate;
        } catch (Exception e) {
            throw new SQLException(e.getLocalizedMessage(), e);
        }
    }
}
