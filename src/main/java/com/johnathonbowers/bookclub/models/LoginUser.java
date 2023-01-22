package com.johnathonbowers.bookclub.models;

import javax.validation.constraints.NotBlank;

public class LoginUser {

	@NotBlank(message="Email address is required.")
	public String email;
	
	@NotBlank(message="Password is required.")
	public String password;
	
	public LoginUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
