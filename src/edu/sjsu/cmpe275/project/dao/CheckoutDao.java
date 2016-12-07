package edu.sjsu.cmpe275.project.dao;

import java.sql.Date;
import java.util.List;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;

public interface CheckoutDao {

	public void createcheckout(Checkout checkout);
	public List<Book> getbooks(int userid);
	public List<Date> getdates(int userid);
}
