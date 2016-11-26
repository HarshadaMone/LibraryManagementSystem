package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.model.User;


public interface UserDao {
	public void createUser(User user);
	public User getUser(int id);
	public void deleteUser(User user);
}

