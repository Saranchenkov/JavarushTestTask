package com.testtask.springmvc.dao;

import java.util.List;

import com.testtask.springmvc.model.User;

public interface GenericDao<T> {

	T findById(int id);

	void save(final T entity);
	
	void deleteByName(String name);
	
	List<T> findAll();

	T findByName(String name);

	void update(final T entity);

	List<T> getCurrentPageList(final int pageNumber);
}
