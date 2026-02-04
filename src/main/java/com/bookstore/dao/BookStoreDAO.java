package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;

@Transactional
@Repository
public class BookStoreDAO implements IBookStoreDAO {
	
	 private static final Logger logger = LoggerFactory.getLogger(BookStoreDAO.class);
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all books available in database and return it as List<Book>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		logger.info("DAO → Fetching all books from database");
		
		String hql = "FROM Book as atcl ORDER BY atcl.id";
		 List<Book> books=  entityManager.createQuery(hql).getResultList();
		 logger.info("DAO → Total books fetched: {}", books.size());
		 return books;
	}

	/**
	 * This method is responsible to get a particular Book detail by given book id 
	 */
	@Override
	public Book getBook(int bookId) {
		logger.info("DAO → Fetching book with ID: {}", bookId);
		  Book book = entityManager.find(Book.class, bookId);
		  if (book == null) {
	            logger.warn("DAO → No book found with ID: {}", bookId);
	        } else {
	            logger.info("DAO → Book found: ID={}, Name={}", book.getId(), book.getName());
	        }
		  return book;
	}

	/**
	 * This method is responsible to create new book in database
	 */
	@Override
	public Book createBook(Book book) {
		logger.info("DAO → Creating new book");
        logger.debug("DAO → Book data: Name={}, Author={}, Category={}, Price={}",
                book.getName(), book.getAuthor(), book.getCategory(), book.getPrice());
		entityManager.persist(book);
		 entityManager.flush();
		Book savedBook = getLastInsertedBook();
		logger.info("DAO → Book created successfully with ID: {}", savedBook.getId());
		return savedBook;
	}

	/**
	 * This method is responsible to update book detail in database
	 */
	@Override
	public Book updateBook(int bookId, Book book) {
		logger.info("DAO → Updating book with ID: {}", bookId);
		//First We are taking Book detail from database by given book id and 
		// then updating detail with provided book object
		Book bookFromDB = getBook(bookId);
		  if (bookFromDB == null) {
	            logger.error("DAO → Cannot update. Book not found with ID: {}", bookId);
	            return null;
	        }
		  logger.debug("DAO → Old Data → Name={}, Price={}",
	                bookFromDB.getName(), bookFromDB.getPrice());
		bookFromDB.setName(book.getName());
		bookFromDB.setAuthor(book.getAuthor());
		bookFromDB.setCategory(book.getCategory());
		bookFromDB.setPublication(book.getPublication());
		bookFromDB.setPages(book.getPages());
		bookFromDB.setPrice(book.getPrice());
		
		entityManager.flush();
		
		//again i am taking updated result of book and returning the book object
		Book updatedBook = getBook(bookId);
		
		logger.info("DAO → Book updated successfully with ID: {}", bookId);
        logger.debug("DAO → New Data → Name={}, Price={}",
                updatedBook.getName(), updatedBook.getPrice());
		return updatedBook;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteBook(int bookId) {
		 logger.info("DAO → Deleting book with ID: {}", bookId);
		Book book = getBook(bookId);
		if (book == null) {
            logger.warn("DAO → Delete failed. Book not found with ID: {}", bookId);
            return false;
        }
		entityManager.remove(book);
		  entityManager.flush();
		
		//we are checking here that whether entityManager contains earlier deleted book or not
		// if contains then book is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(book);
		if(status){
			logger.error("DAO → Book deletion failed for ID: {}", bookId);
			return false;
		}
		 logger.info("DAO → Book deleted successfully with ID: {}", bookId);
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Book class
	 * @return book
	 */
	private Book getLastInsertedBook(){
		 logger.debug("DAO → Fetching last inserted book");
		String hql = "from Book order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Book book = (Book)query.getSingleResult();
		logger.debug("DAO → Last inserted book ID: {}", book.getId());
		return book;
	}

}