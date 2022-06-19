package com.demo.bms.exception;

public class BookNotFoundException extends Exception{

	int bookId;
	
	public BookNotFoundException(int bookId) {
		this.bookId = bookId;
	}
	
	@Override
	public String getMessage() {
		return "Book with ID:" + bookId +" not Found!";
	}
}
