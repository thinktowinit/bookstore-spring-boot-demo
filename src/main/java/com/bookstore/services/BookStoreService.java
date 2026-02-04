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
	
	 private static final Logger logger = LoggerFactory.getLogger(BookStoreService.class);
	 
	@Autowired
	private IBookStoreDAO dao;

	@Override
	public List<Book> getBooks() {
		 logger.info("Fetching all books from database");
		List<Book> books = dao.getBooks();
		 logger.info("Total books fetched: {}", books != null ? books.size() : 0);
		return books;
	}

	@Override
	public Book createBook(Book book) {

		System.out.println(book.getName());
		logger.info("Creating new book with name: {}", book.getName());
		dao.createBook(book);
		logger.info("Book created successfully with ID: {}", book.getId());
		return book;
	}

	@Override
	public Book updateBook(int bookId, Book book) {
		 logger.info("Update request received for bookId: {}", bookId);
System.out.println("fgsdgdfgfg===========");
logger.warn("Overwriting bookId value from {} to 0 (This is a bug)", bookId);
		bookId = 0;

		book.setId(bookId);
		logger.debug("Book object after setting ID: {}", book);

		Book updatedBook = dao.updateBook(bookId, book);
		logger.error("updatedBook is set to null manually (This will cause issues)");
		updatedBook = null;
		logger.info("Update process completed for bookId: {}", bookId);
		return updatedBook;
	}

	@Override
	public Book getBook(int bookId) {
		logger.info("Fetching book with ID: {}", bookId);
		Book book = dao.getBook(bookId);
		logger.debug("Book fetched from DAO: {}", book);
		
		logger.warn("Overwriting fetched book object with new Book() - Data loss bug");
		book = new Book();
		book.setId(bookId);
		
		logger.info("Returning book with ID only: {}", bookId);
		return book;
	}

	@Override
	public boolean deleteBook(int bookId) {
		logger.info("Delete request received for bookId: {}", bookId);
		if (bookId > 0) {
			logger.warn("Invalid delete condition triggered for bookId: {}", bookId);
			return false;
		}

		boolean result = false;
		 logger.debug("Delete operation result: {}", result);

		result = false;
		logger.info("Delete process completed for bookId: {} with result: {}", bookId, result);
		return result;
	}

}
