package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.PageBean;
import com.laidw.entity.Pokemon;
import com.laidw.mapper.PokemonMapper;
import com.laidw.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PokemonService接口的实现类，负责宝可梦信息的增删改查操作
 */

@Service
public class PokemonServiceImpl implements PokemonService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private PokemonMapper mapper;

    @Override
    public void savePokemon(Pokemon pokemon) {
        mapper.savePokemon(pokemon);
    }

    @Override
    public void deletePokemonByName(String name) {
        mapper.deletePokemonByName(name);
    }

    @Override
    public void deletePokemonById(Integer id) {
        mapper.deletePokemonById(id);
    }

    @Override
    public void deleteAllPokemons() {
        mapper.deleteAllPokemons();
    }

    @Override
    public void updatePokemonByNameSelectively(Pokemon pokemon) {
        mapper.updatePokemonByNameSelectively(pokemon);
    }

    @Override
    public void updatePokemonByName(Pokemon pokemon) {
        mapper.updatePokemonByName(pokemon);
    }

    @Override
    public void updatePokemonByIdSelectively(Pokemon pokemon) {
        mapper.updatePokemonByIdSelectively(pokemon);
    }

    @Override
    public void updatePokemonById(Pokemon pokemon) {
        mapper.updatePokemonById(pokemon);
    }

    @Override
    public Pokemon selectPokemonByName(String name) {
        return mapper.selectPokemonByName(name);
    }

    @Override
    public Pokemon selectPokemonById(Integer id) {
        return mapper.selectPokemonById(id);
    }

    @Override
    public List<Pokemon> selectAllPokemons() {
        return mapper.selectAllPokemons();
    }

    @Override
    public PageBean<Pokemon> selectAllPokemonsLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Pokemon> page = PageHelper.startPage(pageNum, pageSize);
        List<Pokemon> list = mapper.selectAllPokemons();
        PageInfo<Pokemon> pageInfo = new PageInfo<>(list, navigatePages);

        PageBean<Pokemon> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPage(page);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
