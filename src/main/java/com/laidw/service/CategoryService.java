package com.laidw.service;

import com.laidw.entity.Category;
import com.laidw.entity.PageBean;

import java.util.List;

/**
 * 该Service负责处理技能类型相关的业务（增删改查）
 */

public interface CategoryService {
    void saveCategory(Category category);

    void deleteCategoryByName(String name);
    void deleteCategoryById(Integer id);
    void deleteAllCategories();

    void updateCategoryByName(Category category);
    void updateCategoryById(Category category);

    Category selectCategoryByName(String name);
    Category selectCategoryById(Integer id);
    List<Category> selectAllCategories();
    List<Category> selectAllCategoriesOrderByName();
    PageBean<Category> selectAllCategoriesLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
