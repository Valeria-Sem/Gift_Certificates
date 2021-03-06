package com.epam.esm.util;

import java.util.HashMap;
import java.util.Map;

public class SqlQueryBuilder {

    public static String createSqlSearchQuery(Map<String, String> properties) {
        StringBuilder sql = new StringBuilder("select gift_certificate.* from gift_certificate, tag, gift_tag where " +
                "gift_tag.gift_id = gift_certificate.id and gift_tag.tag_id = tag.id ");

        for (String property : properties.keySet()) {
            switch (property) {
                case ("tag"):
                    if (properties.get(property) != null) {
                        sql.append(" and tag.name = '").append(properties.get(property)).append("'");
                    }
                    break;
                case ("part"):
                    if (properties.get(property) != null) {
                        sql.append(" and gift_certificate.name like '%").append(properties.get(property)).append("%'" +
                                "or gift_certificate.description like '%").append(properties.get(property)).append("%' ");
                    }
                    break;
                case ("sort"):
                    if (properties.get(property) != null) {
                        sql.append(" group by gift_certificate.id order by gift_certificate.name  ").append(properties.get(property));
                    } else {
                        sql.append(" group by gift_certificate.id");
                    }
                    break;
            }
        }

        return sql.toString();
    }
}
