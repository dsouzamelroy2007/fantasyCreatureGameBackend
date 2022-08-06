package com.assignment.spark.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

@Slf4j
public class CreaturesRowMapper implements RowMapper<Creature> {
    @Override
    public Creature mapRow(ResultSet rs, int rowNumber) {
        try {
            return Creature.builder().id(rs.getInt("id")).name(rs.getString("name")).state(rs.getString("state")).creatureType(rs.getString("type")).build();
        } catch (EmptyResultDataAccessException exception) {
            log.warn("Communication was not found: ", exception);
        } catch (Exception exception) {
            log.warn("Error getting Communication profile data: ", exception);
        }
        return null;

    }
}
