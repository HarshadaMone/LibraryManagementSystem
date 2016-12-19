package edu.sjsu.cmpe275.project.service;


import java.sql.Date;
import java.util.Calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.dao.AvailableBooksDao;
import edu.sjsu.cmpe275.project.dao.WaitListDao;
import edu.sjsu.cmpe275.project.model.AvailableBooks;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;

@Service
@Transactional(readOnly=true, propagation = Propagation.REQUIRED)
public class WaitListServiceImpl implements WaitListService{
	
	@Autowired
	private WaitListDao waitListDao;
	
	@Autowired
	private AvailableBooksDao availableBooksDao;
	

	//@Override
	public void addToWaitlist(WaitList w) {
		// TODO Auto-generated method stub
		waitListDao.addToWaitlist(w);
	}



	//@Override
	public List<Book> getBooks(int userId) {
		// TODO Auto-generated method stub
		List<Book> books = waitListDao.getbooks(userId);
		return books;
	}



	//@Override
	public List<User> getUsers(int bookId) {
		// TODO Auto-generated method stub

		List<User> users = waitListDao.getUsers(bookId);
		return users;
		
	}


	@Override
	public void removeFromWaitlist(WaitList w) {
		// TODO Auto-generated method stub
		waitListDao.deleteFromWaitlist(w);		
	}



	@Override
	public void getNextUser(int bookId) {
		// TODO Auto-generated method stub
		List<WaitList> waitlist =  waitListDao.getWaitingUsers(bookId);
		int id = waitlist.get(0).getWaitId();
		int index =0;
		for(int i=1;i<waitlist.size();i++)
		{	
			if(id> waitlist.get(i).getWaitId()){
				id = waitlist.get(i).getWaitId();
				index = i;
			}
		}
		System.out.println("Id to be deleted : "+ id);
		Book b = waitlist.get(index).getBook();
		User u = waitlist.get(index).getUser();
		WaitList nextUser = waitlist.get(index);
		java.util.Calendar cal=java.util.Calendar.getInstance();
		java.sql.Date now = (java.sql.Date) new Date(cal.getTimeInMillis());
		cal.add(Calendar.DATE, 3);
		Date dateby = new Date(cal.getTimeInMillis());
		AvailableBooks av = new AvailableBooks(b, u, now, dateby);
		availableBooksDao.makeAvailable(av);
		waitListDao.deleteFromWaitlist(nextUser);
		
	}

	

	
	
}
