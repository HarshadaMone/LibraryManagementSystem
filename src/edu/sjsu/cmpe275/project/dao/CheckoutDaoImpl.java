package edu.sjsu.cmpe275.project.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
			
		}
	}

	public List<Book> getBooks(int sjsuId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Book b;
		List<Book> books = new ArrayList<Book>();
		List<Checkout> ckl = new ArrayList<Checkout>();
		User user;
		try{
				user = (User) session.get(User.class, sjsuId);	
				ckl.addAll(user.getCheckouts());
				for (int i = 0; i < ckl.size(); i++) {
					b = ckl.get(i).getBook();
					books.add(b);
				}
				tx.commit();
			}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return books;
	}
	
	public void returnedBooks(List<Book> books, int sjsuId ){
		
			List <Checkout> ckl = null;
			Checkout ck;
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try{
				 for(int i = 0; i<books.size();i++){ 
					 ckl = (List<Checkout>) session.get(Checkout.class, books.get(i).getBookId());
					 for(int j =0 ; j< ckl.size();j++){
						 if(ckl.get(j).getUser().getSjsuId() == sjsuId){
							 ck = ckl.get(j);
							 session.delete(ck);
						 }
						 
					 }
					
				 }
				tx.commit();
			}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
	}
	
	public void returnedBooksFlag(List<Book> books, int sjsuId ){
		
		List <Checkout> ckl = null;
		Checkout ck;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			 for(int i = 0; i<books.size();i++){ 
				 ckl = (List<Checkout>) session.get(Checkout.class, books.get(i).getBookId());
				 for(int j =0 ; j< ckl.size();j++){
					 if(ckl.get(j).getUser().getSjsuId() == sjsuId){
						 ck = ckl.get(j);
						 ck.setReturnFlag("true");
						 session.saveOrUpdate(ck);
						 
					 }
					 
				 }
				
			 }
			tx.commit();
		}catch(HibernateException e){
		tx.rollback();
		throw e;
	}finally{
		session.close();
	}
	
	
}
	

}
