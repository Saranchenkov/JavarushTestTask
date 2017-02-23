package com.testtask.springmvc.dao;

import java.util.List;

import com.testtask.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	void saveUser(User user);
	
	void deleteUserByName(String name);
	
	List<User> findAllUsers();

	User findUserByName(String name);

}
