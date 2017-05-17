package com.testtask.springmvc.dao;


import com.testtask.springmvc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractJpaDao<User> implements GenericDao<User> {

    public UserDao() {
        setClazz(User.class);
    }

    @Override
    public List<User> getCurrentPageList(int pageNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(User.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);

        TypedQuery<User> typedQuery = entityManager.createQuery(select);

            int pageSize = 4;
            typedQuery.setFirstResult((pageNumber - 1) * pageSize - 1);
            typedQuery.setMaxResults(pageSize);
            System.out.println("Current page: " + typedQuery.getResultList());

        return typedQuery.getResultList();
    }
}
