package edu.sjsu.cmpe275.project.service;

import edu.sjsu.cmpe275.project.model.User;


public interface UserService {
	public void createUser(User user);
	public User getUser(int sjsuId);
	public User getUser(String email);
	public String deleteUser(int sjsuId);
}
