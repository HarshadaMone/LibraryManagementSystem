package edu.sjsu.cmpe275.project.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;

public interface CheckoutDao {

	public void createcheckout(Checkout checkout);
	public List<Book> getbooks(int userid);
	public int getbooksday(int userid);
	public List<Date> getdates(int userid);
	public void returnbook(int userid,int bookid);
	public List<Checkout> getcheckout(int userid);
	public Checkout getCheckedOutBook(int userId,int bookId);
	public void updateCheckoutDate(Checkout checkout);
}
