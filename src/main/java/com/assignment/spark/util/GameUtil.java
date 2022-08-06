package com.assignment.spark.util;

import com.assignment.spark.model.Action;
import com.assignment.spark.model.Attribute;
import com.assignment.spark.model.CreatureDTO;
import com.assignment.spark.model.Revival;
import com.assignment.spark.model.Weapon;
import com.assignment.spark.model.constant.CurrentAction;
import com.assignment.spark.repository.CreatureAction;
import com.assignment.spark.repository.CreatureAttribute;
import com.assignment.spark.repository.Creature;
import java.util.ArrayList;
import java.util.List;

public class GameUtil {

  public static Creature getCreatureDomainObjectFromCreatureModel(CreatureDTO creatureDTO){
    Creature creature = Creature.builder()
                                  .name(creatureDTO.getName())
                                  .state(creatureDTO.getState().name())
                                  .action(getActionForCreature(creatureDTO.getAction()))
                                  .attributes(getAttributes(creatureDTO.getAttributes()))
                                  .build();
    return creature;
  }

  public static List<CreatureAttribute> getAttributes(List<Attribute> attributes) {
    List<CreatureAttribute> creatureAttributes =  new ArrayList<>();
    attributes.stream().forEach(
            a -> { CreatureAttribute creatureAttribute =
                  CreatureAttribute.builder()
                      .name(a.getName())
                      .value(a.getValue())
                      .build();
               creatureAttributes.add(creatureAttribute);
            }
    );
    return creatureAttributes;
  }

  public static CreatureAction getActionForCreature(Action action){
    CreatureAction creatureAction = new CreatureAction();
    String currentAction = action instanceof Weapon
        ? CurrentAction.WEAPON.name() : action instanceof Revival
        ? CurrentAction.REVIVAL.name() :CurrentAction.DEATH.name();
    creatureAction.setAction(currentAction);
    return creatureAction;
  }

  public static Integer getLeadershipValue(Creature creature){
    return LeadershipUtil.getLeaderShipValueForCreature(creature);
  }

  public static List<CreatureAttribute> getUpdatedAttributes(List<CreatureAttribute> creatureAttributes,Attribute updatedAttribute){
    List<CreatureAttribute> updatedCreatureAttributeList =  new ArrayList<>();
    creatureAttributes.stream().forEach(
        a -> { CreatureAttribute creatureAttribute = null;
         if(updatedAttribute.getName().equals(a.getName())) {
           creatureAttribute = CreatureAttribute.builder()
               .name(a.getName())
               .value(updatedAttribute.getValue())
               .build();
           updatedCreatureAttributeList.add(creatureAttribute);
         }else
           updatedCreatureAttributeList.add(a);
        }
    );
    return updatedCreatureAttributeList;
  }

}


/*

     1 to 10 for each attribute value
      1 - -100
      2 -  -65
      3 -  -30
      4 -  0
      5 -  16
      6  - 34
      7 - 48
      8 - 62
      9 - 76
      10 - 100

      creature 1 - 4 attributes


      9,10  - -20 or 0

      7,8  -   -60 or -60

      5,6  -  -100 or -80

      4 - 0    0
      3 - -30   -30

      3 - -30

      3 - -30  //


      //10 - 100  -100


        -30 -20 -10 0 10 20 30 40


      c1
       3 - -30
       4 -  0
       1 - -100
       10 -  100 - 0

      c1 = -65


       c2
          3 - -30
          4 -  0
          1 - -100
          8 - 62  -40

        c2 =  -85

       = 10
      c2 = 40

      c1 < c2

      -90 /4 =   -22



      400 / 4 = 100

      -20

      leadership > 0    --  45



     1 to 4 corresponds to -100 to 0 of leadership val
     5 - 10 corresponds to 0 to 100 of leadership val


    fetch all attributes from creature


 */
