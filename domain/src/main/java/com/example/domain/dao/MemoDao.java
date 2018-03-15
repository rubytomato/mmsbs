package com.example.domain.dao;

import com.example.domain.dto.MemoDto;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class MemoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true, timeout = 3)
    public List<MemoDto> findAll() {
        String jpql = "SELECT m.id AS id, m.title AS title, m.description AS description FROM Memo AS m";

        @SuppressWarnings("unchecked")
        List<MemoDto> lists = entityManager
                .createQuery(jpql)
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(MemoDto.class))
                .setFetchSize(50)
                .list();

        return lists;
    }

}
