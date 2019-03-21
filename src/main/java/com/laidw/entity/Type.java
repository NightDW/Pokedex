package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装宝可梦属性的相关信息
 */

@Getter@Setter@ToString
public class Type {
    /**
     * 属性的id，唯一标识
     */
    private Integer id;

    /**
     * 属性的名称
     */
    private String name;

    /**
     * 属性图标的url地址
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
        if(!(obj instanceof Type))
            return false;
        Type t = (Type) obj;
        return name.equals(t.getName());
    }
}
