package com.johnathonbowers.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.johnathonbowers.bookclub.models.LoginUser;
import com.johnathonbowers.bookclub.models.User;
import com.johnathonbowers.bookclub.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User createUser(User newUser) {
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepository.save(newUser);
	}
	
	public User findOneUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return null;
		}
		return user.get();
	}
	
	public User registerUser(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Exist", "This email address is already associated with an account. Please either log in or use a different address.");
		}
		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Match", "Passwords do not match!");
		}
		if (!result.hasErrors()) {
			return this.createUser(newUser);
		}
		return null;
	}
	
	public User loginUser(LoginUser newLoginUser, BindingResult result) {
		Optional<User> user = userRepository.findByEmail(newLoginUser.getEmail());
		if (!user.isPresent() || !BCrypt.checkpw(newLoginUser.getPassword(), user.get().getPassword())) {
			result.rejectValue("password", "Exist", "Email or password is incorrect!");
			return null;
		} else {
			return user.get();
		}
	}
	
}
