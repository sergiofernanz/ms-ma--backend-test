package com.sergio.social.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sergio.social.database.model.User;
import com.sergio.social.enums.FriendStatusEnum;

@Service
public interface FriendService {

	List<String> getFriends(User username);
	
	void request(User originUser, User destinationUser);

	void updateFriendRequest(User originUser, User destinationUser,FriendStatusEnum friendStatus);

}
