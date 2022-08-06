package com.assignment.spark.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Attribute {

  @NotNull
  private String name;

  @Max(value = 10)
  @Min(value = 1)
  private Integer value;


}
