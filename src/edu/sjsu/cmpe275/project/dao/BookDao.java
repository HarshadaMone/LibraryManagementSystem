package edu.sjsu.cmpe275.project.dao;

import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;


public interface BookDao {
	public void createBook(Book book);
	public Book getBook(int id);
	public void deleteBook(Book book);
	public int getMaxId();
	public List<Book> searchForBook(String searchText);
	public List<Book> getBooks();
	}

