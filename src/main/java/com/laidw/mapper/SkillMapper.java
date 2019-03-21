package com.laidw.mapper;

import com.laidw.entity.Skill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于操作Skill的Mapper接口
 */

@Mapper
public interface SkillMapper {
    /**
     * 添加方法（id会自动增长，所以不需要有id）
     * @param skill 需要存储到数据库中的Skill对象
     */
    void saveSkill(Skill skill);

    /**
     * 删除方法，根据技能名称来删除技能数据
     * @param name 技能名称
     */
    void deleteSkillByName(String name);

    /**
     * 删除方法，根据技能的id来删除技能数据
     * @param id 技能的id
     */
    void deleteSkillById(Integer id);

    /**
     * 删除所有的技能数据
     */
    void deleteAllSkills();

    /**
     * 删除有指定属性的技能
     * @param typeName 属性名称
     */
    void deleteSkillsByTypeName(String typeName);

    /**
     * 删除有指定分类的技能
     * @param categoryName 分类名称
     */
    void deleteSkillsByCategoryName(String categoryName);

    /**
     * 根据技能名称来更新技能数据
     * @param skill Skill对象，需要指明其name属性，且非空属性的数据才会更新到数据库中
     */
    void updateSkillByNameSelectively(Skill skill);

    /**
     * 根据技能名称来更新技能数据
     * @param skill Skill对象，需要指明其name属性，且所有属性的数据都会更新到数据库中
     */
    void updateSkillByName(Skill skill);

    /**
     * 根据技能的id来更新技能数据
     * @param skill Skill对象，需要指明其id属性，且非空属性的数据才会更新到数据库中
     */
    void updateSkillByIdSelectively(Skill skill);

    /**
     * 根据技能的id来更新技能数据
     * @param skill Skill对象，需要指明其id属性，且所有属性的数据都会更细心到数据库中
     */
    void updateSkillById(Skill skill);

    /**
     * 把技能的旧属性名改成新属性名
     * @param oldName 旧属性名
     * @param newName 新属性名
     */
    void renameSkillTypeName(@Param("oldName")String oldName, @Param("newName") String newName);

    /**
     * 把技能的旧分类名改成新分类名
     * @param oldName 旧分类名
     * @param newName 新分类名
     */
    void renameSkillCategoryName(@Param("oldName")String oldName, @Param("newName") String newName);


    /**
     * 根据技能名称查询技能数据
     * @param name 技能名称
     * @return 查询结果
     */
    Skill selectSkillByName(String name);

    /**
     * 根据技能的id查询技能数据
     * @param id 技能的id
     * @return 查询结果
     */
    Skill selectSkillById(Integer id);

    /**
     * 查询所有的技能数据
     * @return 查询结果
     */
    List<Skill> selectAllSkills();
}
