package com.assignment.spark.model;

import com.assignment.spark.model.constant.WeaponType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Weapon extends Action {

  private WeaponType weaponType;
}
