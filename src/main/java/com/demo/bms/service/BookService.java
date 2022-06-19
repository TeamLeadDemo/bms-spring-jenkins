package com.demo.bms.service;

import java.util.List;

import com.demo.bms.exception.ApplicationException;
import com.demo.bms.exception.BookEmptyException;
import com.demo.bms.exception.BookNotFoundException;
import com.demo.bms.pojo.BookPojo;

public interface BookService {
	BookPojo addBook(BookPojo bookPojo) throws ApplicationException;
	
	BookPojo updateBook(BookPojo bookPojo) throws ApplicationException;
	
	boolean deleteBook(int bookId) throws ApplicationException;
	
	List<BookPojo> getAllBooks() throws ApplicationException, BookEmptyException;
	
	BookPojo getABook(int bookId) throws ApplicationException, BookNotFoundException;
	
	List<BookPojo> getBooksByGenre(String genre) throws ApplicationException;

	List<BookPojo> getBooksByGenreAndAuthor(String genre, String author);
}
