package edu.sjsu.cmpe275.project.service;

import java.util.*;
import edu.sjsu.cmpe275.project.model.Book;


public interface BookService {
	public void createBook(Book book);
	public Book getBook(int id);
	public String deleteBook(int id);
	public int getMaxId();
	public List<Book> getBooks();
}
