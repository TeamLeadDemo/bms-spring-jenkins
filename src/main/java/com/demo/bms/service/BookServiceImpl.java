package com.demo.bms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bms.dao.BookDao;
import com.demo.bms.entity.BookEntity;
import com.demo.bms.exception.ApplicationException;
import com.demo.bms.exception.BookEmptyException;
import com.demo.bms.exception.BookNotFoundException;
import com.demo.bms.pojo.BookPojo;

@Service
public class BookServiceImpl implements BookService{
	
	// since we are working with spring boot, there is no need to configure the logginh framework
	// createing the logger variable to log the information
	// choose slf4j;s Logger to import and this in turn uses Logback(spring boot default) framework for logging
	// final static Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	BookDao bookDao;
	
	public BookServiceImpl() {
		//bookDao = new BookDaoHibernateImpl();
	}

	@Override
	public BookPojo addBook(BookPojo bookPojo) throws ApplicationException {
		// copy the pojo into an entity object
		BookEntity bookEntity = new BookEntity();
		BeanUtils.copyProperties(bookPojo, bookEntity);
		
		//  now pass the bookEntity object to spring data jpa to be inserted into the table
		BookEntity returnedBookEntity = bookDao.saveAndFlush(bookEntity);
		//set the primary key into the book pojo and return it back
		bookPojo.setId(returnedBookEntity.getId());
		return bookPojo;
	}

	@Override
	public BookPojo updateBook(BookPojo bookPojo) throws ApplicationException {
		// copy the pojo into an entity object
		BookEntity bookEntity = new BookEntity();
		BeanUtils.copyProperties(bookPojo, bookEntity);
		
		//  now pass the bookEntity object to spring data jpa to be updated into the table
		BookEntity returnedBookEntity = bookDao.save(bookEntity);
		
		return bookPojo;
	}

	@Override
	public boolean deleteBook(int bookId) throws ApplicationException {
		bookDao.deleteById(bookId);
		return true;
	}

	@Override
	public List<BookPojo> getAllBooks() throws ApplicationException, BookEmptyException {
		//LOG.info("Entered getAllBooks() in service...");
		List<BookEntity> allBooksEntity = bookDao.findAll();
		// now we have to copy each book entity object in the collection to a collection on book pojo
		// create a empty collection of book pojo
		List<BookPojo> allBooksPojo = new ArrayList<BookPojo>();
		if(allBooksEntity.isEmpty()) {
			throw new BookEmptyException();
		}
		for(BookEntity fetchedBookEntity: allBooksEntity) {
			BookPojo returnBookPojo = new BookPojo(fetchedBookEntity.getId(), fetchedBookEntity.getBookTitle(), fetchedBookEntity.getBookGenre(), fetchedBookEntity.getBookAuthor(),fetchedBookEntity.getBookCost(), fetchedBookEntity.getBookImage());
			allBooksPojo.add(returnBookPojo);
		}
		
		//LOG.info("Exited getAllBooks() in service...");
		return allBooksPojo;
	}

	@Override
	public BookPojo getABook(int bookId) throws ApplicationException, BookNotFoundException {
		
		//LOG.info("Entered getABook() in service...");
		Optional<BookEntity> bookEntityOpt = bookDao.findById(bookId);
		BookPojo bookPojo = null;		
		if(bookEntityOpt.isPresent()) {
			// take out the entity object which is wrapped into the optional object
			BookEntity fetchedBookEntity = bookEntityOpt.get();
			// copy the entity into the pojo
			//bookPojo = new BookPojo(fetchedBookEntity.getId(), fetchedBookEntity.getBookTitle(), fetchedBookEntity.getBookGenre(), fetchedBookEntity.getBookAuthor(),fetchedBookEntity.getBookCost(), fetchedBookEntity.getBookImage());
			bookPojo = new BookPojo();
			BeanUtils.copyProperties(fetchedBookEntity, bookPojo); // nested copying will not take place here
		}else {
			// book with the bookId is not present in the DB
			throw new BookNotFoundException(bookId);
		}
		//LOG.info("Exited getABook() in service...");
		return bookPojo;
	}

	@Override
	public List<BookPojo> getBooksByGenre(String genre) throws ApplicationException {
		List<BookEntity> allBooksEntity = bookDao.findByBookGenre(genre);
		// now we have to copy each book entity object in the collection to a collection on book pojo
		// create a empty collection of book pojo
		List<BookPojo> allBooksPojo = new ArrayList<BookPojo>();
		for(BookEntity fetchedBookEntity: allBooksEntity) {
			BookPojo returnBookPojo = new BookPojo(fetchedBookEntity.getId(), fetchedBookEntity.getBookTitle(), fetchedBookEntity.getBookGenre(), fetchedBookEntity.getBookAuthor(),fetchedBookEntity.getBookCost(), fetchedBookEntity.getBookImage());
			allBooksPojo.add(returnBookPojo);
		}
		return allBooksPojo;
	}

	@Override
	public List<BookPojo> getBooksByGenreAndAuthor(String genre, String author) {
		List<BookEntity> allBooksEntity = bookDao.findByBookGenreAndBookAuthor(genre, author);
		// now we have to copy each book entity object in the collection to a collection on book pojo
		// create a empty collection of book pojo
		List<BookPojo> allBooksPojo = new ArrayList<BookPojo>();
		for(BookEntity fetchedBookEntity: allBooksEntity) {
			BookPojo returnBookPojo = new BookPojo(fetchedBookEntity.getId(), fetchedBookEntity.getBookTitle(), fetchedBookEntity.getBookGenre(), fetchedBookEntity.getBookAuthor(),fetchedBookEntity.getBookCost(), fetchedBookEntity.getBookImage());
			allBooksPojo.add(returnBookPojo);
		}
		return allBooksPojo;
	}
   
	
}
