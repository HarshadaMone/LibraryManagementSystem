package edu.sjsu.cmpe275.project.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;

public interface CheckoutService {
	
	public void createcheckout(Checkout checkout);
	public List<Book> getbooks(int userid);
	public List<Date> getdates(int userid);
	public BigInteger getbooksday(int userid);
	
}
