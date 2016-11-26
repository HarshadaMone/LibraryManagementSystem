package edu.sjsu.cmpe275.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.sjsu.cmpe275.project.dao.UserDao;
import edu.sjsu.cmpe275.project.model.User;

/**
 * service - transactional
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void createUser(User user) {
		userDao.createUser(user);
	}

	public User getUser(int sjsuId) {
		return userDao.getUser(sjsuId);
	}

	public String deleteUser(int sjsuId) {
		
		User user = userDao.getUser(sjsuId);
		if(user == null){
			return "false";
		}
		
		userDao.deleteUser(user);
		return "true";
	}
}