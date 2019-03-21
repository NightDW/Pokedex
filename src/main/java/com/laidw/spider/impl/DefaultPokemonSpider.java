package com.laidw.spider.impl;

import com.laidw.entity.Ability;
import com.laidw.entity.Pokemon;
import com.laidw.entity.Type;
import com.laidw.spider.properties.PokemonSpiderProperties;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Showdown&lt;Pokemon&gt;的默认子类
 * 也是默认的宝可梦信息爬虫类
 */

@Component
public class DefaultPokemonSpider extends ShowdownSpider<Pokemon> {
    @Autowired
    private PokemonSpiderProperties properties;

    @Override
    protected String getUrl() {
        return properties.getWebUrl();
    }

    @Override
    protected String getLoadedMsg() {
        return properties.getPokemonsLoadedMsg();
    }

    @Override
    protected String getUlSelector() {
        return properties.getUlSelector();
    }

    @Override
    protected String getInvalidLiTagMsg() {
        return properties.getInvalidliTagMsg();
    }

    /**
     * 用于解析li标签，把其中的数据封装成Pokemon对象
     * @param li li标签
     * @return Pokemon对象
     */
    protected Pokemon getObjectInternal(Element li) {
        Element a = li.selectFirst("a[href]");

        //每个宝可梦的信息都封装在这些span标签中
        //注意select()方法会把span子标签中的span子标签也选出来
        //所以这里应该用children()方法，防止选出子子标签
        Elements spans = a.children();

        //用于封装数据的Pokemon对象
        Pokemon pokemon = new Pokemon();

        //给Pokemon对象的各个属性赋值
        //pokemon.setIconUrl(getIconUrl(spans.get(1)));
        pokemon.setName(getName(spans.get(2)));
        pokemon.setTypes(getTypes(spans.get(3)));
        pokemon.setAbilities(getAbilities(spans.get(4), spans.get(5)));
        pokemon.setOther(getOther(a.select("span.col.statcol")));

        return pokemon;
    }

    ///**
    // * 用于获取宝可梦图标的url地址
    // * 给出一个span标签，它内部还有一个span标签；假设element为父元素
    // * 那么element.selectFirst("span")方法返回的依旧是父元素
    // * 而element.child(0)方法才是返回子元素，要特别注意
    // * @param element li标签的第2个span子标签；这个span标签内还有一个span标签，后者包含地址数据
    // * @return 宝可梦图标的url地址
    // */
    //private String getIconUrl(Element element){
    //    String styleStr = element.child(0).attr("style");
    //    return styleStr.substring(styleStr.indexOf('(') + 1 , styleStr.indexOf(')'));
    //}

    /**
     * 用于获取宝可梦的名称
     * @param element li标签的第3个span子标签，它的Text内容就是宝可梦的名称
     * @return 宝可梦的名称
     */
    private String getName(Element element){
        String name = element.text();

        //对于Mega或阿罗拉形态的宝可梦，此时element代表<span>"Xxx"<small>-Xxx</small></span>
        //此时需要对得到的文本内容，即element.text()进行裁剪
        return name.replaceAll("\"", "").replaceAll(" ", "");
    }

    /**
     * 用于获取宝可梦的属性
     * @param element li标签的第4个span子标签；它有几个img子标签就代表该宝可梦有几个属性
     * @return 宝可梦的属性
     */
    private List<Type> getTypes(Element element){
        List<Type> types = new ArrayList<>();
        Elements imgs = element.children();
        for(Element img: imgs){
            Type type = new Type();
            type.setIconUrl(img.attr("src"));
            type.setName(img.attr("alt"));
            types.add(type);
        }
        return types;
    }

    /**
     * 用于获取宝可梦的特性
     * @param e1 li标签的第5个span子标签，代表1个或2个普通特性(内容分别为ability与"ability1"<br>"ability2")
     * @param e2 li标签的第6个span子标签，代表隐藏特性，可为空
     * @return 宝可梦的特性
     */
    private List<Ability> getAbilities(Element e1, Element e2){
        List<Ability> abilities = new ArrayList<>();

        //先解析普通特性
        String normal_abilities = e1.html();
        if(normal_abilities != null && !normal_abilities.isEmpty()){
            String[] arr = normal_abilities.split("<br>");
            for(String str : arr) {
                Ability ability = new Ability();
                ability.setName(str.trim());
                abilities.add(ability);
            }
        }

        //再解析隐藏特性
        String hidden_ability = e2.text();
        if(hidden_ability != null && !hidden_ability.isEmpty()) {
            Ability ability = new Ability();
            ability.setName(hidden_ability);
            abilities.add(ability);
        }

        return abilities;
    }

    /**
     * 用于获取宝可梦的种族值
     * @param elements li标签中的class="col statcol"的span子标签
     * @return 一个含6个元素数组，分别代表HP，物攻，物防，特攻，特防，速度
     */
    private Integer[] getOther(Elements elements){
        Integer[] arr = new Integer[6];
        String tem;
        for(int i = 0; i <= 5; i++) {
            //这里text()方法返回的是类似HP 45形式的字符串
            tem = elements.get(i).text().trim();
            tem = tem.substring(tem.lastIndexOf(' ') + 1);
            arr[i] = Integer.parseInt(tem);
        }
        return arr;
    }
}
