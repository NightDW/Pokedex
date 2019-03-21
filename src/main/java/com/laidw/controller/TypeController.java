package com.laidw.controller;

import com.laidw.controller.properties.TypeControllerProperties;
import com.laidw.entity.PageBean;
import com.laidw.entity.Type;
import com.laidw.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责属性信息的增删改查操作的Controller
 */

@Controller
public class TypeController {
    /**
     * 注入相关的组件
     */
    @Autowired private TypeControllerProperties properties;
    @Autowired private TypeService service;

    /**
     * 用户请求获取属性数据页面时调用这个方法，分页查出所有属性数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/types/{pageNum}")
    public ModelAndView getAllTypesLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Type> pageBean = service.selectAllTypesLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/type/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的属性信息并把它展示到属性详情页上
     * @param id 要修改的属性的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/type/{id}")
    public ModelAndView selectTypeById(@PathVariable("id") Integer id, Integer curPage){
        Type type = service.selectTypeById(id);
        ModelAndView mav = new ModelAndView("/type/edit");
        mav.addObject("type", type);
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把属性详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/type")
    public ModelAndView toTypeEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/type/edit");
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户在属性详情页面提交了属性数据时调用这个方法，把数据修改到数据库中，并重定向回属性列表页面
     * @param type 用户提交的Type数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/type")
    public String updateTypeById(Type type, Integer curPage){
        System.out.println(type);
        service.updateTypeById(type);
        return "redirect:/types/" + curPage;
    }

    /**
     * 用户在属性详情页面提交了属性数据时调用这个方法，把数据保存到数据库中，并重定向回属性列表页面
     * @param type 用户提交的Type数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/type")
    public String saveType(Type type, Integer curPage){
        System.out.println(type);
        service.saveType(type);
        return "redirect:/types/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的属性数据，并重定向回属性列表页面
     * @param id 要删除的属性的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/type/{id}")
    public String deleteSkillById(@PathVariable("id") Integer id, Integer curPage){
        service.deleteTypeById(id);
        return "redirect:/types/" + curPage;
    }
}
