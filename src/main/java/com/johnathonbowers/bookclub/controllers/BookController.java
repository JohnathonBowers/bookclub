package com.johnathonbowers.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnathonbowers.bookclub.models.Book;
import com.johnathonbowers.bookclub.models.User;
import com.johnathonbowers.bookclub.services.BookService;
import com.johnathonbowers.bookclub.services.UserService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String getAllBooks(HttpSession session, Model model) {
		List<Book> allBooks = bookService.getAllBooks();
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("allBooks", allBooks);
		model.addAttribute("user", userService.findOneUser(userId));
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String createBookForm(@ModelAttribute("book") Book book, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "addbook.jsp";
	}
	
	@PostMapping("/new")
	public String createBookAction(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "addbook.jsp";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findOneUser(userId);
			book.setUser(user);
			bookService.createBook(book);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/{id}")
	public String viewBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Book book = bookService.getOneBook(id);
		model.addAttribute("book", book);
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		return "viewbook.jsp";
	}
	
	@GetMapping("/{id}/edit")
	public String editBookForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		Book book = bookService.getOneBook(id);
		Long bookUserId = (Long) book.getUser().getId();
		Long userId = (Long) session.getAttribute("userId");
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!bookUserId.equals(userId)) {
			return "redirect:/books";
		}
		model.addAttribute("book", book);
		return "editbook.jsp";
	}
	
	@PutMapping("/{id}/edit")
	public String editBookAction(@Valid @ModelAttribute("book") Book book, @PathVariable("id") Long id, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "editbook.jsp";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findOneUser(userId);
			book.setUser(user);
			bookService.updateBook(book);
			return "redirect:/books/{id}";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String naughtyUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@DeleteMapping("/{id}/delete")
	public String destroyBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
	
}
