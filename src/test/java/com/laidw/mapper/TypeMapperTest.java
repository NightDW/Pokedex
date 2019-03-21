package com.laidw.mapper;

import com.laidw.entity.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeMapperTest {
    @Autowired
    private TypeMapper mapper;

    //一些实体类对象
    private Type type = new Type();

    @Test
    public void testSaveAbility(){
        for(int i = 1; i < 10; i++){
            type.setName("t" + i);
            type.setIconUrl("url" + i);
            mapper.saveType(type);
        }
    }

    @Test
    public void testSelectAbility(){
        System.out.println(mapper.selectTypeByName("t3"));
        System.out.println("------------------------------");

        System.out.println(mapper.selectTypeById(5));
        System.out.println("------------------------------");

        List<Type> list = mapper.selectAllTypes();
        for(Type t : list)
            System.out.println(t);
    }

    @Test
    public void testUpdateAbility(){
        type.setId(2);
        type.setName("t_new");
        type.setIconUrl("url_new");
        mapper.updateTypeById(type);

        type.setName("t8");
        mapper.updateTypeByName(type);
    }

    @Test
    public void testDeleteAbility() throws InterruptedException {
        mapper.deleteTypeByName("t_new");
        Thread.sleep(5000);

        mapper.deleteTypeById(8);
        Thread.sleep(5000);

        mapper.deleteAllTypes();
    }
}
