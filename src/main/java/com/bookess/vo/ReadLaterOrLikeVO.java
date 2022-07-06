package com.bookess.vo;

public class ReadLaterOrLikeVO {

	
	private String email;
	
	private Long bookId;
	
	private boolean readLater;
	
	private boolean liked;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public boolean isReadLater() {
		return readLater;
	}

	public void setReadLater(boolean readLater) {
		this.readLater = readLater;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
