package com.laidw.mapper;

import com.laidw.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用于操作Type的Mapper接口；由于比较简单，所以使用注解方式开发
 */

@Mapper
public interface TypeMapper {
    @Insert("INSERT INTO pkmtype(name, icon_url) VALUES(#{name}, #{iconUrl})")
    void saveType(Type type);

    @Delete("DELETE FROM pkmtype WHERE name = #{name}")
    void deleteTypeByName(String name);

    @Delete("DELETE FROM pkmtype WHERE id = #{id}")
    void deleteTypeById(Integer id);

    @Delete("DELETE FROM pkmtype")
    void deleteAllTypes();

    @Update("UPDATE pkmtype SET icon_url = #{iconUrl} WHERE name = #{name}")
    void updateTypeByName(Type type);

    @Update("UPDATE pkmtype SET name = #{name}, icon_url = #{iconUrl} WHERE id = #{id}")
    void updateTypeById(Type type);

    @Select("SELECT * FROM pkmtype WHERE name = #{name}")
    Type selectTypeByName(String name);

    @Select("SELECT * FROM pkmtype WHERE id = #{id}")
    Type selectTypeById(Integer id);

    @Select("SELECT * FROM pkmtype")
    List<Type> selectAllTypes();

    @Select("SELECT * FROM pkmtype ORDER BY name")
    List<Type> selectAllTypesOrderByName();
}
