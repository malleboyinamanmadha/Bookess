package com.bookess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookess.entity.BookEntity;
import com.bookess.entity.UserBookEntity;
import com.bookess.entity.UserEntity;
import com.bookess.repository.BookRepository;
import com.bookess.repository.UserBookRepository;
import com.bookess.repository.UserRepository;
import com.bookess.vo.LoginVO;
import com.bookess.vo.ReadLaterOrLikeVO;

@Service
public class UserBookService {
	

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private UserBookRepository userBookRepo;
	
	

	public List<BookEntity> getBooks() {
		return bookRepo.findAll();
	}

	public List<UserBookEntity> getBooksByUserAndReadLater(String email) throws Exception {
		UserEntity userName = UserService.map.get(email);
		if (userName == null) {
			throw new Exception("User Not Login");
		} 
		return userBookRepo.findByUserEmailAndReadLater(email, true);
	}
	
	public List<UserBookEntity> getBooksByUserAndLiked(String email) throws Exception {
		UserEntity userName = UserService.map.get(email);
		if (userName == null) {
			throw new Exception("User Not Login");
		} 
		return userBookRepo.findByUserEmailAndLiked(email, true);
	}

	public String readLater(ReadLaterOrLikeVO vo) {
		String email = vo.getEmail();
		UserEntity userName = UserService.map.get(email);
		if (userName == null) {
			return "please login first";
		} 
		else {
			UserBookEntity findByUserIdAndBookId = userBookRepo.findByUserEmailAndBookId(email,vo.getBookId());
			if(findByUserIdAndBookId!=null) {
				findByUserIdAndBookId.setReadLater(true);
				userBookRepo.save(findByUserIdAndBookId);
				return "Success";
			}else {
				Optional<UserEntity> user = userRepo.findByEmail(email);
				Optional<BookEntity> book = bookRepo.findById(vo.getBookId());
				
				if(user.isPresent() && book.isPresent()) {
					UserBookEntity userBook=new UserBookEntity();
					userBook.setBook(book.get());
					userBook.setUser(user.get());
					userBook.setReadLater(true);
					userBookRepo.save(userBook);
					return "Success";
				}
				return "Failed";
			}
			
		}
	}

	public String liked(ReadLaterOrLikeVO vo) {
		String email = vo.getEmail();
		UserEntity userName = UserService.map.get(email);
		if (userName == null) {
			return "please login first";
		} 
		else {
			UserBookEntity findByUserIdAndBookId = userBookRepo.findByUserEmailAndBookId(email,vo.getBookId());
			if(findByUserIdAndBookId!=null) {
				findByUserIdAndBookId.setLiked(true);
				userBookRepo.save(findByUserIdAndBookId);
				return "Success";
			}else {
				Optional<UserEntity> user = userRepo.findByEmail(email);
				Optional<BookEntity> book = bookRepo.findById(vo.getBookId());
				
				if(user.isPresent() && book.isPresent()) {
					UserBookEntity userBook=new UserBookEntity();
					userBook.setBook(book.get());
					userBook.setUser(user.get());
					userBook.setLiked(true);
					userBookRepo.save(userBook);
					return "Success";
					
				}
				return "Failed";
			}
		}
	}

}
