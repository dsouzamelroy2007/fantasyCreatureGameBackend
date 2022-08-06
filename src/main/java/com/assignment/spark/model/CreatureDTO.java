package com.assignment.spark.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreatureDTO implements Serializable {

  private Integer id;
  private String name;
  private Action action;
  private State state;
  private List<Attribute> attributes;
}
