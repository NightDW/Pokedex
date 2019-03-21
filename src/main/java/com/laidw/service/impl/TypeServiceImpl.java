package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.PageBean;
import com.laidw.entity.Type;
import com.laidw.mapper.PokemonMapper;
import com.laidw.mapper.SkillMapper;
import com.laidw.mapper.TypeMapper;
import com.laidw.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TypeService接口的实现类，负责宝可梦属性的增删改查操作
 */

@Service
public class TypeServiceImpl implements TypeService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private TypeMapper mapper;
    @Autowired private PokemonMapper pokemonMapper;
    @Autowired private SkillMapper skillMapper;

    @Override
    public void saveType(Type type) {
        mapper.saveType(type);
    }

    @Override
    public void deleteTypeByName(String name) {
        mapper.deleteTypeByName(name);
        pokemonMapper.deletePokemonsByTypeName(name);
        skillMapper.deleteSkillsByTypeName(name);
    }

    @Override
    public void deleteTypeById(Integer id) {
        Type type = mapper.selectTypeById(id);
        mapper.deleteTypeById(id);
        pokemonMapper.deletePokemonsByTypeName(type.getName());
        skillMapper.deleteSkillsByTypeName(type.getName());
    }

    @Override
    public void deleteAllTypes() {
        mapper.deleteAllTypes();
        pokemonMapper.deleteAllPokemons();
        skillMapper.deleteAllSkills();
    }

    @Override
    public void updateTypeByName(Type type) {
        mapper.updateTypeByName(type);
    }

    @Override
    public void updateTypeById(Type type) {
        Type tem = mapper.selectTypeById(type.getId());
        mapper.updateTypeById(type);
        pokemonMapper.renamePokemonTypeName(tem.getName(), type.getName());
        skillMapper.renameSkillTypeName(tem.getName(), type.getName());
    }

    @Override
    public Type selectTypeByName(String name) {
        return mapper.selectTypeByName(name);
    }

    @Override
    public Type selectTypeById(Integer id) {
        return mapper.selectTypeById(id);
    }

    @Override
    public List<Type> selectAllTypes() {
        return mapper.selectAllTypes();
    }

    @Override
    public List<Type> selectAllTypesOrderByName() {
        return mapper.selectAllTypesOrderByName();
    }

    @Override
    public PageBean<Type> selectAllTypesLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Type> page = PageHelper.startPage(pageNum, pageSize);
        List<Type> list = mapper.selectAllTypes();
        PageInfo<Type> pageInfo = new PageInfo<>(list);

        PageBean<Type> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
