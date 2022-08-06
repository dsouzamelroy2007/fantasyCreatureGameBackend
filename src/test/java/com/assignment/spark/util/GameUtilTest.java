package com.assignment.spark.util;

import com.assignment.spark.model.Attribute;
import com.assignment.spark.model.CreatureDTO;
import com.assignment.spark.model.Action;
import com.assignment.spark.model.Revival;
import com.assignment.spark.model.State;

import com.assignment.spark.model.Weapon;
import com.assignment.spark.model.constant.CurrentAction;
import com.assignment.spark.model.constant.WeaponType;
import com.assignment.spark.repository.Creature;
import com.assignment.spark.repository.CreatureAction;
import com.assignment.spark.repository.CreatureAttribute;
import java.util.List;

public class GameUtilTest {

  public static Creature getCreatureForTest1(){
    Creature creature = Creature.builder()
        .id(212)
        .name("Test Creature 1")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("strength",6)))
        .build();
    return creature;
  }

  public static Creature getCreatureForTest2(){
    Creature creature = Creature.builder()
        .id(3123)
        .name("Test Creature 2")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("speed",8)))
        .build();
    return creature;
  }

  public static CreatureAttribute getCreatureAttributeForTest1(){
    return CreatureAttribute.builder().name("strength").value(8).build();
  }

  public static CreatureAttribute getCreatureAttributeForTest2(){
    return CreatureAttribute.builder().name("armor").value(9).build();

  }

  public static CreatureDTO getCreatureDomainObject1(){
    CreatureDTO creature = CreatureDTO.builder()
        .name("Test Creature 1")
        .state(State.ALIVE)
        .action(getWeaponForTest())
        .attributes(List.of(new Attribute("speed",8)))
        .build();
    return creature;

  }

  public static List<Attribute> getAttributes(){
    Attribute attribute = Attribute.builder()
                                      .name("strength")
                                      .value(8)
                                      .build();
    return List.of(attribute);
  }

  public static Weapon getWeaponForTest(){
    Weapon weapon = Weapon.builder()
                            .weaponType(WeaponType.HAMMER)
                            .build();
    return weapon;
  }

  public static CreatureAction getActionForTest(){
    CreatureAction creatureAction = new CreatureAction();
    Action action = getWeaponForTest();
    String currentAction = action instanceof Weapon ? CurrentAction.WEAPON.name() : action instanceof Revival
                                                                                    ? CurrentAction.REVIVAL.name() :CurrentAction.DEATH.name();
    creatureAction.setAction(currentAction);
    creatureAction.setWeaponType(WeaponType.HAMMER.name());
    return creatureAction;
  }

  public static List<Creature> getCreaturesForTest(){
    Creature creature1 = getCreatureForTest1();
    Creature creature2 = getCreatureForTest2();
    return List.of(creature1,creature2);
  }

  public static List<CreatureAttribute> getCreatureAttributesForTest(){
    CreatureAttribute creatureAttribute1 = getCreatureAttributeForTest1();
    CreatureAttribute creatureAttribute2 = getCreatureAttributeForTest2();
    return List.of(creatureAttribute1,creatureAttribute2);
  }


  public static Creature getCreatureForTest3(){
    Creature creature = Creature.builder()
        .id(212)
        .name("Test Creature 3")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("strength",6),new CreatureAttribute("intelligent",7),new CreatureAttribute("armor",9),new CreatureAttribute("speed",5)))
        .build();
    return creature;
  }

  public static Creature getCreatureForTest4(){
    Creature creature = Creature.builder()
        .id(212)
        .name("Test Creature 4")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("strength",3),new CreatureAttribute("intelligent",7),new CreatureAttribute("armor",9),new CreatureAttribute("speed",5)))
        .build();
    return creature;
  }

  public static Creature getCreatureForTest5(){
    Creature creature = Creature.builder()
        .id(212)
        .name("Test Creature 5")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("strength",3),new CreatureAttribute("intelligent",3),new CreatureAttribute("armor",9),new CreatureAttribute("speed",5)))
        .build();
    return creature;
  }

  public static Creature getCreatureForTest6(){
    Creature creature = Creature.builder()
        .id(212)
        .name("Test Creature 5")
        .state(State.ALIVE.name())
        .action(getActionForTest())
        .attributes(List.of(new CreatureAttribute("strength",2),new CreatureAttribute("intelligent",4),new CreatureAttribute("armor",4),new CreatureAttribute("speed",5)))
        .build();
    return creature;
  }

}
