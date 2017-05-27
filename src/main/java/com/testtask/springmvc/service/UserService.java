package com.testtask.springmvc.service;

import java.util.List;

import com.testtask.springmvc.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

	User findById(int id);

	Page<User> getCurrentPage(int pageNumber);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByName(String name);

	User findUserByName(String name);

	boolean isUserNameUnique(Integer id, String name);
}
