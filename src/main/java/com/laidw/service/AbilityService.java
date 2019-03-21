package com.laidw.service;

import com.laidw.entity.Ability;
import com.laidw.entity.PageBean;

import java.util.List;

/**
 * 该Service负责处理宝可梦特性相关的业务（增删改查）
 */

public interface AbilityService {
    void saveAbility(Ability ability);

    void deleteAbilityByName(String name);
    void deleteAbilityById(Integer id);
    void deleteAllAbilities();

    void updateAbilityByName(Ability ability);
    void updateAbilityById(Ability ability);

    Ability selectAbilityByName(String name);
    Ability selectAbilityById(Integer id);
    List<Ability> selectAllAbilities();
    List<Ability> selectAllAbilitiesOrderByName();
    PageBean<Ability> selectAllAbilitiesLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
