package com.laidw.mapper;

import com.laidw.entity.Category;
import com.laidw.entity.Skill;
import com.laidw.entity.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillMapperTest {
    @Autowired
    private SkillMapper mapper;

    //一些实体类对象
    private Skill skill = new Skill();
    private Type t1, t2, t3;
    private Category c1, c2, c3;

    @Test
    public void testSaveSkill(){
        for(int i = 1; i < 10; i++){
            skill.setName("skill" + i);
            mapper.saveSkill(skill);
        }
    }

    @Test
    public void testSelectSkill(){
        skill = mapper.selectSkillById(2);
        System.out.println(skill);
        System.out.println(skill.getType());
        System.out.println("---------------------------");

        skill = mapper.selectSkillByName("skill5");
        System.out.println(skill);
        System.out.println(skill.getCategory());
        System.out.println("---------------------------");

        List<Skill> list = mapper.selectAllSkills();
        for(Skill s : list)
            System.out.println(s);
    }

    @Test
    public void testUpdateSkill(){
        skill = new Skill();
        skill.setName("skill5"); skill.setCategory(c2); skill.setPp(12);
        mapper.updateSkillByName(skill);

        skill = new Skill();
        skill.setName("skill6"); skill.setType(t2); skill.setPp(17);
        mapper.updateSkillByNameSelectively(skill);

        skill = new Skill();
        skill.setId(7); skill.setCategory(c3); skill.setPp(11);
        mapper.updateSkillById(skill);

        skill = new Skill();
        skill.setId(8); skill.setType(t3); skill.setPp(14);
        mapper.updateSkillByIdSelectively(skill);
    }

    @Test
    public void testDeleteSkill() throws InterruptedException {
        mapper.deleteSkillByName("skill9");
        Thread.sleep(5000);

        mapper.deleteSkillById(8);
        Thread.sleep(5000);

        mapper.deleteAllSkills();
    }

    @Before
    public void initTypesAndCategory(){
        t1 = new Type(); t1.setName("t1"); t1.setIconUrl("t1_url");
        t2 = new Type(); t2.setName("t2"); t2.setIconUrl("t2_url");
        t3 = new Type(); t3.setName("t3"); t3.setIconUrl("t3_url");

        c1 = new Category(); c1.setName("c1"); c1.setIconUrl("c1_url");
        c2 = new Category(); c2.setName("c2"); c2.setIconUrl("c2_url");
        c3 = new Category(); c3.setName("c3"); c3.setIconUrl("c3_url");
    }

    @Before
    public void initSkill(){
        skill.setPower(100);
        skill.setPp(10);
        skill.setAccuracy(100);
        skill.setEffect("Big Power");
        skill.setType(t1);
        skill.setCategory(c1);
    }
}
