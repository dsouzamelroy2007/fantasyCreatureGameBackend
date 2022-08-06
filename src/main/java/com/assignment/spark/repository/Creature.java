package com.assignment.spark.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Creature {
    private Integer id;
    private String name;
    private CreatureAction action;
    private String state;
    private List<CreatureAttribute> attributes;
    private String creatureType;
    private Integer leadershipValue;
}
