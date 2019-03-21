package com.laidw.spider.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本类封装和HtmlUnit的WebClient相关的属性
 * 各项属性都已经提供了默认值，如果需要自定义配置，只需在主配置文件中重新配置即可
 */

@Component
@Getter@Setter@ToString
@ConfigurationProperties(prefix = "spider.html-unit")
public class HtmlUnitProperties {
    /**
     * 指定使用哪种类型的浏览器
     */
    private String clientType = "default";

    /**
     * 指定是否接收任何主机连接，无论它是否有有效的证书
     */
    private Boolean useInsecureSSL = true;

    /**
     * 指定连接超时时间
     */
    private Integer timeout = 60000;

    /**
     * 指定当JS执行失败时是否抛出异常
     */
    private Boolean throwExOnScriptError = false;

    /**
     * 指定当状态码不为200时是否报错
     */
    private Boolean throwExOnNot200 = false;

    /**
     * 这两个配置的作用暂时不懂
     */
    private Boolean activeXNative = false;
    private Boolean doNotTrackEnabled = false;

    /**
     * 指定是否启用CSS，JS和AJAX
     */
    private Boolean cssEnabled = false;
    private Boolean jsEnabled = true;
    private Boolean ajaxEnabled = true;

    /**
     * 指定执行JS的最长时间（单位：毫秒）
     */
    private Integer jsTimeout = 10000;
}
