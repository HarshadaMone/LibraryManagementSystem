package edu.sjsu.cmpe275.project.dao;

import java.util.List;

import edu.sjsu.cmpe275.project.model.AvailableBooks;

public interface AvailableBooksDao {

	public void makeAvailable(AvailableBooks av);
	public void makeUnavailable(AvailableBooks av);
	public List<AvailableBooks> getAvailableBooks();
	public List<AvailableBooks> getBooksForUser(int userId);
	
}
