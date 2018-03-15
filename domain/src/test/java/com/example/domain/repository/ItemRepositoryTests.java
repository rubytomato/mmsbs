package com.example.domain.repository;

import com.example.domain.constant.StandardType;
import com.example.domain.dto.ItemNameAndPriceDto;
import com.example.domain.entity.Category;
import com.example.domain.entity.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository repository;

    @Before
    public void setup() {
        System.out.println("setup");

        Category c1 = Category.builder().name("Kitchen & Dining").delFlag(false).createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(c1);
        Category c2 = Category.builder().name("FURNITURE").delFlag(false).createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(c2);
        Category c3 = Category.builder().name("BEDDING & BATH").delFlag(false).createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(c3);


        Item i1 = Item.builder().name("Kitchen & Dining - 659dac416e").price(200).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.F).category(c1).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i1);

        Item i2 = Item.builder().name("Kitchen & Dining - 8373fb1177").price(1000).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.D).category(c1).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i2);

        Item i3 = Item.builder().name("Kitchen & Dining - 933197171d").price(800).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.A).category(c1).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i3);

        Item i4 = Item.builder().name("FURNITURE - 2f54691c6f").price(1000).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.J).category(c2).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i4);

        Item i5 = Item.builder().name("FURNITURE - bc9babe111").price(400).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.E).category(c2).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i5);

        Item i6 = Item.builder().name("FURNITURE - e1cff470fb").price(900).salesFrom(LocalDateTime.now())
                .salesTo(LocalDateTime.now()).standardType(StandardType.H).category(c2).delFlag(false)
                .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i6);

        entityManager.clear();
    }

    @Test
    public void findAll() {
        List<Item> lists = repository.findAll();
        //lists.forEach(System.out::println);
    }

    @Test
    public void findOne() {
        Item item = repository.findOne(1L);
        //System.out.println(item);
    }

    @Test
    public void findOneById() {
        ItemNameAndPriceDto nameAndPrice = repository.findOneById(1L, ItemNameAndPriceDto.class);
        //System.out.println(nameAndPrice);
    }

    @Test
    public void example() {
        Item probe = new Item();
        probe.setPrice(1000);
        probe.setStandardType(StandardType.D);

        Example<Item> example = Example.of(probe);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

    @Test
    public void matcher() {
        Item item = new Item();
        item.setName("Kitchen");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.startsWith());

        Example<Item> example = Example.of(item, matcher);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

    @Test
    public void matching() {
        Item probe = new Item();
        probe.setName("kitchen");
        probe.setPrice(800);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.ignoreCase().startsWith());
                //.withMatcher("price", match -> match.exact());

        Example<Item> example = Example.of(probe, matcher);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

    @Test
    public void matchingAny() {
        Item probe = new Item();
        probe.setName("kitchen");
        probe.setPrice(800);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", match -> match.ignoreCase().startsWith());

        Example<Item> example = Example.of(probe, matcher);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

    @Test
    public void withIgnorePaths() {
        Item probe = new Item();
        probe.setId(11L);
        probe.setName("kitchen");
        probe.setPrice(800);
        probe.setStandardType(StandardType.A);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", match -> match.ignoreCase().startsWith())
                .withIgnorePaths("id", "price", "standardType");

        Example<Item> example = Example.of(probe, matcher);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

    @Test
    public void withIncludeNullValues() {
        Item probe = new Item();
        probe.setName("kitchen");

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", match -> match.ignoreCase().startsWith())
                .withIncludeNullValues();

        Example<Item> example = Example.of(probe, matcher);

        List<Item> items = repository.findAll(example);
        //items.forEach(System.out::println);
    }

}
