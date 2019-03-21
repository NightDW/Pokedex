package com.laidw.spider.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本类封装和宝可梦信息爬虫相关的属性
 * 各项属性都已经提供了默认值，如果需要自定义配置，只需在主配置文件中重新配置即可
 */

@Component
@Getter@Setter@ToString
@ConfigurationProperties(prefix = "spider.pokemon")
public class PokemonSpiderProperties {
    /**
     * 指定宝可梦信息爬虫要爬取哪个页面的数据；要求该页面有所有宝可梦的信息
     */
    private String webUrl = "https://dex.pokemonshowdown.com/pokemon/";

    /**
     * 指定页面中所有宝可梦的信息所在的ul标签的选择器
     */
    private String ulSelector = "ul[class=utilichart]";

    /**
     * 指定宝可梦信息加载完毕，即整个页面加载完毕之后，系统的提示信息
     */
    private String pokemonsLoadedMsg = "宝可梦数据已加载完毕，开始解析页面";

    /**
     * 指定ul标签内出现非法的li标签（即不包含宝可梦信息的li标签）时系统的提示信息
     */
    private String invalidliTagMsg = "无效的li标签！已自动忽略";
}
