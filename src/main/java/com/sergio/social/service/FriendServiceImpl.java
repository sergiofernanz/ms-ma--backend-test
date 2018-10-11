package com.sergio.social.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergio.social.database.model.Friend;
import com.sergio.social.database.model.User;
import com.sergio.social.enums.FriendStatusEnum;
import com.sergio.social.repository.FriendRepository;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendRepository friendRepository;

	/*
	 * Retrieve all the friends of the user ( where the user are destination or
	 * origin and are accepted)
	 * 
	 */
	@Override
	public List<String> getFriends(User username) {
		List<Friend> friends = friendRepository.findByOriginUserOrDestinationUserAndStatus(username, username,
				FriendStatusEnum.ACCEPTED);

		List<String> userFriends = friends.stream().flatMap(
				friend -> Stream.of(friend.getOriginUser().getUsername(), friend.getDestinationUser().getUsername()))
				.filter(name -> !name.equals(username.getUsername())).collect(Collectors.toList());

		return userFriends;
	}

	@Override
	public void request(User originUser, User destinationUser) {
		friendRepository.save(Friend.builder().originUser(originUser).destinationUser(destinationUser).status(FriendStatusEnum.INITIAL).build());
	}

	@Override
	public void updateFriendRequest(User originUser, User destinationUser,FriendStatusEnum friendStatus) {
		Friend friendRequest = friendRepository.findByOriginUserAndDestinationUser(originUser, destinationUser);
		friendRequest.setStatus(friendStatus);
		friendRepository.save(friendRequest);
	}


}
