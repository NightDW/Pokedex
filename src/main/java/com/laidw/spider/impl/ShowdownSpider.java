package com.laidw.spider.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 我们默认的爬虫爬取的都是PokemonShowdown网站上的数据
 * 而PokemonShowdown的页面都是经过JS渲染才能把所有的数据都展现出来的
 * 也就是说，这些爬虫很多地方的代码是类似的，可以把公共代码抽取到此类中
 */

public abstract class ShowdownSpider<T> extends AbstractSpider<T>{
    @Override
    protected HtmlPage getHtmlPage(WebClient client) throws IOException {
        HtmlPage page;

        try {
            //发送请求获得页面，其中getUrl()方法需要子类实现
            page = client.getPage(getUrl());

            //后台等待JS执行完成
            waitForJsExecution(client);

            //渲染之后页面有这个标签：<button class="button big">More</button>
            //我们需要模拟点击这个按钮；注意点完之后还会有，直到全部信息加载完成才消失
            try {
                while(true){
                    page.getDocumentElement().getOneHtmlElementByAttribute("button", "class", "button big").click();
                }
            }catch (Exception e){
                //getLoadedMsg()方法需要子类实现
                System.out.println(getLoadedMsg());
            }

        }finally {
            //关闭资源
            client.close();
        }
        return page;
    }

    @Override
    protected List<T> getObjects(Document doc) {
        //获取到列表，即ul标签，其中getUlSelector()方法需要子类实现
        Element ul = doc.selectFirst(getUlSelector());

        //获取到ul标签的所有li子标签，每个li标签内包含了一个实体类对应的信息
        Elements lis = ul.getElementsByTag("li");

        //定义一个列表来接收解析后的数据
        List<T> list = new ArrayList<>();

        int index = 0;

        //遍历所有的li标签，逐个解析li标签，把数据封装成对象放到List集合中
        for(Element li : lis)
        {
            try {
                //getObjectInternal()方法需要子类实现
                list.add(getObjectInternal(li));
            }catch(Exception e){
                //如果getPokemonInternal()方法出错，说明该li不包含宝可梦数据，忽略即可
                //getInvalidLiTagMsg()方法需要子类实现
                System.out.println(getInvalidLiTagMsg() + " " + index);
            }
            index++;
        }
        return list;
    }

    /**
     * 需要子类实现的4个方法，方便子类把一些信息传递给本类
     * @return 子类传递给本类的信息
     */
    protected abstract String getUrl();
    protected abstract String getLoadedMsg();
    protected abstract String getUlSelector();
    protected abstract String getInvalidLiTagMsg();

    /**
     * 需要子类实现的方法，把一个li标签中的数据封装成一个实体类对象
     * @param li li标签
     * @return 封装成的实体类对象
     */
    protected abstract T getObjectInternal(Element li);
}
