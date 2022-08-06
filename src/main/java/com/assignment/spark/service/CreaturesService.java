package com.assignment.spark.service;

import com.assignment.spark.model.Attribute;
import com.assignment.spark.model.CreatureDTO;
import com.assignment.spark.repository.Creature;
import com.assignment.spark.repository.CreatureAttribute;
import com.assignment.spark.repository.CreatureRepository;
import com.assignment.spark.util.GameUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreaturesService {


    private final CreatureRepository creatureRepository;

    public List<Creature> getCreatures() {
        return creatureRepository.getCreatures();
    }


    public Creature getCreatureById(Integer id) {
        return creatureRepository.getCreatureById(id);
    }

    public Creature getCreatureByName(String name) {
        return creatureRepository.getCreatureByName(name);
    }


    public Boolean createCreature(CreatureDTO newCreature){
       Creature creature = GameUtil.getCreatureDomainObjectFromCreatureModel(newCreature);
       creature.setLeadershipValue(getLeadershipValueForCreature(creature));
       return creatureRepository.saveCreature(creature);
    }

    public Integer getLeadershipValueForCreature(Creature creature){
        return GameUtil.getLeadershipValue(creature);
    }

    public Boolean updateAttributeValueForCreature(Attribute attribute, Integer id){
        Creature creature = getCreatureById(id);
        List<CreatureAttribute> creatureAttributes = GameUtil.getUpdatedAttributes(creature.getAttributes(),attribute);
        creature.setAttributes(creatureAttributes);
        return creatureRepository.updateCreatureAttributeValue(attribute.getName(), attribute.getValue(),id,getLeadershipValueForCreature(creature));
    }


}
