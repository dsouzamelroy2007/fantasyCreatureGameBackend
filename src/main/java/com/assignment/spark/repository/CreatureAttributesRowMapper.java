package com.assignment.spark.repository;

import java.sql.ResultSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

@Slf4j
public class CreatureAttributesRowMapper implements RowMapper<CreatureAttribute> {
    @Override
    public CreatureAttribute mapRow(ResultSet rs, int rowNumber) {
        try {
            return CreatureAttribute.builder().name(rs.getString("name")).value(rs.getInt("attribute_value")).build();
        } catch (EmptyResultDataAccessException exception) {
            log.warn("Communication was not found: ", exception);
        } catch (Exception exception) {
            log.warn("Error getting Communication profile data: ", exception);
        }
        return null;

    }
}
