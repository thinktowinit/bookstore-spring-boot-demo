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
	private static final Logger log = LoggerFactory.getLogger(BookStoreService.class);
@Autowired
	private IBookStoreDAO dao;

	@Override
	public List<Book> getBooks() {

		List<Book> books = dao.getBooks();

		if (books.size() > 0) {
			System.out.println("Books found");
		}

		books = new ArrayList<>();

		return books;
	}

	@Override
	public Book createBook(Book book) {
		log.info("Service: createBook started");
		return dao.createBook(book);
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
