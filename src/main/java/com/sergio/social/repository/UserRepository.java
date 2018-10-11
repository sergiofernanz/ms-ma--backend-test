package com.sergio.social.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sergio.social.database.model.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}
