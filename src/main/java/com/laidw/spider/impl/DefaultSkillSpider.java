package com.laidw.spider.impl;

import com.laidw.entity.Category;
import com.laidw.entity.Skill;
import com.laidw.entity.Type;
import com.laidw.spider.properties.SkillSpiderProperties;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Showdown&lt;Skill&gt;的默认子类
 * 也是默认的技能信息爬虫类
 */

@Component
public class DefaultSkillSpider extends ShowdownSpider<Skill> {
    @Autowired
    private SkillSpiderProperties properties;

    @Override
    protected String getUrl() {
        return properties.getWebUrl();
    }

    @Override
    protected String getLoadedMsg() {
        return properties.getSkillsLoadedMsg();
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
     * 用于解析li标签，把其中的数据封装成Skill对象
     * @param li li标签
     * @return Skill对象
     */
    protected Skill getObjectInternal(Element li) {
        Element a = li.selectFirst("a[href]");

        //每个技能的信息都封装在这些span标签中
        //注意select()方法会把span子标签中的span子标签也选出来
        //所以这里应该用children()方法，防止选出子子标签
        Elements spans = a.children();

        //用于封装数据的Skill对象
        Skill skill = new Skill();

        //给Skill对象的各个属性赋值
        skill.setName(getName(spans.get(0)));
        skill.setType(getType(spans.get(1)));
        skill.setCategory(getCategory(spans.get(1)));
        skill.setPower(getPower(spans.get(2)));
        skill.setAccuracy(getAccuracy(spans.get(3)));
        skill.setPp(getPp(spans.get(4)));
        skill.setEffect(getEffect(spans.get(5)));

        return skill;
    }

    /**
     * 用于获取技能的名称
     * @param element li标签的第一个span子标签
     * @return 技能的名称
     */
    private String getName(Element element){
        return element.text();
    }

    /**
     * 用于获取技能的属性
     * @param element li标签的第二个span子标签，属性的信息在span标签的第一个img子标签中
     * @return 技能的属性
     */
    private Type getType(Element element){
        Element img = element.getElementsByTag("img").get(0);
        Type type = new Type();

        type.setName(img.attr("alt"));
        type.setIconUrl(img.attr("src"));

        return type;
    }

    /**
     * 用于获取技能的类型
     * @param element li标签的第二个span子标签，类型的信息在span标签的第二个img子标签中
     * @return 技能的类型
     */
    private Category getCategory(Element element){
        Element img = element.getElementsByTag("img").get(1);
        Category category = new Category();

        category.setName(img.attr("alt"));
        category.setIconUrl(img.attr("src"));

        return category;
    }

    /**
     * 用于获取技能的威力
     * 特别地，如果威力没有获取到或者威力为中横杠，此时需要手动把威力设置为0
     * @param element li标签的第三个span子标签，其文本内容为&lt;em&gt;Power&lt;/em&gt;&lt;br&gt;"90"
     * @return 技能的威力
     */
    private Integer getPower(Element element){
        //这里text()方法返回的字符串为Power 90格式的
        String str =  element.text().trim();

        //这个中横杠比较特殊，它对应的整数为8212
        if(str.isEmpty() || str.equals("Power —"))
            return 0;

        str = str.substring(str.lastIndexOf(' ') + 1);
        return Integer.parseInt(str);
    }

    /**
     * 用于获取技能的命中率
     * 特别地，如果命中率是一个中横杠，说明该技能是必中的技能，此时把命中率设置为0
     * @param element li标签的第四个span子标签，其文本内容和第三个span子标签类似
     * @return 技能的命中率
     */
    private Integer getAccuracy(Element element){
        //这里text()方法返回的字符串为Accuracy 90%格式的
        String str =  element.text().trim();

        str = str.substring(str.lastIndexOf(' ') + 1);

        //这个中横杠比较特殊，它对应的整数为8212
        if(str.equals("—"))
            return 0;

        //把最后的%去掉，把剩下的字符串转成整数
        return Integer.parseInt(str.substring(0, str.length() - 1));
    }

    /**
     * 用于获取技能的PP数
     * @param element li标签的第五个span子标签，其文本内容和第三个span子标签类似
     * @return 技能的PP数
     */
    private Integer getPp(Element element){
        //这里text()方法返回的字符串为PP 10格式的
        String str =  element.text().trim();

        str = str.substring(str.lastIndexOf(' ') + 1);
        return Integer.parseInt(str);
    }

    /**
     * 用于获取技能的追加效果
     * @param element li标签的第六个span子标签
     * @return 技能的追加效果
     */
    private String getEffect(Element element){
        return element.text();
    }
}
