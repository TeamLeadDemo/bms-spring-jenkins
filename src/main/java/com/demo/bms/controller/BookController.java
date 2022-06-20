package com.demo.bms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bms.exception.ApplicationException;
import com.demo.bms.exception.BookEmptyException;
import com.demo.bms.exception.BookNotFoundException;
import com.demo.bms.pojo.BookPojo;
import com.demo.bms.service.BookService;

@CrossOrigin // to enable cors
@RestController
@RequestMapping("api")
public class BookController {
	
	//final static Logger LOG = LoggerFactory.getLogger(BookController.class);
	
	// throws ApplicationException is incorrect here, have to handle the exceptions gracefully
	// this can be done using GlobalExceptionHAndler - will be covered tomorrow
	
	@Autowired
	BookService bookService;
	// create the rest methods for the rest enpoints
	
	// http://localhost:5555/api/books
	@GetMapping("books")
	public List<BookPojo> getAllBooks() throws ApplicationException, BookEmptyException{
		//LOG.info("Entered getAllBooks() in controller...");
		List<BookPojo> allBooks = bookService.getAllBooks();
		//LOG.info("Exited getAllBooks() in controller...");
		return allBooks;
	}
	
	// http://localhost:5555/api/books/2
	@GetMapping("books/{bid}")
	public BookPojo getABook(@PathVariable("bid") int bookId) throws ApplicationException, BookNotFoundException {
		//LOG.info("Entered getABook() in controller...");
		BookPojo bookPojo = bookService.getABook(bookId);
		//LOG.info("Exited getABook() in controller...");
		return bookPojo;
	}
	
	//http://localhost:5555/api/books/2
	@DeleteMapping("books/{bid}")
	public boolean deleteBook(@PathVariable("bid") int bookId) throws ApplicationException {
		return bookService.deleteBook(bookId);
	}
	
	// http://localhost:5555/api/books
	@PostMapping("books")
	public BookPojo addBook(@Valid @RequestBody BookPojo bookPojo) throws ApplicationException {
		return bookService.addBook(bookPojo);
	}

	// http://localhost:5555/api/books
	@PutMapping("books")
	public BookPojo updateBook(@RequestBody BookPojo bookPojo) throws ApplicationException {
		return bookService.updateBook(bookPojo);
	}
	
	// http://localhost:5555/api/books/Comedy
	@GetMapping("books/genre/{genre}")
	public List<BookPojo> getBooksByGenre(@PathVariable("genre") String genre) throws ApplicationException {
		return bookService.getBooksByGenre(genre);
		
	}
	
	// http://localhost:5555/api/books/Comedy
	@GetMapping("books/genre/author/{genre}/{author}")
	public List<BookPojo> getBooksByGenreAndAuthor(@PathVariable("genre") String genre, @PathVariable("author") String author ) throws ApplicationException {
		return bookService.getBooksByGenreAndAuthor(genre, author);
		
	}
	
	
}
