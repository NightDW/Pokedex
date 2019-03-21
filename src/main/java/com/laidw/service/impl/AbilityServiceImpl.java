package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.Ability;
import com.laidw.entity.PageBean;
import com.laidw.mapper.AbilityMapper;
import com.laidw.mapper.PokemonMapper;
import com.laidw.service.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AbilityService接口的实现类，负责宝可梦特性的增删改查操作
 */

@Service
public class AbilityServiceImpl implements AbilityService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private AbilityMapper mapper;
    @Autowired private PokemonMapper pokemonMapper;

    @Override
    public void saveAbility(Ability ability) {
        mapper.saveAbility(ability);
    }

    @Override
    public void deleteAbilityByName(String name) {
        mapper.deleteAbilityByName(name);
        pokemonMapper.deletePokemonsByAbilityName(name);
    }

    @Override
    public void deleteAbilityById(Integer id) {
        Ability ability = mapper.selectAbilityById(id);
        mapper.deleteAbilityById(id);
        pokemonMapper.deletePokemonsByAbilityName(ability.getName());
    }

    @Override
    public void deleteAllAbilities() {
        mapper.deleteAllAbilities();
        pokemonMapper.deleteAllPokemons();
    }

    @Override
    public void updateAbilityByName(Ability ability) {
        mapper.updateAbilityByName(ability);
    }

    @Override
    public void updateAbilityById(Ability ability) {
        Ability tem = mapper.selectAbilityById(ability.getId());
        mapper.updateAbilityById(ability);
        pokemonMapper.renamePokemonAbilityName(tem.getName(), ability.getName());
    }

    @Override
    public Ability selectAbilityByName(String name) {
        return mapper.selectAbilityByName(name);
    }

    @Override
    public Ability selectAbilityById(Integer id) {
        return mapper.selectAbilityById(id);
    }

    @Override
    public List<Ability> selectAllAbilities() {
        return mapper.selectAllAbilities();
    }

    @Override
    public List<Ability> selectAllAbilitiesOrderByName() {
        return mapper.selectAllAbilitiesOrderByName();
    }

    @Override
    public PageBean<Ability> selectAllAbilitiesLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Ability> page = PageHelper.startPage(pageNum, pageSize);
        List<Ability> list = mapper.selectAllAbilities();
        PageInfo<Ability> pageInfo = new PageInfo<>(list);

        PageBean<Ability> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
