package com.testtask.springmvc.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao<T>{
	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	public void setClazz(Class<T> clazz) {
		this.clazz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T findById(int id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll(){
		return entityManager.createQuery("select x from " + clazz.getName() + " x")
				.getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteByName(String name) {
		entityManager.createQuery("delete from " + clazz.getName() + " x where x.name = :name")
				.setParameter("name", name)
				.executeUpdate();
	}

	public T findByName(String name) {
		T entity = null;
		try {
			entity = entityManager.createQuery("select x from " + clazz.getName() + " x where x.name = :name", clazz)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e){
			System.out.printf("\n\n Entity with name %s is not found!\n", name);
		}
		return entity;
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

}
