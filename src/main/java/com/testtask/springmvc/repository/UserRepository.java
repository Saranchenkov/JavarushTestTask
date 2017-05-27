package com.testtask.springmvc.repository;

import com.testtask.springmvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ivan on 26.05.2017.
 */
@Transactional(readOnly = true)
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query("select u from User u where u.id=:id")
    User findById(@Param("id") int ID);

    @Modifying
    @Transactional
    @Query("delete from User u where u.name=:name")
    void deleteByName(@Param("name") String name);

    @Override
    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.name=:name")
    User findByName(@Param("name") String name);

}
