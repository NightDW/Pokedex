package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装宝可梦特性的相关信息
 */

@Getter@Setter@ToString
public class Ability {
    /**
     * 特性的id，唯一标识
     */
    private Integer id;

    /**
     * 特性的名称
     */
    private String name;

    /**
     * 特性的具体描述信息
     */
    private String description;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Ability))
            return false;
        Ability a = (Ability) obj;
        return name.equals(a.getName());
    }
}
