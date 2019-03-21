package com.laidw.controller;

import com.laidw.controller.properties.CategoryControllerProperties;
import com.laidw.entity.Category;
import com.laidw.entity.PageBean;
import com.laidw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责分类信息的增删改查操作的Controller
 */

@Controller
public class CategoryController {
    /**
     * 注入相关的组件
     */
    @Autowired private CategoryControllerProperties properties;
    @Autowired private CategoryService service;

    /**
     * 用户请求获取分类数据页面时调用这个方法，分页查出所有分类数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/categories/{pageNum}")
    public ModelAndView getAllCategoriesLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Category> pageBean = service.selectAllCategoriesLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/category/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的分类信息并把它展示到分类详情页上
     * @param id 要修改的分类的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/category/{id}")
    public ModelAndView selectCategoryById(@PathVariable("id") Integer id, Integer curPage){
        Category category = service.selectCategoryById(id);
        ModelAndView mav = new ModelAndView("/category/edit");
        mav.addObject("category", category);
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把分类详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/category")
    public ModelAndView toCategoryEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/category/edit");
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户在分类详情页面提交了分类数据时调用这个方法，把数据修改到数据库中，并重定向回分类列表页面
     * @param category 用户提交的Category数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/category")
    public String updateCategoryById(Category category, Integer curPage){
        System.out.println(category);
        service.updateCategoryById(category);
        return "redirect:/categories/" + curPage;
    }

    /**
     * 用户在分类详情页面提交了分类数据时调用这个方法，把数据保存到数据库中，并重定向回分类列表页面
     * @param category 用户提交的Category数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/category")
    public String saveCategory(Category category, Integer curPage){
        System.out.println(category);
        service.saveCategory(category);
        return "redirect:/categories/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的分类数据，并重定向回分类列表页面
     * @param id 要删除的分类的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/category/{id}")
    public String deleteCategoryById(@PathVariable("id") Integer id, Integer curPage){
        service.deleteCategoryById(id);
        return "redirect:/categories/" + curPage;
    }
}
