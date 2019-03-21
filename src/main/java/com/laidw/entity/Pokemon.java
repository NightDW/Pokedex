package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于封装宝可梦的主要信息
 */

@Getter@Setter
public class Pokemon {
    /**
     * 宝可梦的id，唯一标识
     */
    private Integer id;

    ///**
    // * 宝可梦图标的url地址
    // */
    //private String iconUrl;

    /**
     * 宝可梦的名称
     */
    private String name;

    /**
     * 宝可梦的属性，数量为1-2个
     */
    private List<Type> types;

    /**
     * 宝可梦的特性，数量为1-3个
     * 当数量为1个时，该特性是普通特性
     * 当数量为2个时，这2个特性分别普通特性和隐藏特性
     * 当数量为3个时，这3个特性分别为第一特性，第二特性，隐藏特性
     */
    private List<Ability> abilities;

    /**
     * 宝可梦的各项种族值（HP，物攻，物防，特攻，特防，速度）
     */
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer spa;
    private Integer spd;
    private Integer spe;

    /**
     * 额外提供一个set()方法，方便一次性设置6项种族值
     * @param other 要求是元素个数为6的Integer的数组，各个元素分别代表HP，物攻，物防，特攻，特防，速度的种族值
     */
    public void setOther(Integer...other){
        hp = other[0]; atk = other[1]; def = other[2]; spa = other[3]; spd = other[4]; spe = other[5];
    }

    /**
     * 提供一些简单的方法来设置宝可梦的属性（把结果集封装成实体类对象时会用到这些方法）
     * @param type 要设置的属性
     */
    public void setType1(Type type){
        if(type == null)
            return;
        if(types != null && types.size() != 0)
            throw new RuntimeException("setTypeX()方法只能在特定条件下调用，请检查您是否滥用了这个方法！");
        types = new ArrayList<>();
        types.add(type);
    }
    public void setType2(Type type){
        if(type == null)
            return;
        if(types == null || types.size() != 1)
            throw new RuntimeException("setTypeX()方法只能在特定条件下调用，请检查您是否滥用了这个方法！");
        types.add(type);
    }

    /**
     * 提供一些简单的方法来设置宝可梦的特性（把结果集封装成实体类对象是会用到这些方法）
     * @param ability 要设置的特性
     */
    public void setAbility1(Ability ability){
        if(ability == null)
            return;
        if(abilities != null && types.size() != 0)
            throw new RuntimeException("setAbilityX()方法只能在特定条件下调用，请检查您是否滥用了这个方法！");
        abilities = new ArrayList<>();
        abilities.add(ability);
    }
    public void setAbility2(Ability ability){
        if(ability == null)
            return;
        if(abilities == null || abilities.size() != 1)
            throw new RuntimeException("setAbilityX()方法只能在特定条件下调用，请检查您是否滥用了这个方法！");
        abilities.add(ability);
    }
    public void setAbility3(Ability ability){
        if(ability == null)
            return;
        if(abilities == null || abilities.size() != 2)
            throw new RuntimeException("setAbilityX()方法只能在特定条件下调用，请检查您是否滥用了这个方法！");
        abilities.add(ability);
    }

    /**
     * 重写toString()方法，便于查看Pokemon对象的具体信息
     * @return 该Pokemon对象的具体信息
     */
    public String toString() {
        String ln = System.lineSeparator();
        String str = "Pokemon {" + ln;
        //str += "\ticon_url = " + iconUrl + ln;
        str += "\tid = " + id + ln;
        str += "\tname = " + name + ln;
        str += "\ttypes = " + types + ln;
        str += "\tabilities = " + abilities + ln;
        str += "\tothers = " + getOtherString() + ln + "}";
        return str;
    }

    /**
     * 一些私有的方法，可以考虑改成公共的，方便外界调用
     * @return 相应的字符串
     */
    private String getOtherString(){
        return hp + "," + atk + "," + def + "," + spa + "," + spd + "," + spe;
    }
}
