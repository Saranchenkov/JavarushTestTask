package com.testtask.springmvc.service;

import java.util.List;

import com.testtask.springmvc.model.User;

public interface UserService {

	User findById(int id);

	List<User> getCurrentPageList(int pageNumber);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByName(String name);

	List<User> findAllUsers();
	
	User findUserByName(String name);

	boolean isUserNameUnique(Integer id, String name);

	int getPageCount();
}
