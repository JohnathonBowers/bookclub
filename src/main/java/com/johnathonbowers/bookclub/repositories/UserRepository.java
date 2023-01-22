package com.johnathonbowers.bookclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.johnathonbowers.bookclub.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail (String email);
	
	Optional<User> findById (Long userId);
	
}
