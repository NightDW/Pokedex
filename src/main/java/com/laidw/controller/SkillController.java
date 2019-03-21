package com.laidw.controller;

import com.laidw.controller.properties.SkillControllerProperties;
import com.laidw.entity.PageBean;
import com.laidw.entity.Skill;
import com.laidw.service.CategoryService;
import com.laidw.service.SkillService;
import com.laidw.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责技能信息的增删改查操作的Controller
 */

@Controller
public class SkillController {
    /**
     * 注入必要的组件
     */
    @Autowired private SkillControllerProperties properties;
    @Autowired private SkillService skillService;
    @Autowired private TypeService typeService;
    @Autowired private CategoryService categoryService;

    /**
     * 用户请求获取技能数据页面时调用这个方法，分页查出所有技能数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/skills/{pageNum}")
    public ModelAndView getAllSkillsLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Skill> pageBean = skillService.selectAllSkillsLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/skill/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的技能信息并把它展示到技能详情页上
     * @param id 要修改的技能的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/skill/{id}")
    public ModelAndView selectSkillById(@PathVariable("id") Integer id, Integer curPage){
        Skill skill = skillService.selectSkillById(id);
        ModelAndView mav = new ModelAndView("/skill/edit");
        mav.addObject("skill", skill);
        mav.addObject("curPage", curPage);
        addCategoryInfo(mav);
        addTypeInfo(mav);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把技能详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/skill")
    public ModelAndView toSkillEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/skill/edit");
        mav.addObject("curPage", curPage);
        addCategoryInfo(mav);
        addTypeInfo(mav);
        return mav;
    }

    /**
     * 用户在技能详情页面提交了技能数据时调用这个方法，把数据修改到数据库中，并重定向回技能列表页面
     * @param skill 用户提交的Skill数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/skill")
    public String updateSkillById(Skill skill, Integer curPage){
        System.out.println(skill);
        skillService.updateSkillById(skill);
        return "redirect:/skills/" + curPage;
    }

    /**
     * 用户在技能详情页面提交了技能数据时调用这个方法，把数据保存到数据库中，并重定向回技能列表页面
     * @param skill 用户提交的Skill数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/skill")
    public String saveSkill(Skill skill, Integer curPage){
        System.out.println(skill);
        skillService.saveSkill(skill);
        return "redirect:/skills/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的技能数据，并重定向回技能列表页面
     * @param id 要删除的技能的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/skill/{id}")
    public String deleteSkillById(@PathVariable("id") Integer id, Integer curPage){
        skillService.deleteSkillById(id);
        return "redirect:/skills/" + curPage;
    }

    /**
     * 两个私有的方法，把查询出来的技能分类和属性信息放到ModelAndView中
     * @param mav 需要存放相关数据的ModelAndView
     */
    private void addCategoryInfo(ModelAndView mav){
        mav.addObject("categories", categoryService.selectAllCategoriesOrderByName());
    }
    private void addTypeInfo(ModelAndView mav){
        mav.addObject("types", typeService.selectAllTypesOrderByName());
    }
}
