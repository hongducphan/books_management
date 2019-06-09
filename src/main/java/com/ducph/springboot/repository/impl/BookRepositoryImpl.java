package com.ducph.springboot.repository.impl;

import com.ducph.springboot.model.Book;
import com.ducph.springboot.repository.BookRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @Autowired
    private EntityManager em;

    public BookRepositoryImpl() {
    }

    @Override
    public List<Book> findBooksGreaterThanId(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);

        Predicate predicate = cb.gt(book.<Number> get("id"), id);
        cq.select(book).where(predicate);

        TypedQuery<Book> query =  em.createQuery(cq);
        List<Book> results = query.getResultList();
        return results;
    }
}
