package com.laidw.controller;

import com.laidw.controller.properties.RoleControllerProperties;
import com.laidw.entity.PageBean;
import com.laidw.entity.Role;
import com.laidw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责角色信息的增删改查操作的Controller
 */

@Controller
public class RoleController {
    /**
     * 注入相关的组件
     */
    @Autowired private RoleControllerProperties properties;
    @Autowired private RoleService service;

    /**
     * 用户请求获取角色数据页面时调用这个方法，分页查出所有角色数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/roles/{pageNum}")
    public ModelAndView getAllRolesLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Role> pageBean = service.selectAllRolesLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/role/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的角色信息并把它展示到角色详情页上
     * @param id 要修改的角色的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/role/{id}")
    public ModelAndView selectRoleById(@PathVariable("id") Integer id, Integer curPage){
        Role role = service.selectRoleById(id);
        ModelAndView mav = new ModelAndView("/role/edit");
        mav.addObject("role", role);
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把角色详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/role")
    public ModelAndView toRoleEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/role/edit");
        mav.addObject("curPage", curPage);
        return mav;
    }

    /**
     * 用户在角色详情页面提交了角色数据时调用这个方法，把数据修改到数据库中，并重定向回角色列表页面
     * @param role 用户提交的Role数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/role")
    public String updateRoleById(Role role, Integer curPage){
        System.out.println(role);
        service.updateRoleById(role);
        return "redirect:/roles/" + curPage;
    }

    /**
     * 用户在角色详情页面提交了角色数据时调用这个方法，把数据保存到数据库中，并重定向回角色列表页面
     * @param role 用户提交的Role数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/role")
    public String saveRole(Role role, Integer curPage){
        System.out.println(role);
        service.saveRole(role);
        return "redirect:/roles/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的角色数据，并重定向回角色列表页面
     * @param id 要删除的角色的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/role/{id}")
    public String deleteRoleById(@PathVariable("id") Integer id, Integer curPage){
        service.deleteRoleById(id);
        return "redirect:/roles/" + curPage;
    }
}
