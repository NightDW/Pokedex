package com.laidw.mapper;

import com.laidw.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用于操作Category的Mapper接口；由于比较简单，所以使用注解方式开发
 */

@Mapper
public interface CategoryMapper {
    @Insert("INSERT INTO category(name, icon_url) VALUES(#{name}, #{iconUrl})")
    void saveCategory(Category category);

    @Delete("DELETE FROM category WHERE name = #{name}")
    void deleteCategoryByName(String name);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategoryById(Integer id);

    @Delete("DELETE FROM category")
    void deleteAllCategories();

    @Update("UPDATE category SET icon_url = #{iconUrl} WHERE name = #{name}")
    void updateCategoryByName(Category category);

    @Update("UPDATE category SET name = #{name}, icon_url = #{iconUrl} WHERE id = #{id}")
    void updateCategoryById(Category category);

    @Select("SELECT * FROM category WHERE name = #{name}")
    Category selectCategoryByName(String name);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectCategoryById(Integer id);

    @Select("SELECT * FROM category")
    List<Category> selectAllCategories();

    @Select("SELECT * FROM category ORDER BY name")
    List<Category> selectAllCategoriesOrderByName();
}
