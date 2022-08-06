package com.assignment.spark.service;

import com.assignment.spark.model.Attribute;
import com.assignment.spark.model.CreatureDTO;
import com.assignment.spark.model.State;
import com.assignment.spark.repository.Creature;
import com.assignment.spark.repository.CreatureAttribute;
import com.assignment.spark.repository.CreatureRepository;
import static com.assignment.spark.util.GameUtilTest.*;

import com.assignment.spark.util.GameUtil;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatureServiceTest {

  @Mock
  CreatureRepository creatureRepository;

  @InjectMocks
  CreaturesService creaturesService;

  @Test
  public void testActionAllowedOnACreatureTrue(){
    Creature creature = getCreatureForTest1();
      when(creatureRepository.getCreatureById(5)).
              thenReturn(creature);
    Creature creatureResult = creaturesService.getCreatureById(5);

    Assertions.assertTrue(creatureResult != null);
    Assertions.assertEquals(State.ALIVE.name(),creatureResult.getState());
  }

  @Test
  public void testCreateCreatureSuccess(){
    CreatureDTO creatureToBeAdded = getCreatureDomainObject1();

     when(creatureRepository.saveCreature(any()))
            .thenReturn(true);

    Boolean result = creaturesService.createCreature(creatureToBeAdded);
    Assertions.assertTrue(result);
  }

  @Test
  public void testGetCreatureByNameSuccess(){
    Creature expectedCreature = getCreatureForTest2();

     when(creatureRepository.getCreatureByName(anyString()))
         .thenReturn(expectedCreature);

     Creature resultantCreature = creaturesService.getCreatureByName("Test Creature 2");

     Assertions.assertEquals(expectedCreature.getId(),resultantCreature.getId());

  }
  @Test
  public void testCalculateLeadershipValueForCreatureWithAllAttributeValuesGreaterThan5(){
    Creature creature = getCreatureForTest3();

    Integer leadershipValue = creaturesService.getLeadershipValueForCreature(creature);

    Assertions.assertEquals(43,leadershipValue);
  }

  @Test
  public void testCalculateLeadershipValueForCreatureWithThreeAttributeValuesGreaterThan5(){
    Creature creature = getCreatureForTest4();

    Integer leadershipValue = creaturesService.getLeadershipValueForCreature(creature);

    Assertions.assertEquals(16,leadershipValue);
  }

  @Test
  public void testLeaderValueChangesAfterCreaturesAttributeValueIsUpdated(){
    Creature creature = getCreatureForTest4();

    Integer leadershipValueBeforeUpdate = creaturesService.getLeadershipValueForCreature(creature);

    Assertions.assertEquals(16,leadershipValueBeforeUpdate);

    when(creatureRepository.getCreatureById(212)).
        thenReturn(creature);
    lenient().when(creatureRepository.updateCreatureAttributeValue("strength", 6,212,creaturesService.getLeadershipValueForCreature(creature)))
        .thenReturn(true);
    creaturesService.updateAttributeValueForCreature(new Attribute("strength",6),212);

    when(creatureRepository.getCreatureById(212)).
        thenReturn(getCreatureForTest3());
    Creature updatedCreature = creaturesService.getCreatureById(212);

    Integer leadershipValueAfterUpdate = creaturesService.getLeadershipValueForCreature(updatedCreature);

    Assertions.assertEquals(43,leadershipValueAfterUpdate);
  }

  @Test
  public void testCalculateLeadershipValueForCreatureWithTwoAttributeValuesGreaterThan5(){
    Creature creature = getCreatureForTest5();

    Integer leadershipValue = creaturesService.getLeadershipValueForCreature(creature);

    Assertions.assertEquals(-30,leadershipValue);
  }

  @Test
  public void testCalculateLeadershipValueForCreatureWithOneAttributeValuesGreaterThan5(){
    Creature creature = getCreatureForTest6();

    Integer leadershipValue = creaturesService.getLeadershipValueForCreature(creature);

    Assertions.assertEquals(-65,leadershipValue);
  }


}
