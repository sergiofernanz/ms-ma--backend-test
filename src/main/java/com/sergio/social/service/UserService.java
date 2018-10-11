package com.sergio.social.service;

import org.springframework.stereotype.Service;

import com.sergio.social.database.model.User;

@Service
public interface UserService {

	User getActualUser();
	
	User getUserByUsername(String username);
	
    User save(User user);
	
	
}
