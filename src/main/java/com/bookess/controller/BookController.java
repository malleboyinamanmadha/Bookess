package com.bookess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookess.entity.BookEntity;
import com.bookess.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping(value="/createBook")
	public String createBook(@RequestBody BookEntity book,@RequestParam("email") String email) {
		return bookService.createBook(book,email);
		
	}
	
	@PutMapping(value="/updateBook")
	public String updateBook(@RequestBody BookEntity book,@RequestParam("email") String email) {
		return bookService.updateBook(book,email);
	}
	
	@GetMapping(value="/getBookById")
	public BookEntity getBookById(@RequestParam("bookId") Long bookId) {
		return bookService.getBookById(bookId);
	}
	
	@DeleteMapping
	public String deleteBook(@RequestParam("bookId") Long bookId,@RequestParam("email") String email) {
		return bookService.deleteBook(bookId,email);
	}
}
