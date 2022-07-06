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

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserBookRepository userBookRepo;

	
	public String createBook(BookEntity book, String email) {
		UserEntity user = UserService.map.get(email);
		if (user == null) {
			return "To Add book, please login as admin first";
		} else if (!user.getRole().equalsIgnoreCase("admin")) {
			return "Only Admin can add a book";
		} else {
			BookEntity save = bookRepository.save(book);
			if (save != null) {
				return "Success";
			}
		}
		return "Failed";

	}

	public String updateBook(BookEntity book, String email) {
		UserEntity user = UserService.map.get(email);
		if (user == null) {
			return "To Add book, please login as admin first";
		} else if (!user.getRole().equalsIgnoreCase("admin")) {
			return "Only Admin can add a book";
		} else {
			Optional<BookEntity> findById = bookRepository.findById(book.getId());
			if (findById.isEmpty()) {
				return "Book Not Exit in Database with bookId " + book.getId();
			}else {
				BookEntity save = bookRepository.save(book);
				return "Book updated successfully";
			}
		}
		
	}

	public BookEntity getBookById(Long bookId) {
		Optional<BookEntity> findById = bookRepository.findById(bookId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	public String deleteBook(Long bookId, String email) {
		UserEntity user = UserService.map.get(email);
		if (user == null) {
			return "To Add book, please login as admin first";
		} else if (!user.getRole().equalsIgnoreCase("admin")) {
			return "Only Admin can add a book";
		}else {
			List<UserBookEntity> findByBookId = userBookRepo.findByBookId(bookId);
			userBookRepo.deleteAll(findByBookId);
			bookRepository.deleteById(bookId);
			return "success";			
		}
	}

}
