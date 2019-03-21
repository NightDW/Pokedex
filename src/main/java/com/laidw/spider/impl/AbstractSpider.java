package com.laidw.spider.impl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.laidw.spider.Spider;
import com.laidw.spider.properties.HtmlUnitProperties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Spider接口的一个抽象实现类，主要负责完成HtmlUnit的相关配置
 * @param <T> 表示爬虫要把爬取的数据封装成T类型的对象
 */

public abstract class AbstractSpider<T> implements Spider<T> {
    @Autowired
    private HtmlUnitProperties properties;

    @Override
    public List<T> run() throws IOException{
        //先获取WebClient
        WebClient client = getWebClient();

        //用WebClient发送请求获得页面；抽象方法，需要子类实现
        HtmlPage page = getHtmlPage(client);

        //把HtmlPage转成Xml数据并交给Jsoup解析成Document
        Document doc = Jsoup.parse(page.asXml());

        //通过Document对象获取到需要的数据并封装数据；抽象方法，需要子类实现
        return getObjects(doc);
    }

    /**
     * 获取WebClient，WebClient的各项参数是从配置文件中读取出来的
     * @return 返回的是一个已经完成了默认配置的WebClient
     */
    protected WebClient getWebClient(){
        WebClient client;
        switch (properties.getClientType().toLowerCase()){
            case "chrome" : client = new WebClient(BrowserVersion.CHROME); break;
            case "firefox_38" : client = new WebClient(BrowserVersion.FIREFOX_38); break;
            case "firefox_45" : client = new WebClient(BrowserVersion.FIREFOX_45); break;
            case "ie" : client = new WebClient(BrowserVersion.INTERNET_EXPLORER); break;
            case "edge" : client = new WebClient(BrowserVersion.EDGE); break;
            case "best" : client = new WebClient(BrowserVersion.BEST_SUPPORTED); break;
            default : client = new WebClient(BrowserVersion.getDefault());
        }

        WebClientOptions ops = client.getOptions();
        ops.setUseInsecureSSL(properties.getUseInsecureSSL());
        ops.setTimeout(properties.getTimeout());
        ops.setThrowExceptionOnScriptError(properties.getThrowExOnScriptError());
        ops.setThrowExceptionOnFailingStatusCode(properties.getThrowExOnNot200());
        ops.setActiveXNative(properties.getActiveXNative());
        ops.setDoNotTrackEnabled(properties.getDoNotTrackEnabled());
        ops.setCssEnabled(properties.getCssEnabled());
        ops.setJavaScriptEnabled(properties.getJsEnabled());

        if(properties.getAjaxEnabled())
            client.setAjaxController(new NicelyResynchronizingAjaxController());

        return client;
    }

    /**
     * 利用WebClient发送请求获取页面
     * @param client WebClient
     * @return 获取到的页面
     * @throws IOException 解析失败时返回的异常
     */
    protected abstract HtmlPage getHtmlPage(WebClient client) throws IOException;

    /**
     * 通过Document对象获取到需要的数据并封装数据
     * @param doc Document
     * @return 最终解析的结果
     */
    protected abstract List<T> getObjects(Document doc);

    /**
     * 提供这个方法供子类在渲染页面时调用
     * @param client WebClient
     */
    protected void waitForJsExecution(WebClient client){
        client.waitForBackgroundJavaScript(properties.getJsTimeout());
    }
}
