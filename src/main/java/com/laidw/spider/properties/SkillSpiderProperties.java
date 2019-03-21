package com.laidw.spider.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本类封装和技能信息爬虫相关的属性，注意本类和PokemonSpiderProperties类是很相似的
 * 各项属性都已经提供了默认值，如果需要自定义配置，只需在主配置文件中重新配置即可
 */

@Component
@Getter@Setter@ToString
@ConfigurationProperties(prefix = "spider.skill")
public class SkillSpiderProperties {
    /**
     * 指定技能信息爬虫要爬取哪个页面的数据；要求该页面有所有技能的信息
     */
    private String webUrl = "https://dex.pokemonshowdown.com/moves/";

    /**
     * 指定页面中所有技能的信息所在的ul标签的选择器
     */
    private String ulSelector = "ul[class=utilichart]";

    /**
     * 指定技能信息加载完毕，即整个页面加载完毕之后，系统的提示信息
     */
    private String skillsLoadedMsg = "技能数据已加载完毕，开始解析页面";

    /**
     * 指定ul标签内出现非法的li标签（即不包含技能信息的li标签）时系统的提示信息
     */
    private String invalidliTagMsg = "无效的li标签！已自动忽略";
}
