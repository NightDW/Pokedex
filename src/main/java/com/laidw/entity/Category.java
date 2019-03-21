package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装技能类型的相关信息
 */

@Getter@Setter@ToString
public class Category {
    /**
     * 类型的id，唯一标识
     */
    private Integer id;

    /**
     * 类型的名称
     */
    private String name;

    /**
     * 类型图标的url地址
     */
    private String iconUrl;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Category))
            return false;
        Category c = (Category) obj;
        return name.equals(c.getName());
    }
}
