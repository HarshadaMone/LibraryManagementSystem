package edu.sjsu.cmpe275.project.dao;


import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;

public interface WaitListDao {

	public void addToWaitlist(WaitList waitlist);
	public List<Book> getbooks(int userid);
	public List<User> getUsers(int bookid);
	public void deleteFromWaitlist(WaitList waitlist);
	public User getNextUser(int bookId);
	public List<WaitList> getWaitingUsers(int bookid);
	
	
}
