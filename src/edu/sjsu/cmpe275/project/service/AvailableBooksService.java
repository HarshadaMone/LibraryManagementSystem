package edu.sjsu.cmpe275.project.service;

import java.util.List;

import edu.sjsu.cmpe275.project.model.AvailableBooks;

public interface AvailableBooksService {

	public List<AvailableBooks> getUserBooks(int userId); 
	public List<AvailableBooks> getAllBooks();
	public void removeUserBook(AvailableBooks av);
	public List<AvailableBooks> checkDate();
	public void addToAvailble(AvailableBooks av);
	public void removeAllUserBook(List<AvailableBooks> booksToRemove );
}
