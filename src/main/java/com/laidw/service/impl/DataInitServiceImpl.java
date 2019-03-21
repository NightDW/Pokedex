package com.laidw.service.impl;

import com.laidw.entity.*;
import com.laidw.mapper.*;
import com.laidw.service.DataInitService;
import com.laidw.spider.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * DataInitService接口的实现类，负责处理数据初始化的业务
 */

@Service
public class DataInitServiceImpl implements DataInitService {
    /**
     * 本Service需要使用到的组件，直接注入即可
     */
    @Autowired private Spider<Pokemon> pokemonSpider;
    @Autowired private Spider<Skill> skillSpider;

    /**
     * 本Service需要依赖到的各种Mapper，如何实现批量操作？
     */
    @Autowired private AbilityMapper abilityMapper;
    @Autowired private CategoryMapper categoryMapper;
    @Autowired private PokemonMapper pokemonMapper;
    @Autowired private SkillMapper skillMapper;
    @Autowired private TypeMapper typeMapper;

    /**
     * 用于存储爬虫爬取到的数据
     */
    private List<Pokemon> pokemons;
    private List<Skill> skills;

    @Override
    public void initAbilities() throws IOException{
        getPokemons();
        Set<Ability> set = new LinkedHashSet<>();
        abilityMapper.deleteAllAbilities();
        for(Pokemon pokemon : pokemons)
            set.addAll(pokemon.getAbilities());
        for(Ability ability : set)
            abilityMapper.saveAbility(ability);
    }

    @Override
    public void initCategories() throws IOException{
        getSkills();
        Set<Category> set = new LinkedHashSet<>();
        categoryMapper.deleteAllCategories();
        for(Skill skill : skills)
            set.add(skill.getCategory());
        for(Category category : set)
            categoryMapper.saveCategory(category);
    }

    @Override
    public void initPokemons() throws IOException{
        getPokemons();
        pokemonMapper.deleteAllPokemons();
        for(Pokemon pokemon : pokemons)
            pokemonMapper.savePokemon(pokemon);
    }

    @Override
    public void initSkills() throws IOException{
        getSkills();
        skillMapper.deleteAllSkills();
        for(Skill skill : skills)
            skillMapper.saveSkill(skill);
    }

    @Override
    public void initTypes() throws IOException{
        getSkills();
        getPokemons();
        Set<Type> set = new LinkedHashSet<>();
        typeMapper.deleteAllTypes();
        for(Skill skill : skills)
            set.add(skill.getType());

        //Showdown给某一只原创宝可梦赋予了Bird属性
        //所以这里还要再把所有宝可梦的属性遍历一遍，避免遗漏
        for(Pokemon pokemon : pokemons)
            set.addAll(pokemon.getTypes());

        for(Type type : set)
            typeMapper.saveType(type);
    }

    /**
     * 提供2个方法，用于执行爬虫，获取数据
     */
    private void getPokemons() throws IOException {
        if(pokemons == null)
            pokemons = pokemonSpider.run();
    }
    private void getSkills() throws IOException {
        if(skills == null)
            skills = skillSpider.run();
    }
}
