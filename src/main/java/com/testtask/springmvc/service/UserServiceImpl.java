package com.testtask.springmvc.service;

import com.testtask.springmvc.model.User;
import com.testtask.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
public class UserServiceImpl implements UserService {

	private static final int PAGE_SIZE = 4;

	@Autowired
	private UserRepository repository;

	public User findById(int id) {
		return repository.findById(id);
	}

	@Transactional
	public void saveUser(User user) {
		repository.save(user);
	}

	public Page<User> getCurrentPage(int pageNumber){
		PageRequest page = new PageRequest(pageNumber - 1, PAGE_SIZE);
		return repository.findAll(page);
	}

	@Transactional
	public void updateUser(User user) {
		repository.save(user);
	}

	public void deleteUserByName(String name) {
		repository.deleteByName(name);
	}

	public User findUserByName(String name) {
		return repository.findByName(name);
	}

	public boolean isUserNameUnique(Integer id, String name) {
		User user = findUserByName(name);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
}
