package com.laidw.controller;

import com.laidw.controller.properties.AccountControllerProperties;
import com.laidw.entity.Account;
import com.laidw.entity.PageBean;
import com.laidw.service.AccountService;
import com.laidw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责账户信息的增删改查操作的Controller
 */

@Controller
public class AccountController {
    /**
     * 注入相关的组件
     */
    @Autowired private AccountControllerProperties properties;
    @Autowired private AccountService service;
    @Autowired private RoleService roleService;

    /**
     * 用户请求获取账户数据页面时调用这个方法，分页查出所有账户数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/accounts/{pageNum}")
    public ModelAndView getAllAccountsLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Account> pageBean = service.selectAllAccountsLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/account/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的账户信息并把它展示到账户详情页上
     * @param id 要修改的账户的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/account/{id}")
    public ModelAndView selectAccountById(@PathVariable("id") Integer id, Integer curPage){
        Account account = service.selectAccountById(id);
        ModelAndView mav = new ModelAndView("/account/edit");
        mav.addObject("account", account);
        mav.addObject("curPage", curPage);
        addRoleInfo(mav);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把账户详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/account")
    public ModelAndView toAccountEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/account/edit");
        mav.addObject("curPage", curPage);
        addRoleInfo(mav);
        return mav;
    }

    /**
     * 用户在账户详情页面提交了账户数据时调用这个方法，把数据修改到数据库中，并重定向回账户列表页面
     * @param account 用户提交的Account数据
     * @param curPage 用于重定向回原来的页面
     * @param model 当用户数据出错时需要靠它来传输数据
     * @return list.html页面
     */
    @PutMapping("/account")
    public String updateAccountById(Account account, Integer curPage, Model model){
        System.out.println(account);
        try{
            service.updateAccountById(account);
        }catch (Exception e){
            model.addAttribute("e", new Exception("用户名或密码重复了！"));
            return "error/error";
        }
        return "redirect:/accounts/" + curPage;
    }

    /**
     * 用户在账户详情页面提交了账户数据时调用这个方法，把数据保存到数据库中，并重定向回账户列表页面
     * @param account 用户提交的Account数据
     * @param curPage 用于重定向回原来的页面
     * @param model 当用户数据出错是需要靠它来传输数据
     * @return list.html页面
     */
    @PostMapping("/account")
    public String saveAccount(Account account, Integer curPage, Model model){
        System.out.println(account);
        try{
            service.saveAccount(account);
        }catch (Exception e){
            model.addAttribute("e", e);
            return "error/error";
        }
        return "redirect:/accounts/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的账户数据，并重定向回账户列表页面
     * @param id 要删除的账户的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/account/{id}")
    public String deleteAccountById(@PathVariable("id") Integer id, Integer curPage){
        service.deleteAccountById(id);
        return "redirect:/accounts/" + curPage;
    }

    /**
     * 一个私有的方法，把所有的角色查询出来并存放到ModelAndView中
     * @param mav 需要存放数据的ModelAndView
     */
    private void addRoleInfo(ModelAndView mav){
        mav.addObject("roles", roleService.selectAllRolesOrderByName());
    }
}
