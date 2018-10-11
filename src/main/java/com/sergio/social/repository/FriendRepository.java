package com.sergio.social.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sergio.social.database.model.Friend;
import com.sergio.social.database.model.User;
import com.sergio.social.enums.FriendStatusEnum;


@RepositoryRestResource
public interface FriendRepository extends CrudRepository<Friend, Long> {

    List<Friend> findByOriginUserOrDestinationUserAndStatus(User originUser,User destinationUser,FriendStatusEnum status);
    
    Friend findByOriginUserAndDestinationUser(User originUser,User destinationUser);
    
}
