package com.laidw.controller;

import com.laidw.entity.Account;
import com.laidw.service.AccountService;
import com.laidw.service.MailService;
import com.laidw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 负责一些基本功能的Controller，比如登录，注册，找回密码等
 */

@Controller
public class BaseController {
    /**
     * 注入相关的组件
     */
    @Autowired private AccountService accountService;
    @Autowired private RoleService roleService;
    @Autowired private MailService mailService;

    /**
     * 负责返回首页
     * @return index.html页面
     */
    @GetMapping({"/", "/index"})
    public String toIndexPage(){
        return "base/index";
    }

    /**
     * 负责返回注册页面
     * @return registry.html页面及其需要的数据
     */
    @GetMapping("/registry")
    public ModelAndView toRegistryPage(){
        ModelAndView mav = new ModelAndView("base/registry");
        mav.addObject("roles", roleService.selectAllRolesOrderByName());
        return mav;
    }

    /**
     * 负责返回登录页面
     * @return login.html页面
     */
    @GetMapping("/login")
    public String toLoginPage(){
        return "base/login";
    }

    /**
     * 负责处理注册账户的请求；注意，此步骤完成后，账户信息会存到数据库，但尚未激活；激活需要验证邮箱
     * @param account 需要注册的账户
     * @return 一条提示信息，提醒用户激活账户
     */
    @ResponseBody
    @PostMapping(value="/registry", produces="text/html;charset=utf-8")
    public String doRegistry(Account account, HttpServletRequest request){
        //生成验证码
        String verifyCode = UUID.randomUUID().toString();

        //把用户数据（包括验证码）存到数据库中
        account.setIsActive(false);
        account.setVerifyCode(verifyCode);
        try{
            accountService.saveAccount(account);
        }catch (Exception e){
            return "注册失败！" + e.toString();
        }

        //发送模板邮件；由于模板不能解析@{}表达式
        //所以不能直接给模板传递verifyCode和account_id参数
        //只能自己把链接拼接起来传递给模板，让模板直接取出
        String requestUrl = request.getRequestURL().toString();
        String projectUrl = requestUrl.substring(0, requestUrl.lastIndexOf('/') + 1);
        String verifyUrl = projectUrl + "verify?verifyCode=" + verifyCode + "&account_id=" + account.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("projectUrl", projectUrl);
        map.put("verifyUrl", verifyUrl);
        try {
            mailService.sendTemplateMail(account.getEmail(), "验证邮件", "base/verify", map);
        }catch (Exception e){
            return "发送验证邮件时出错，请稍后重试！";
        }
        return "账户注册成功！但该账户需要激活后才可以正常使用！激活链接已发送至绑定邮箱！";
    }

    /**
     * 负责处理激活账户的请求，通过校验验证码来确定是否激活
     * @param verifyCode 用户发送的验证码
     * @return 验证成功则转发到登录页面；验证失败则转发到注册页面
     */
    @GetMapping("/verify")
    public ModelAndView doVerify(String verifyCode, Integer account_id){
        ModelAndView mav = new ModelAndView();

        /*
         * 注意，添加账户的方式有2种：1是通过网页注册，2是由超级管理员自己添加
         * 如果是通过网页注册的，那么该账户的VerifyCode必定不为空
         * 如果是由超级管理员自己添加的，那么该账户就没有VerifyCode了
         */
        Account account = accountService.selectAccountById(account_id);
        String trueCode = account.getVerifyCode();

        /*
         * 如果数据库中没有验证码的数据，说明这条记录是超级管理员自己添加的
         * 此时只需判断这个账户是否已经激活即可；这段代码基本不会执行到
         * 超级管理员总不会自己添加了账户后又多此一举地给这个账户进行校验吧
         */
        if(trueCode == null){
            mav.addObject("account", account);
            if(account.getIsActive()){
                mav.setViewName("base/login");
                mav.addObject("msg", "该账户无需验证，且已被超级管理员激活！");
            }else{
                mav.setViewName("forward:/registry");
                mav.addObject("msg", "该账户无需验证，请直接找超级管理员激活！");
            }
        }

        /*
         * 如果两个验证码相同，则验证成功；激活这个账户，把账户信息保存到Request域中并转发到登录页面
         */
        else if(trueCode.equals(verifyCode)){
            accountService.activateAccountById(account_id);
            mav.setViewName("base/login");
            mav.addObject("msg", "验证成功！");
            mav.addObject("account", account);
        }

        /*
         * 如果验证失败，则转发到注册页面
         */
        else{
            mav.setViewName("forward:/registry");
            mav.addObject("msg","验证失败，请检查注册信息或联系作者！");
            mav.addObject("account", account);
        }

        return mav;
    }

    /**
     * 用于在用户注册时检测用户名是否已存在
     * @return 用户名尚未注册时返回Null，否则返回提示信息
     */
    @ResponseBody
    @GetMapping("/check/name")
    public String checkName(String name){
        if(accountService.checkIfNameHasExisted(name))
            return "用户名已存在！";
        return null;
    }

