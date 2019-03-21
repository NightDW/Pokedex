package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.PageBean;
import com.laidw.entity.Skill;
import com.laidw.mapper.SkillMapper;
import com.laidw.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SkillService接口的实现类，负责技能信息的增删改查操作
 */

@Service
public class SkillServiceImpl implements SkillService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private SkillMapper mapper;

    @Override
    public void saveSkill(Skill skill) {
        mapper.saveSkill(skill);
    }

    @Override
    public void deleteSkillByName(String name) {
        mapper.deleteSkillByName(name);
    }

    @Override
    public void deleteSkillById(Integer id) {
        mapper.deleteSkillById(id);
    }

    @Override
    public void deleteAllSkills() {
        mapper.deleteAllSkills();
    }

    @Override
    public void updateSkillByNameSelectively(Skill skill) {
        mapper.updateSkillByNameSelectively(skill);
    }

    @Override
    public void updateSkillByName(Skill skill) {
        mapper.updateSkillByName(skill);
    }

    @Override
    public void updateSkillByIdSelectively(Skill skill) {
        mapper.updateSkillByIdSelectively(skill);
    }

    @Override
    public void updateSkillById(Skill skill) {
        mapper.updateSkillById(skill);
    }

    @Override
    public Skill selectSkillByName(String name) {
        return mapper.selectSkillByName(name);
    }

    @Override
    public Skill selectSkillById(Integer id) {
        return mapper.selectSkillById(id);
    }

    @Override
    public List<Skill> selectAllSkills() {
        return mapper.selectAllSkills();
    }

    @Override
    public PageBean<Skill> selectAllSkillsLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Skill> page = PageHelper.startPage(pageNum, pageSize);
        List<Skill> list = mapper.selectAllSkills();
        PageInfo<Skill> pageInfo = new PageInfo<>(list, navigatePages);

        PageBean<Skill> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
