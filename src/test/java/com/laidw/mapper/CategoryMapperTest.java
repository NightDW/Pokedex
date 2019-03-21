package com.laidw.mapper;

import com.laidw.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper mapper;

    //一些实体类对象
    private Category category = new Category();

    @Test
    public void testSaveAbility(){
        for(int i = 1; i < 10; i++){
            category.setName("c" + i);
            category.setIconUrl("url" + i);
            mapper.saveCategory(category);
        }
    }

    @Test
    public void testSelectAbility(){
        System.out.println(mapper.selectCategoryByName("c3"));
        System.out.println("------------------------------");

        System.out.println(mapper.selectCategoryById(5));
        System.out.println("------------------------------");

        List<Category> list = mapper.selectAllCategories();
        for(Category c : list)
            System.out.println(c);
    }

    @Test
    public void testUpdateAbility(){
        category.setId(2);
        category.setName("c_new");
        category.setIconUrl("url_new");
        mapper.updateCategoryById(category);

        category.setName("c8");
        mapper.updateCategoryByName(category);
    }

    @Test
    public void testDeleteAbility() throws InterruptedException {
        mapper.deleteCategoryByName("c_new");
        Thread.sleep(5000);

        mapper.deleteCategoryById(8);
        Thread.sleep(5000);

        mapper.deleteAllCategories();
    }
}
