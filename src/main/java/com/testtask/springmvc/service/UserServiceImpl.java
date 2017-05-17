package com.testtask.springmvc.service;

import java.util.List;

import com.testtask.springmvc.dao.GenericDao;
import com.testtask.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private GenericDao<User> dao;

	@Autowired
	public void setDao(GenericDao<User> dao) {
		this.dao = dao;
	}

	public User findById(int id) {
		return dao.findById(id);
	}

	public void saveUser(User user) {
		dao.save(user);
	}

	public List<User> getCurrentPageList(int pageNumber){
		return dao.getCurrentPageList(pageNumber);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity != null){
			entity.setName(user.getName());
			entity.setCreatedDate(user.getCreatedDate());
			entity.setAge(user.getAge());
			entity.setIsAdmin(user.getIsAdmin());
		}
	}

	public void deleteUserByName(String name) {
		dao.deleteByName(name);
	}
	
	public List<User> findAllUsers() {
		return dao.findAll();
	}

	public User findUserByName(String name) {
		return dao.findByName(name);
	}

	public boolean isUserNameUnique(Integer id, String name) {
		System.out.println("\n\n FINDING... \n\n");
		User user = findUserByName(name);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public int getPageCount() {
		return (int)Math.ceil(findAllUsers().size()/4.0);
	}
}