    /**
     * 用于在用户注册时检测邮箱是否已绑定
     * @return 邮箱尚未被注册时返回Null，否则返回提示信息
     */
    @ResponseBody
    @GetMapping("/check/email")
    public String checkEmail(String email){
        if(accountService.checkIfEmailHasExisted(email))
            return "邮箱已被绑定！";
        return null;
    }

    /**
     * 用于在用户修改个人信息时检测用户名是否已存在
     * @return 用户名尚未注册时返回Null，否则返回提示信息
     */
    @ResponseBody
    @GetMapping("/check/name-not-id")
    public String checkNameNotId(String name, Integer id){
        if(accountService.selectAccountByNameAndNotId(name, id) != null)
            return "用户名已存在！";
        return null;
    }

    /**
     * 用于在用户注册时检测邮箱是否已绑定
     * @return 邮箱尚未被注册时返回Null，否则返回提示信息
     */
    @ResponseBody
    @GetMapping("/check/email-not-id")
    public String checkEmailNotId(String email, Integer id){
        if(accountService.selectAccountByEmailAndNotId(email, id) != null)
            return "邮箱已被绑定！";
        return null;
    }

    /**
     * 用于返回找回密码页面
     * @return forget.html页面
     */
    @GetMapping("/forget")
    public String toForgetPage(){
        return "base/forget";
    }

    /**
     * 负责处理找回密码业务的方法
     * @param name 用户提交的账户名，优先级比邮箱高
     * @param email 用户提交的邮箱
     * @return 处理完成的提示信息
     */
    @ResponseBody
    @PostMapping("/forget")
    public String doFindBack(@RequestParam(required=false) String name, @RequestParam(required=false) String email){
        //注意，name和email这两个参数必定不为空
        if(name.isEmpty() && email.isEmpty())
            return "请填写用户名或邮箱名！";

        Account account;
        if(!name.isEmpty()) account = accountService.selectAccountByName(name);
        else account = accountService.selectAccountByEmail(email);

        if(account == null)
            return "找不到此用户！";

        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        try{
            mailService.sendTemplateMail(account.getEmail(), "找回密码", "base/back", map);
        }catch (Exception e){
            return "发送邮件失败，请稍后重试或联系作者！";
        }
        return "账户信息已成功发送至该账号绑定的邮箱，请前往邮箱查看！";
    }

    /**
     * 负责返回修改个人信息页面
     * @return modify.html
     */
    @GetMapping("/modify")
    public ModelAndView toModifyPage(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        Account  account = accountService.selectAccountByName(name);
        ModelAndView mav = new ModelAndView("base/modify");
        mav.addObject("account", account);
        mav.addObject("roles", roleService.selectAllRolesOrderByName());
        return mav;
    }

    @PostMapping("/modify")
    public ModelAndView doModify(Account account, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Integer id = account.getId();
        Account oldAccount = accountService.selectAccountById(id);
        if(!oldAccount.getEmail().equals(account.getEmail())){
            //更换了绑定邮箱，需要先把该账户锁定
            account.setIsActive(false);
            //还要生成新的验证码
            String verifyCode = UUID.randomUUID().toString();
            account.setVerifyCode(verifyCode);

            try{
                accountService.updateAccountById(account);
            }catch (Exception e){
                mav.addObject("e", e);
                mav.addObject("msg", "修改失败！请检查用户名或邮箱是否已被注册！");
                mav.setViewName("error/error");
                return mav;
            }

            //发送模板邮件
            String requestUrl = request.getRequestURL().toString();
            String projectUrl = requestUrl.substring(0, requestUrl.lastIndexOf('/') + 1);
            String verifyUrl = projectUrl + "verify?verifyCode=" + verifyCode + "&account_id=" + account.getId();
            Map<String, Object> map = new HashMap<>();
            map.put("projectUrl", projectUrl);
            map.put("verifyUrl", verifyUrl);
            try {
                mailService.sendTemplateMail(account.getEmail(), "验证邮件", "base/verify", map);
            }catch (Exception e){
                mav.addObject("e", e);
                mav.addObject("msg", "发送验证邮件时出错，请稍后重试!");
                mav.setViewName("error/error");
                return mav;
            }
            mav.addObject("msg", "修改成功！但该账户需要激活后才可以正常使用！激活链接已发送至绑定邮箱！");
            mav.setViewName("base/auto-logout");
            return mav;
        }
        account.setIsActive(true);
        try{
            accountService.updateAccountById(account);
        }catch (Exception e){
            mav.addObject("e", e);
            mav.addObject("msg", "修改失败，请检查用户名是否已经被注册了！");
            mav.setViewName("error/error");
            return mav;
        }
        mav.addObject("msg", "修改成功！");
        mav.setViewName("base/auto-logout");
        return mav;
    }
}
