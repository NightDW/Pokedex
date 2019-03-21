package com.laidw.spider.impl;

import com.laidw.entity.Pokemon;
import com.laidw.spider.Spider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPokemonSpiderTest {
    @Autowired
    private Spider<Pokemon> spider;

    @Test
    public void testRun() throws IOException {
        List<Pokemon> list =  spider.run();
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}
