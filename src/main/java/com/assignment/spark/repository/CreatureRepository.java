package com.assignment.spark.repository;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreatureRepository{

    private final JdbcTemplate jdbcTemplate;
    private final CreaturesRowMapper creaturesRowMapper = new CreaturesRowMapper();
    private final CreatureAttributesRowMapper creatureAttributesRowMapper = new CreatureAttributesRowMapper();
    private static final String GET_CREATURES_QUERY = "SELECT id, name FROM creatures";
    private static final String GET_CREATURE_BY_ID = "SELECT * FROM creatures where id = ?";
    private static final String GET_CREATURE_ACTION_BY_ID = "SELECT * FROM creature_action where creature_id = ?";
    private static final String GET_CREATURE_ATTRIBUTES_BY_ID = "SELECT * FROM attributes where creature_id = ?";
    private static final String GET_CREATURE_BY_NAME = "SELECT * FROM creatures where name = ?";
    private static final String SAVE_CREATURE = "INSERT INTO creatures (name,type,action,state,leadership_value) values (?,?,?,?,?)";
    private static final String SAVE_CREATURE_ATTRIBUTES = "INSERT INTO attributes (creature_id,name,attribute_value) values (?,?,?)";
    private static final String SAVE_CREATURE_ACTION = "INSERT INTO creature_action (creature_id,action_name,type) values (?,?,?)";
    private static final String UPDATE_CREATURE_ATTRIBUTE = "UPDATE attributes set attribute_value = ? where creature_id = ? and name = ?)";
    private static final String UPDATE_CREATURE_LEADERSHIP_VALUE = "UPDATE creatures set leadership_value = ? where creature_id = ?)";

    public List<Creature> getCreatures() {
        return jdbcTemplate.query(GET_CREATURES_QUERY, creaturesRowMapper)                                      ;
    }

    public Creature getCreatureById(Integer creatureId){
        Creature creature =  jdbcTemplate.queryForObject(GET_CREATURE_BY_ID,new Object[]{creatureId},Creature.class);
        return populateCreatureDependencies(creature);
    }

    public Creature getCreatureByName(String creatureName){
        Creature creature = jdbcTemplate.queryForObject(GET_CREATURE_BY_NAME,new Object[]{creatureName},Creature.class);
        return populateCreatureDependencies(creature);
    }

    public List<CreatureAttribute> getCreatureAttributesById(Integer creatureId){
        return jdbcTemplate.query(GET_CREATURE_ATTRIBUTES_BY_ID,new Object[]{creatureId}, creatureAttributesRowMapper);
    }

    public CreatureAction getCreatureActionById(Integer creatureId){
        return jdbcTemplate.queryForObject(GET_CREATURE_ACTION_BY_ID,new Object[]{creatureId},CreatureAction.class);
    }

    public Creature populateCreatureDependencies(Creature creature){
        List<CreatureAttribute> creatureAttributes = getCreatureAttributesById(creature.getId());
        CreatureAction creatureAction = getCreatureActionById(creature.getId());
        creature.setAttributes(creatureAttributes);
        creature.setAction(creatureAction);
        return creature;
    }

    public Boolean saveCreature(Creature creatureToSave){
        int craetureSaveCount = jdbcTemplate.update(SAVE_CREATURE,
            creatureToSave.getName(),creatureToSave.getCreatureType(),creatureToSave.getAction().getAction(),creatureToSave.getState(),creatureToSave.getLeadershipValue());
        if(craetureSaveCount != 0){
           Creature creature = getCreatureByName(creatureToSave.getName());
           int attributesSize = creatureToSave.getAttributes().size();
           int attributeSaveCount =  saveCreatureAttributes(creatureToSave.getAttributes(),creature.getId()).length;
           saveCreatureAction(creatureToSave.getAction(),creature.getId());
           return attributeSaveCount == attributesSize;
        }
        return false;
    }

    public int saveCreatureAction(CreatureAction action, Integer id) {
        return jdbcTemplate.update(SAVE_CREATURE_ACTION, id, action.getAction(),action.getWeaponType());
    }

    public int[] saveCreatureAttributes(List<CreatureAttribute> creatureAttributes,Integer creatureId){
        List<Object[]> listObject = new ArrayList<>();
        creatureAttributes.stream().forEach(
            attribute -> listObject.add(new Object[]{creatureId, attribute.getName(), attribute.getValue()})
        );
       return jdbcTemplate.batchUpdate(SAVE_CREATURE_ATTRIBUTES,listObject);
    }

    public boolean updateCreatureAttributeValue(String attributeName, Integer value, Integer id, Integer leadershipValue) {
        int updateCount = jdbcTemplate.update(UPDATE_CREATURE_ATTRIBUTE, value, id, attributeName);
        if(updateCount != 0){
            updateCreatureLeadershipValue(id,leadershipValue);
            return true;
        }
        return false;
    }

    //intentionally kept as private so that it is called only when creatureAttributevalue is updated
    private int updateCreatureLeadershipValue(Integer id, Integer leadershipValue){
        return jdbcTemplate.update(UPDATE_CREATURE_LEADERSHIP_VALUE, leadershipValue, id);
    }

}
