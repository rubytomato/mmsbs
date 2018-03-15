package com.example.domain.dao;

import com.example.domain.dto.ItemDto;
import com.example.domain.entity.Category;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String ITEM_STOCK_QUERY =
        "SELECT item.id AS itemId, " +
               " item.name AS itemName, " +
               " item.price AS price, " +
               " item.salesFrom AS salesFrom, " +
               " item.salesTo AS salesTo, " +
               " item.standardType AS standardType, " +
               " item.category.name as categoryName, " +
               " stocks.stock AS stock " +
          "FROM com.example.domain.entity.Item AS item " +
   "INNER JOIN item.itemStocks AS stocks " +
         "WHERE item.category = :category " +
           "AND item.delFlag = :delFlag " +
           "AND item.salesFrom <= :currentDate " +
           "AND item.salesTo >= :currentDate " +
           "AND stocks.stock > 0 " +
           "AND stocks.delFlag = :delFlag "
            ;

    @SuppressWarnings("unchecked")
    @Transactional
    public List<ItemDto> findItemStock(final Category category, final LocalDateTime current) {

        Query query = entityManager.createQuery(ITEM_STOCK_QUERY)
                // (1)
                .unwrap(org.hibernate.Query.class)
                // (2)
                .setResultTransformer(Transformers.aliasToBean(ItemDto.class))
                .setParameter("category", category)
                .setParameter("delFlag", Boolean.FALSE, BooleanType.INSTANCE)
                .setParameter("currentDate", current, LocalDateTimeType.INSTANCE)
                .setComment("item stock list");

        System.out.println(query.getClass().getCanonicalName());

        // (3)
        System.out.println(query.getQueryString());

        return query.list();
    }

}
