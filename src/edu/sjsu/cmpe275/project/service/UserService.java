package edu.sjsu.cmpe275.project.service;


import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;


public interface UserService {
	public void createUser(User user);
	public User getUser(int sjsuId);
	public User getUser(String email);
	public String deleteUser(int sjsuId);
	public List<Book> getBooks(int sjsuId);
}
