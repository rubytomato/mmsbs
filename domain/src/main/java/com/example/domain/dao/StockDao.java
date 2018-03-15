package com.example.domain.dao;

import com.example.domain.entity.Stock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class StockDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Stock findOne(Long id) {

        TypedQuery<Stock> query = entityManager.createQuery("SELECT s FROM Stock AS s WHERE s.id = :id", Stock.class)
            .setParameter("id", id);

        Stock result = query.getSingleResult();
        return result;
    }

    public List<Stock> findAll() {
        TypedQuery<Stock> query = entityManager.createQuery("SELECT s FROM Stock AS s", Stock.class)
                .setMaxResults(10);

        List<Stock> lists = query.getResultList();
        return lists;
    }

    @Transactional(propagation = Propagation.NESTED)
    public Stock save(Stock stock) {
        entityManager.persist(stock);
        entityManager.flush();
        return stock;
    }

}
