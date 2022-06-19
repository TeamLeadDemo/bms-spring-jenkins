package com.demo.bms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bms.entity.BookEntity;

// by extending JpaRepository of spring data jpa, spring data jpa will take 
// care of the hibernate jpa implementation of the dao interface
// we need not provide an implementation class for the dao interface here


//@Component
//stereotype annotations  - @RestController, @Service, @Repository

@Repository
public interface BookDao extends JpaRepository<BookEntity, Integer> {

	// ways to provide custome queries
	// finder methods
	// @Query - provide the HQL query here
	
	// create finder methods here for customized searches
	
	// finder method to fetch books of the genre passsed as argument
	// select * from book_details where book_genre='genre';
	List<BookEntity> findByBookGenre(String genre);
	
	// select * from book_details where book_genre='genre' and book_author='author';
	List<BookEntity> findByBookGenreAndBookAuthor(String genre, String author);
	
}
