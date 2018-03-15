package com.example.domain.dao;

import com.example.domain.TestApplication;
import com.example.domain.dto.ItemDto;
import com.example.domain.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
public class ItemDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemDao itemDao;

    @Transactional
    @Test
    public void findItemStock() {
        Category category = entityManager.find(Category.class, 1L);
        //System.out.println(category);

        LocalDateTime current = LocalDateTime.of(2016, 5, 15, 0, 0, 0);

        List<ItemDto> itemDtos = itemDao.findItemStock(category, current);
        //itemDtos.forEach(System.out::println);
    }

}
