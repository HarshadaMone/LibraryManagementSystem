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
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.Waitlist;

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
	
	public List<Checkout> getcheckout(int userid)
	{
		return checkoutDao.getcheckout(userid);
	}

	
	public void returnbook(int userid, int bookid) {
		// TODO Auto-generated method stub
		checkoutDao.returnbook(userid, bookid);
		
	}
	public Checkout getCheckedOutBook(int userId,int bookId)
	{
		return checkoutDao.getCheckedOutBook(userId, bookId);
	}
	public void updateCheckoutDate(Checkout checkout)
	{
		checkoutDao.updateCheckoutDate(checkout);
	}
	public List<Checkout> getCheckouts()
	{
		return checkoutDao.getCheckouts();
	}
	public void updateFine(Checkout checkout)
	{
		checkoutDao.updateFine(checkout);
	}



	
	public void getreserve(int bookid) {
		// TODO Auto-generated method stub
		
	}



	
	public void setreserve(Waitlist wl) {
		// TODO Auto-generated method stub
		checkoutDao.setreserve(wl);
		
	}
}
