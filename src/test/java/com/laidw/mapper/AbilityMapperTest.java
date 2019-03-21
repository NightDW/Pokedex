package com.laidw.mapper;

import com.laidw.entity.Ability;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbilityMapperTest {
    @Autowired
    private AbilityMapper mapper;

    //一些实体类对象
    private Ability ability = new Ability();

    @Test
    public void testSaveAbility(){
        for(int i = 1; i < 10; i++){
            ability.setName("a" + i);
            ability.setDescription("desc" + i);
            mapper.saveAbility(ability);
        }
    }

    @Test
    public void testSelectAbility(){
        System.out.println(mapper.selectAbilityByName("a3"));
        System.out.println("------------------------------");

        System.out.println(mapper.selectAbilityById(5));
        System.out.println("------------------------------");

        List<Ability> list = mapper.selectAllAbilities();
        for(Ability a : list)
            System.out.println(a);
    }

    @Test
    public void testUpdateAbility(){
        ability.setId(2);
        ability.setName("a_new");
        ability.setDescription("desc_new");
        mapper.updateAbilityById(ability);

        ability.setName("a8");
        mapper.updateAbilityByName(ability);
    }

    @Test
    public void testDeleteAbility() throws InterruptedException {
        mapper.deleteAbilityByName("a_new");
        Thread.sleep(5000);

        mapper.deleteAbilityById(8);
        Thread.sleep(5000);

        mapper.deleteAllAbilities();
    }
}
