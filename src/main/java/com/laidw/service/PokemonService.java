package com.laidw.service;

import com.laidw.entity.PageBean;
import com.laidw.entity.Pokemon;

import java.util.List;

/**
 * 该Service负责处理宝可梦相关的业务（增删改查）
 */

public interface PokemonService {
    void savePokemon(Pokemon pokemon);

    void deletePokemonByName(String name);
    void deletePokemonById(Integer id);
    void deleteAllPokemons();

    void updatePokemonByNameSelectively(Pokemon pokemon);
    void updatePokemonByName(Pokemon pokemon);
    void updatePokemonByIdSelectively(Pokemon pokemon);
    void updatePokemonById(Pokemon pokemon);

    Pokemon selectPokemonByName(String name);
    Pokemon selectPokemonById(Integer id);
    List<Pokemon> selectAllPokemons();

    PageBean<Pokemon> selectAllPokemonsLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
