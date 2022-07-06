package com.bookess.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookess.entity.BookEntity;
import com.bookess.entity.UserBookEntity;
import com.bookess.service.UserBookService;
import com.bookess.vo.LoginVO;
import com.bookess.vo.ReadLaterOrLikeVO;

@RestController
@RequestMapping("/userbook")
public class UserBookController {
	
	
	@Autowired
	private UserBookService userBookService;
	
	
	
	
	@GetMapping("/getBooks")
	public List<BookEntity> getBooks(){
		return userBookService.getBooks();
	}
	
	@GetMapping("/getBooksByUserAndReadLater")
	public List<UserBookEntity> getBooksByUserAndReadLater(@RequestParam("email") String email) throws Exception{
		return userBookService.getBooksByUserAndReadLater(email);
	}
	
	@GetMapping("/getBooksByUserAndLiked")
	public List<UserBookEntity> getBooksByUserAndLiked(@RequestParam("email") String email) throws Exception{
		return userBookService.getBooksByUserAndLiked(email);
	}
	
	
	@PostMapping("/readLater")
	public String readLater(@RequestBody ReadLaterOrLikeVO vo) {
		return userBookService.readLater(vo);
	}
	@PostMapping("/liked")
	public String liked(@RequestBody ReadLaterOrLikeVO vo) {
		return userBookService.liked(vo);
	}
	
		
	
	

}
