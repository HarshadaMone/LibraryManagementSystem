package edu.sjsu.cmpe275.project.dao;


import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;


public interface UserDao {
	public void createUser(User user);
	public User getUser(int id);
	public User getUser(String email);
	public void deleteUser(User user);
	public List<Book> getBooks(int sjsuId);
}

