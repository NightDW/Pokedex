package com.laidw.controller;

import com.laidw.service.DataInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 负责处理数据初始化的业务的Controller
 */

@RestController
@RequestMapping("/data-init")
public class DataInitController {

    @Autowired
    private DataInitService service;

    /**
     * 只初始化某一个模块的数据
     * @param dataName 需要初始化的数据的名称
     * @return 成功初始化则返回成功，否则返回失败的信息
     */
    @GetMapping("/{dataName}")
    public String initData(@PathVariable("dataName") String dataName){
        dataName = dataName.toUpperCase().charAt(0) + dataName.toLowerCase().substring(1);
        try{
            service.getClass().getMethod("init" + dataName).invoke(service);
        }catch (Exception e){
            e.printStackTrace();
            return e.getClass() + " : " + e.getMessage();
        }
        return "Init " + dataName + " Successfully!";
    }

    /**
     * 初始化全部数据
     * @return 成功初始化则返回成功，否则返回失败的信息
     */
    @GetMapping("/all")
    public String initAll(){
        String s1 = initData("Abilities");
        String s2 = initData("Categories");
        String s3 = initData("Pokemons");
        String s4 = initData("Skills");
        String s5 = initData("Types");
        return s1 + "<br>" + s2 + "<br>" + s3 + "<br>" + s4 + "<br>" + s5;
    }
}
