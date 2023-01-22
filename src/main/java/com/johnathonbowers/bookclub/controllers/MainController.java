package com.johnathonbowers.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.johnathonbowers.bookclub.models.LoginUser;
import com.johnathonbowers.bookclub.models.User;
import com.johnathonbowers.bookclub.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String loginRegForm(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "loginreg.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User createdUser = userService.registerUser(newUser, result);
		if (createdUser == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "loginreg.jsp";
		}
		session.setAttribute("userId", createdUser.getId());
		return "redirect:/books";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User loggedInUser = userService.loginUser(newLogin, result);
		if (loggedInUser == null) {
			model.addAttribute("newUser", new User());
			return "loginreg.jsp";
		} else {
			session.setAttribute("userId", loggedInUser.getId());
			return "redirect:/books";
		}	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
	
}
