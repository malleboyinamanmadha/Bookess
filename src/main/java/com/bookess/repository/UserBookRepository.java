package com.bookess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookess.entity.UserBookEntity;

@Repository
public interface UserBookRepository extends JpaRepository<UserBookEntity, Long>{

	
	List<UserBookEntity> findByUserEmailAndReadLater(String email,boolean readLater);
	
	List<UserBookEntity> findByUserEmailAndLiked(String email,boolean liked);

	UserBookEntity findByUserEmailAndBookId(String email, Long userId2);
	
	List<UserBookEntity> findByUserEmail(String email);
	
	List<UserBookEntity> findByBookId(Long bookId);
	
	
	
}
