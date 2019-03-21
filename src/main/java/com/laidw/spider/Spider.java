package com.laidw.spider;

import java.io.IOException;
import java.util.List;

/**
 * 所有爬虫的根接口，注意这里的爬虫使用的是HtmlUnit+Jsoup这两个技术
 * 因为我们爬取的网页的数据可能是通过JS异步加载的，而Jsoup不能解析JS
 * 所以选择由HtmlUnit来获取并渲染页面，然后由Jsoup来解析页面
 * @param <T> 表示爬虫要把爬取的数据封装成T类型的对象
 */

public interface Spider<T> {
    /**
     * 主要方法，需要完成网页获取，网页解析和数据封装
     * @return 返回的就是封装完成的实体类列表
     * @throws IOException 解析页面失败（如获取不到指定的标签）时抛出的异常
     */
    List<T> run() throws IOException;
}
