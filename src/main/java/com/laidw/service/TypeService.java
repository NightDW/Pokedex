package com.laidw.service;

import com.laidw.entity.PageBean;
import com.laidw.entity.Type;

import java.util.List;

/**
 * 该Service负责处理宝可梦属性相关的业务（增删改查）
 */

public interface TypeService {
    void saveType(Type type);

    void deleteTypeByName(String name);
    void deleteTypeById(Integer id);
    void deleteAllTypes();

    void updateTypeByName(Type type);
    void updateTypeById(Type type);

    Type selectTypeByName(String name);
    Type selectTypeById(Integer id);
    List<Type> selectAllTypes();
    List<Type> selectAllTypesOrderByName();
    PageBean<Type> selectAllTypesLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
