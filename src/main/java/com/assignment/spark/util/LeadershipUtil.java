package com.assignment.spark.util;

import com.assignment.spark.repository.Creature;
import com.assignment.spark.repository.CreatureAttribute;
import io.swagger.models.auth.In;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

public class LeadershipUtil {


  private static int MIN_VALUE = 5;

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

*/
  private static Map<Integer,Integer> ATTRIBUTE_VALUE_LEADERSHIP_MAP = Map.of(1,-100,2,-65,3,-30,4,0,5,16,6,34,7,48,8,62,9,76,10,100);

  @Setter
  @Getter
  private static class CreatureAttributeStats{
    boolean isLeader;
    boolean allAttributesLeader;
     int minNegativeAttributeValue = Integer.MAX_VALUE;
    int minPositiveAttributeValue = Integer.MAX_VALUE;
    int averagePositiveLeadershipValue = 0;

  }

  public static Integer getLeaderShipValueForCreature(Creature creature){
    List<CreatureAttribute> creatureAttributeList = creature.getAttributes();
    CreatureAttributeStats creatureAttributeStats = getCreatureAttributeStats(creatureAttributeList);
    return getLeaderShipValue(creatureAttributeStats);
  }

  private static Integer getLeaderShipValue(CreatureAttributeStats creatureAttributeStats) {
    if(creatureAttributeStats.isAllAttributesLeader())
      return creatureAttributeStats.getAveragePositiveLeadershipValue();
    else if(creatureAttributeStats.isLeader())
      return ATTRIBUTE_VALUE_LEADERSHIP_MAP.get(creatureAttributeStats.getMinPositiveAttributeValue());
    else
      return ATTRIBUTE_VALUE_LEADERSHIP_MAP.get(creatureAttributeStats.getMinNegativeAttributeValue());
  }

  private static CreatureAttributeStats getCreatureAttributeStats(List<CreatureAttribute> creatureAttributeList){
    List<Integer> postiveAttributes = new ArrayList<>();
    CreatureAttributeStats creatureAttributeStats = new CreatureAttributeStats();
    creatureAttributeList
        .stream()
        .mapToInt( attribute -> attribute.getValue())
        .forEach(
        attributeVal -> {
          if(attributeVal >= MIN_VALUE) {
            postiveAttributes.add(attributeVal);
            creatureAttributeStats.minPositiveAttributeValue = Math.min(creatureAttributeStats.minPositiveAttributeValue,attributeVal);
          }else
            creatureAttributeStats.minNegativeAttributeValue = Math.min(creatureAttributeStats.minNegativeAttributeValue,attributeVal);
        }
    );

    creatureAttributeStats.isLeader = postiveAttributes.size() == 3;
    creatureAttributeStats.allAttributesLeader = postiveAttributes.size() == 4;

    creatureAttributeStats.averagePositiveLeadershipValue = (int) postiveAttributes.stream().
                                                                                        mapToInt(attributeVal -> ATTRIBUTE_VALUE_LEADERSHIP_MAP.get(attributeVal)).
                                                                                        average().getAsDouble();
    return creatureAttributeStats;
  }


}
