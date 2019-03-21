package com.laidw.mapper;

import com.laidw.entity.Ability;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用于操作Ability的Mapper接口；由于比较简单，所以使用注解方式开发
 */

@Mapper
public interface AbilityMapper {
    @Insert("INSERT INTO ability(name, description) VALUES(#{name}, #{description})")
    void saveAbility(Ability ability);

    @Delete("DELETE FROM ability WHERE name = #{name}")
    void deleteAbilityByName(String name);

    @Delete("DELETE FROM ability WHERE id = #{id}")
    void deleteAbilityById(Integer id);

    @Delete("DELETE FROM ability")
    void deleteAllAbilities();

    @Update("UPDATE ability SET description = #{description} WHERE name = #{name}")
    void updateAbilityByName(Ability ability);

    @Update("UPDATE ability SET name = #{name}, description = #{description} WHERE id = #{id}")
    void updateAbilityById(Ability ability);

    @Select("SELECT * FROM ability WHERE name = #{name}")
    Ability selectAbilityByName(String name);

    @Select("SELECT * FROM ability WHERE id = #{id}")
    Ability selectAbilityById(Integer id);

    @Select("SELECT * FROM ability")
    List<Ability> selectAllAbilities();

    @Select("SELECT * FROM ability ORDER BY name")
    List<Ability> selectAllAbilitiesOrderByName();
}
