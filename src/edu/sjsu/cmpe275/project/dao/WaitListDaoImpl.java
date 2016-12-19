package edu.sjsu.cmpe275.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;

@Repository
public class WaitListDaoImpl implements WaitListDao{

	@Autowired
	private SessionFactory sessionFactory;

//	@Override
	public void addToWaitlist(WaitList waitlist) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(waitlist);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
	public List<Book> getbooks(int userid) {
		// TODO Auto-generated method stub
		//Gson gson = new Gson();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Book> waitbooks = new ArrayList<Book>();
		User user = null;
		System.out.println("In waitbooks DAo : ");
		List<WaitList> waitlist=new ArrayList<WaitList>();
		try{
			user = (User) session.get(User.class, userid); 
			waitlist.addAll(user.getWaitlists());
			System.out.println("Waitlist being added : ");
			//System.out.println("Waitlist being added : "+ gson.toJson(waitlist));
			for(int i=0;i<waitlist.size();i++)
			{

				System.out.println("Waitbooks being added : "+ waitlist.get(i).getBook().getBookId());
				waitbooks.add(waitlist.get(i).getBook());
			};

			//System.out.println("Waitbooks being added : "+ gson.toJson(waitbooks));
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return waitbooks;
		//return null;
	}
	
	public List<User> getUsers(int bookid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> waitusers = new ArrayList<User>();
		Book book = null;
		try{
			 
			book = (Book) session.get(Book.class, bookid); 
			List<WaitList> waitlist = new ArrayList<WaitList>();
			System.out.println("Book Details :  "+  book.getBookId()+"  "+ book.getWaitlists().toString());
			waitlist.addAll(book.getWaitlists());
			for(int i=0;i<waitlist.size();i++)
			{
				waitusers.add(waitlist.get(i).getUser());
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return waitusers;
		//return null;
	}

	public List<WaitList> getWaitingUsers(int bookid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> waitusers = new ArrayList<User>();
		List<WaitList> waitlist = new ArrayList<WaitList>();
		Book book = null;
		try{
			 
			book = (Book) session.get(Book.class, bookid); 
			waitlist.addAll(book.getWaitlists());
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return waitlist;
		//return null;
	}
	
//	@Override
	public void deleteFromWaitlist(WaitList waitlist) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try{
			session.delete(waitlist);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		
	}

//	@Override
	public User getNextUser(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
