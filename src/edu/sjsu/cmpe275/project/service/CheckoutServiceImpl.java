package edu.sjsu.cmpe275.project.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.dao.CheckoutDao;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private CheckoutDao checkoutDao;


	public void createcheckout(Checkout checkout) {
		// TODO Auto-generated method stub
		checkoutDao.createcheckout(checkout);
	}


	
	public List<Book> getbooks(int userid) {
		// TODO Auto-generated method stub
		List<Book> books=checkoutDao.getbooks(userid);
		return books;
	}



	
	public List<Date> getdates(int userid) {
		List<Date> dates=checkoutDao.getdates(userid);
		return dates;
	}


	public int getbooksday(int userid) {
		// TODO Auto-generated method stub
		return checkoutDao.getbooksday(userid);
	}

}
