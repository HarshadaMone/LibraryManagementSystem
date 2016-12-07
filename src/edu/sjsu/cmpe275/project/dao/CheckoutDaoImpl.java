package edu.sjsu.cmpe275.project.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.SendCheckoutEmail;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;

@Repository
public class CheckoutDaoImpl implements CheckoutDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void createcheckout(Checkout checkout) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			System.out.println("here");
			session.saveOrUpdate(checkout);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
			SendCheckoutEmail.sendEmail(checkout.getUser().getEmail(),checkout.getBook());
			decbook(checkout.getBook().getBookId());
		}
	}
	
	public void decbook(int bookid)
	{
		System.out.println("ajay"+bookid);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try{
			String sql="UPDATE BOOK SET COPIES=COPIES-1 where BOOK_ID = :bookid";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("bookid", bookid);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
	}

	
	public List<Book> getbooks(int userid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Book> books = new ArrayList<Book>();
		User user = null;
		List<Checkout> checkouts=new ArrayList<Checkout>();
		try{
			user = (User) session.get(User.class, userid);
			checkouts.addAll(user.getCheckouts());
			for(int i=0;i<checkouts.size();i++)
			{
				books.add(checkouts.get(i).getBook());
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return books;
		//return null;
	}
	
	public List<Date> getdates(int userid){
		Session session = sessionFactory.openSession();
		List<Date> dates=new ArrayList<Date>();
		Transaction tx = session.beginTransaction();
		List<Book> books = new ArrayList<Book>();
		User user = null;
		List<Checkout> checkouts=new ArrayList<Checkout>();
		try{
			user = (User) session.get(User.class, userid);
			checkouts.addAll(user.getCheckouts());
			for(int i=0;i<checkouts.size();i++)
			{
				dates.add(checkouts.get(i).getReturnDate());
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return dates;
		
	}
	

}
