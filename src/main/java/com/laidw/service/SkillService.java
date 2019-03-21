package com.laidw.service;

import com.laidw.entity.PageBean;
import com.laidw.entity.Skill;

import java.util.List;

/**
 * 该Service负责处理技能信息相关的业务（增删改查）
 */

public interface SkillService {
    void saveSkill(Skill skill);

    void deleteSkillByName(String name);
    void deleteSkillById(Integer id);
    void deleteAllSkills();

    void updateSkillByNameSelectively(Skill skill);
    void updateSkillByName(Skill skill);
    void updateSkillByIdSelectively(Skill skill);
    void updateSkillById(Skill skill);

    Skill selectSkillByName(String name);
    Skill selectSkillById(Integer id);
    List<Skill> selectAllSkills();
    PageBean<Skill> selectAllSkillsLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
