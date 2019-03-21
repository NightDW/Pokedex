package com.laidw.mapper;

import com.laidw.entity.Pokemon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于操作Pokemon的Mapper接口
 */

@Mapper
public interface PokemonMapper {
    /**
     * 添加方法（id会自动增长，所以不需要有id）
     * @param pokemon 需要存储到数据库中的Pokemon对象
     */
    void savePokemon(Pokemon pokemon);

    /**
     * 删除方法，根据宝可梦名称删除宝可梦数据
     * @param name 宝可梦名称
     */
    void deletePokemonByName(String name);

    /**
     * 删除方法，根据宝可梦的id删除宝可梦数据
     * @param id 宝可梦的id
     */
    void deletePokemonById(Integer id);

    /**
     * 删除所有宝可梦数据
     */
    void deleteAllPokemons();

    /**
     * 删除有指定属性的宝可梦
     * @param typeName 属性名称
     */
    void deletePokemonsByTypeName(String typeName);

    /**
     * 删除有指定特性的宝可梦
     * @param abilityName 特性名称
     */
    void deletePokemonsByAbilityName(String abilityName);

    /**
     * 根据宝可梦名称来更新数据
     * @param pokemon Pokemon对象，需要指明其name属性，且非空属性的数据才会更新到数据库中
     */
    void updatePokemonByNameSelectively(Pokemon pokemon);

    /**
     * 根据宝可梦名称来更新数据
     * @param pokemon Pokemon对象，需要指明其name属性，且所有属性的数据都会更新到数据库中
     */
    void updatePokemonByName(Pokemon pokemon);

    /**
     * 根据宝可梦的id来更新数据
     * @param pokemon Pokemon对象，需要指明其id属性，且非空属性的数据才会更新到数据库中
     */
    void updatePokemonByIdSelectively(Pokemon pokemon);

    /**
     * 根据宝可梦的id来更新数据
     * @param pokemon Pokemon对象，需要指明其id属性，且所有属性的数据都会更新到数据库中
     */
    void updatePokemonById(Pokemon pokemon);

    /**
     * 把宝可梦的旧属性名改成新属性名
     * @param oldName 旧属性名
     * @param newName 新属性名
     */
    void renamePokemonTypeName(@Param("oldName")String oldName, @Param("newName") String newName);

    /**
     * 把宝可梦的旧特性名改成新特性名
     * @param oldName 旧特性名
     * @param newName 新特性名
     */
    void renamePokemonAbilityName(@Param("oldName")String oldName, @Param("newName") String newName);

    /**
     * 根据宝可梦名称来查询宝可梦
     * @param name 宝可梦名称
     * @return 查询结果
     */
    Pokemon selectPokemonByName(String name);

    /**
     * 根据宝可梦的id来查询宝可梦
     * @param id 宝可梦的id
     * @return 查询结果
     */
    Pokemon selectPokemonById(Integer id);

    /**
     * 查询所有的宝可梦数据
     * @return 查询结果
     */
    List<Pokemon> selectAllPokemons();
}
