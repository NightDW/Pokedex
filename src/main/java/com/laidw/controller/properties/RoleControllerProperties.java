package com.laidw.controller.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本类用于封装RoleController需要使用到的一些数据
 * 主要是和展示角色数据的页面相关的；如一个页面要展示几条数据等
 */

@Component
@Getter@Setter@ToString
@ConfigurationProperties(prefix = "controller.role")
public class RoleControllerProperties {
    /**
     * 指定一个页面要展示多少条角色数据
     */
    private Integer pageSize = 100;

    /**
     * 指定导航条上要显示几个页码
     */
    private Integer navigatePages = 7;
}

