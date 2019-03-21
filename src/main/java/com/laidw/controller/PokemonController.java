package com.laidw.controller;

import com.laidw.controller.properties.PokemonControllerProperties;
import com.laidw.entity.PageBean;
import com.laidw.entity.Pokemon;
import com.laidw.service.AbilityService;
import com.laidw.service.PokemonService;
import com.laidw.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 负责宝可梦信息的增删改查操作的Controller
 */

@Controller
public class PokemonController {
    /**
     * 注入必要的组件
     */
    @Autowired private PokemonControllerProperties properties;
    @Autowired private PokemonService pokemonService;
    @Autowired private TypeService typeService;
    @Autowired private AbilityService abilityService;

    /**
     * 用户请求获取宝可梦数据页面时调用这个方法，分页查出所有宝可梦数据并交给页面渲染
     * @param pageNum 表示用户要查看第几页的数据
     * @return list.html页面及其需要的数据
     */
    @GetMapping("/pokemons/{pageNum}")
    public ModelAndView getAllPokemonsLimits(@PathVariable("pageNum") Integer pageNum){
        PageBean<Pokemon> pageBean = pokemonService.selectAllPokemonsLimits(pageNum, properties.getPageSize(), properties.getNavigatePages());
        ModelAndView mav = new ModelAndView("/pokemon/list");
        mav.addObject("pageBean", pageBean);
        return mav;
    }

    /**
     * 用户点击修改按钮时调用这个方法，需要查出相应的宝可梦信息并把它展示到宝可梦详情页上
     * @param id 要修改的宝可梦的id
     * @param curPage 用户点击修改按钮时的当前页码（便于修改后重定向回原来的页面）
     * @return edit.html页面及其需要的数据
     */
    @GetMapping("/pokemon/{id}")
    public ModelAndView selectPokemonById(@PathVariable("id") Integer id, Integer curPage){
        Pokemon pokemon = pokemonService.selectPokemonById(id);
        ModelAndView mav = new ModelAndView("/pokemon/edit");
        mav.addObject("pokemon", pokemon);
        mav.addObject("curPage", curPage);
        addAbilityInfo(mav);
        addTypeInfo(mav);
        return mav;
    }

    /**
     * 用户点击添加按钮时调用这个方法，直接把宝可梦详情页面返回即可
     * @param curPage 用户点击添加按钮时的当前页码（便于添加后重定向回原来的页面）
     * @return edit.html页面
     */
    @GetMapping("/pokemon")
    public ModelAndView toPokemonEditPage(Integer curPage){
        ModelAndView mav = new ModelAndView("/pokemon/edit");
        mav.addObject("curPage", curPage);
        addTypeInfo(mav);
        addAbilityInfo(mav);
        return mav;
    }

    /**
     * 用户在宝可梦详情页面提交了宝可梦数据时调用这个方法，把数据修改到数据库中，并重定向回宝可梦列表页面
     * @param pokemon 用户提交的Pokemon数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PutMapping("/pokemon")
    public String updatePokemonById(Pokemon pokemon, Integer curPage){
        System.out.println(pokemon);
        pokemonService.updatePokemonById(pokemon);
        return "redirect:/pokemons/" + curPage;
    }

    /**
     * 用户在宝可梦详情页面提交了宝可梦数据时调用这个方法，把数据保存到数据库中，并重定向回宝可梦列表页面
     * @param pokemon 用户提交的Pokemon数据
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @PostMapping("/pokemon")
    public String savePokemon(Pokemon pokemon, Integer curPage){
        System.out.println(pokemon);
        pokemonService.savePokemon(pokemon);
        return "redirect:/pokemons/" + curPage;
    }

    /**
     * 用户点击删除按钮时调用这个方法，删除相应的宝可梦数据，并重定向回宝可梦列表页面
     * @param id 要删除的宝可梦的id
     * @param curPage 用于重定向回原来的页面
     * @return list.html页面
     */
    @DeleteMapping("/pokemon/{id}")
    public String deletePokemonById(@PathVariable("id") Integer id, Integer curPage){
        pokemonService.deletePokemonById(id);
        return "redirect:/pokemons/" + curPage;
    }

    /**
     * 两个私有的方法，把查询出来的宝可梦特性和属性信息放到ModelAndView中
     * @param mav 需要存放相关数据的ModelAndView
     */
    private void addAbilityInfo(ModelAndView mav){
        mav.addObject("abilities", abilityService.selectAllAbilitiesOrderByName());
    }
    private void addTypeInfo(ModelAndView mav){
        mav.addObject("types", typeService.selectAllTypesOrderByName());
    }
}
