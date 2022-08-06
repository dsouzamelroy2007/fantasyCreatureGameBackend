package com.assignment.spark.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.assignment.spark.util.GameUtil;
import static com.assignment.spark.util.GameUtilTest.*;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class CreatureRepositoryTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private CreatureRepository creatureRepository;
  private static final String GET_CREATURES_QUERY = "SELECT id, name FROM creatures";
  private static final String GET_CREATURE_BY_ID = "SELECT * FROM creatures where id = ?";
  private static final String GET_CREATURE_ACTION_BY_ID = "SELECT * FROM creature_action where creature_id = ?";
  private static final String GET_CREATURE_ATTRIBUTES_BY_ID = "SELECT * FROM attributes where creature_id = ?";
  private static final String GET_CREATURE_BY_NAME = "SELECT * FROM creatures where name = ?";
  private static final String UPDATE_CREATURE_ATTRIBUTE = "UPDATE attributes set attribute_value = ? where creature_id = ? and name = ?)";
  private static final String UPDATE_CREATURE_LEADERSHIP_VALUE = "UPDATE creatures set leadership_value = ? where creature_id = ?)";


  @Test
  public void testGetCreationActionByIdSucess(){

    CreatureAction expectedAction = getActionForTest();
    when(jdbcTemplate.queryForObject(eq(GET_CREATURE_ACTION_BY_ID),eq(new Object[]{123}),eq(CreatureAction.class)))
        .thenReturn(expectedAction);
    CreatureAction creatureAction= creatureRepository.getCreatureActionById(123);

    Assertions.assertNotNull( creatureAction != null);
    Assertions.assertEquals(expectedAction.getAction(),creatureAction.getAction());
  }

  @Test
  public void testIfCreaturesArePresent(){

    List<Creature> expectedCreatures = getCreaturesForTest();
    when(jdbcTemplate.query(eq(GET_CREATURES_QUERY),any(CreaturesRowMapper.class)))
        .thenReturn(expectedCreatures);

    List<Creature> creatureList = creatureRepository.getCreatures();

    Assertions.assertEquals(expectedCreatures.size(),creatureList.size());
  }


  @Test
  public void testCreatureisPresent(){

    Creature expectedCreature = getCreatureForTest1();

    when(jdbcTemplate.queryForObject(eq(GET_CREATURE_BY_ID),eq(new Object[]{212}),eq(Creature.class)))
       .thenReturn(expectedCreature);

    when(jdbcTemplate.query(eq(GET_CREATURE_ATTRIBUTES_BY_ID),eq(new Object[]{212}), any(CreatureAttributesRowMapper.class)))
        .thenReturn(getCreatureAttributesForTest());


    Creature creatureResult = creatureRepository.getCreatureById(212);

    Assertions.assertTrue(creatureResult != null);

  }

  @Test
  public void testGetCreatureAttributesSuccess(){
    List<CreatureAttribute> expectedCreatureAttributes = getCreatureAttributesForTest();
    when(jdbcTemplate.query(eq(GET_CREATURE_ATTRIBUTES_BY_ID),eq(new Object[]{212}), any(CreatureAttributesRowMapper.class)))
        .thenReturn(expectedCreatureAttributes);

    List<CreatureAttribute> creatureAttributesResult = creatureRepository.getCreatureAttributesById(212);

    Assertions.assertEquals(expectedCreatureAttributes.size(),creatureAttributesResult.size());
  }

  @Test
  public void testGetCreatureByNameSuccess(){
    Creature expectedCreature = getCreatureForTest1();

    when(jdbcTemplate.queryForObject(eq(GET_CREATURE_BY_NAME),eq(new Object[]{"Test Creature 1"}),eq(Creature.class)))
        .thenReturn(expectedCreature);

    Creature creatureFound = creatureRepository.getCreatureByName("Test Creature 1");

    Assertions.assertEquals(expectedCreature.getId(),creatureFound.getId());
  }
}
