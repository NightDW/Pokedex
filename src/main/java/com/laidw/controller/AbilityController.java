package com.laidw.controller;

import com.laidw.controller.properties.AbilityControllerProperties;
import com.laidw.entity.Ability;
import com.laidw.entity.PageBean;
import com.laidw.service.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责特性信息的增删改查操作的Controller
 */

@Controller
public class AbilityController {
    /**
     * 注入相关的组件
     */
    @Autowired private AbilityControllerProperties properties;
    @Autowired private AbilityService service;

    /**
     * 用户请求获取特性数据页面时调用这个方法，分页查出所有特性数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/abilities/{pageNum}")
    public ModelAndView getAllAbilitiesLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Ability> pageBean = service.selectAllAbilitiesLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/ability/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的特性信息并把它展示到特性详情页上
     * @param id 要修改的特性的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/ability/{id}")
    public ModelAndView selectAbilityById(@PathVariable("id") Integer id, Integer curPage){
        Ability ability = service.selectAbilityById(id);
        ModelAndView mav = new ModelAndView("/ability/edit");
        mav.addObject("ability", ability);
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把特性详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/ability")
    public ModelAndView toAbilityEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/ability/edit");
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户在特性详情页面提交了特性数据时调用这个方法，把数据修改到数据库中，并重定向回特性列表页面
     * @param ability 用户提交的Ability数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/ability")
    public String updateAbilityById(Ability ability, Integer curPage){
        System.out.println(ability);
        service.updateAbilityById(ability);
        return "redirect:/abilities/" + curPage;
    }

    /**
     * 用户在特性详情页面提交了特性数据时调用这个方法，把数据保存到数据库中，并重定向回特性列表页面
     * @param ability 用户提交的Ability数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/ability")
    public String saveAbility(Ability ability, Integer curPage){
        System.out.println(ability);
        service.saveAbility(ability);
        return "redirect:/abilities/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的特性数据，并重定向回特性列表页面
     * @param id 要删除的特性的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/ability/{id}")
    public String deleteAbilityById(@PathVariable("id") Integer id, Integer curPage){
        service.deleteAbilityById(id);
        return "redirect:/abilities/" + curPage;
    }
}
