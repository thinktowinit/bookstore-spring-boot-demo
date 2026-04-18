package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IBookStoreDAO;
import com.bookstore.entity.Book;

@Service
public class BookStoreService implements IBookStoreService {

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

		System.out.println(book.getName());

		

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
