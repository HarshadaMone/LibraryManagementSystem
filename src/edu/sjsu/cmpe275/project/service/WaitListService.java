
package edu.sjsu.cmpe275.project.service;

import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;

public interface WaitListService {

	public void addToWaitlist(WaitList w);
	//public void removeFromaWaitlist();
	public List<Book> getBooks(int userId);
	public List<User> getUsers(int bookId);
	public void getNextUser(int bookId);
	public void removeFromWaitlist(WaitList w);
	
}
