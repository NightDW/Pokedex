package com.laidw.service;

import java.io.IOException;

/**
 * 该Service负责执行爬虫，并把获取到的数据存储到数据库中
 */

public interface DataInitService {
    /**
     * 把特性的数据存储到数据库中
     */
    void initAbilities() throws IOException;

    /**
     * 把技能类型的数据存储到数据库中
     */
    void initCategories() throws IOException;

    /**
     * 把宝可梦的数据存储到数据库中
     */
    void initPokemons() throws IOException;

    /**
     * 把技能的数据存储到数据库中
     */
    void initSkills() throws IOException;

    /**
     * 把宝可梦属性的数据存储到数据库中
     */
    void initTypes() throws IOException;
}
