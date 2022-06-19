package com.demo.bms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.demo.bms.dao.BookDao;
import com.demo.bms.entity.BookEntity;
import com.demo.bms.exception.ApplicationException;
import com.demo.bms.exception.BookEmptyException;
import com.demo.bms.exception.BookNotFoundException;
import com.demo.bms.pojo.BookPojo;
import com.demo.bms.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BooKServiceTest {
	
	@Mock
	BookDao bookDao;
	
	@InjectMocks
	BookServiceImpl bookService;
	
	private BookPojo expectedBookPojo;
	private BookEntity dummyBookEntity;
	
	@BeforeEach
    public void setup(){
        
        expectedBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
        dummyBookEntity = new BookEntity(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
        
    }

    // JUnit test for save Book method
    @DisplayName("JUnit test for save book method")
    @Test
    public void testAddBook() throws ApplicationException{
       when(bookDao.saveAndFlush(dummyBookEntity)).thenReturn(dummyBookEntity);

       BookPojo sendBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
       BookPojo actualBookPojo = bookService.addBook(sendBookPojo);

       assertEquals(1, actualBookPojo.getId());
    }

    // JUnit test for getAllBooks method
    @DisplayName("JUnit test for getAllBooks method")
    @Test
    public void testGetAllBooks() throws ApplicationException, BookEmptyException{
        when(bookDao.findAll()).thenReturn(List.of(dummyBookEntity, dummyBookEntity));
   
        List<BookPojo> actualAllBookPojoList = bookService.getAllBooks();

        assertNotNull(actualAllBookPojoList);
        assertEquals(2, actualAllBookPojoList.size());
    }
    
    // JUnit test for getABook method
    @DisplayName("JUnit test for getABook method")
    @Test
    public void testGetABook() throws ApplicationException, BookNotFoundException{
    	when(bookDao.findById(1)).thenReturn(Optional.of(dummyBookEntity));
    	BookPojo actualBookPojo = bookService.getABook(1);
    	assertEquals(expectedBookPojo, actualBookPojo);
    }
    
    // JUnit test for deleteBook method
    @DisplayName("JUnit test for deleteBook method")
    @Test
    public void testDeleteBook() throws ApplicationException, BookNotFoundException{
    	doNothing().when(bookDao).deleteById(1);
    	bookService.deleteBook(1);
    	verify(bookDao, times(1)).deleteById(1);
    }
    
    // JUnit test for save Book method
    @DisplayName("JUnit test for update book method")
    @Test
    public void testUpdateBook() throws ApplicationException{
       when(bookDao.save(dummyBookEntity)).thenReturn(dummyBookEntity);

       BookPojo sendBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
       BookPojo actualBookPojo = bookService.updateBook(sendBookPojo);

       assertEquals(expectedBookPojo, actualBookPojo);
    }

}
