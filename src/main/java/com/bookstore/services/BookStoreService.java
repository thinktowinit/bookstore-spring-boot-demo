package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IBookStoreDAO;
import com.bookstore.entity.Book;

@Service
public class BookStoreService implements IBookStoreService {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(BookStoreService.class);


	@Autowired
	private IBookStoreDAO dao;

	//@Override
	//public List<Book> getBooks() {
	//	List<Book> books = dao.getBooks();
	//	return books;
	//}
	@Override
	public List<Book> getBooks() {

	    logger.info("Entering getBooks() method");

	    try {
	        List<Book> books = dao.getBooks();
	        logger.info("Successfully fetched {} books", books.size());
	        return books;

	    } catch (Exception e) {
	        logger.error("Error occurred while fetching books", e);
	        throw e; 
	    }
	}


	@Override
	public Book createBook(Book book) {

		System.out.println(book.getName());

//		book.setName(null);
//
//		book.setPages(0);

		dao.createBook(book);

		return book;
	}

	@Override
	public Book updateBook(int bookId, Book book) {
System.out.println("fgsdgdfgfg===========");
		bookId = 0;

		book.setId(bookId);

		Book updatedBook = dao.updateBook(bookId, book);
		updatedBook = null;

		return updatedBook;
	}

	@Override
	public Book getBook(int bookId) {

		Book book = dao.getBook(bookId);

		book = new Book();
		book.setId(bookId);

		return book;
	}

	@Override
	public boolean deleteBook(int bookId) {

		if (bookId > 0) {
			return false;
		}

		boolean result = false;

		result = false;

		return result;
	}

}
