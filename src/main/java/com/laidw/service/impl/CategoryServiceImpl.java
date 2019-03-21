package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.Category;
import com.laidw.entity.PageBean;
import com.laidw.mapper.CategoryMapper;
import com.laidw.mapper.SkillMapper;
import com.laidw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService接口的实现类，负责技能类型的增删改查操作
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private CategoryMapper mapper;
    @Autowired private SkillMapper skillMapper;

    @Override
    public void saveCategory(Category category) {
        mapper.saveCategory(category);
    }

    @Override
    public void deleteCategoryByName(String name) {
        mapper.deleteCategoryByName(name);
        skillMapper.deleteSkillsByCategoryName(name);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = mapper.selectCategoryById(id);
        mapper.deleteCategoryById(id);
        skillMapper.deleteSkillsByCategoryName(category.getName());
    }

    @Override
    public void deleteAllCategories() {
        mapper.deleteAllCategories();
        skillMapper.deleteAllSkills();
    }

    @Override
    public void updateCategoryByName(Category category) {
        mapper.updateCategoryByName(category);
    }

    @Override
    public void updateCategoryById(Category category) {
        Category tem = mapper.selectCategoryById(category.getId());
        mapper.updateCategoryById(category);
        skillMapper.renameSkillCategoryName(tem.getName(), category.getName());
    }

    @Override
    public Category selectCategoryByName(String name) {
        return mapper.selectCategoryByName(name);
    }

    @Override
    public Category selectCategoryById(Integer id) {
        return mapper.selectCategoryById(id);
    }

    @Override
    public List<Category> selectAllCategories() {
        return mapper.selectAllCategories();
    }

    @Override
    public List<Category> selectAllCategoriesOrderByName() {
        return mapper.selectAllCategoriesOrderByName();
    }

    @Override
    public PageBean<Category> selectAllCategoriesLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Category> page = PageHelper.startPage(pageNum, pageSize);
        List<Category> list = mapper.selectAllCategories();
        PageInfo<Category> pageInfo = new PageInfo<>(list);

        PageBean<Category> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
