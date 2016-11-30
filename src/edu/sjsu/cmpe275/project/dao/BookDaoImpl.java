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
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;


/**
 * 
 Profile Dao Implementation
 *
 */
@Repository
public class BookDaoImpl implements BookDao{

	//Session Factory object
	@Autowired
	private SessionFactory sessionFactory;
	
	//Implementation for profile creation
	public void createBook(Book book) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(book);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	//Implementation for profile retrieval
	public Book getBook(int bookId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Book book = null;
		try{
			book = (Book) session.get(Book.class, bookId);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return book;
	}

	//Implementation for profile deletion
	public void deleteBook(Book book) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try{
			session.delete(book);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
public int getMaxId() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int id=0;
		try{
			Query query = session.createQuery("SELECT max(id) FROM Book book");
			if((Integer)query.iterate().next()!=null){
			id=(Integer)query.iterate().next();}
			System.out.println("Max bookId: "+id);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return id;
	}

@Override
public List<Book> getBooks() {
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	List<Book>  books=new ArrayList<Book>();
	try{
		String sql="SELECT * FROM BOOK WHERE COPIES>1";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Book.class);
		//query.setParameter("email", email);
		//user = (User) session.get(User.class, email);
		books = query.list();
		 for (Iterator iterator = 
				 books.iterator(); iterator.hasNext();){
			 books.add((Book) iterator.next()); 
		 }
		tx.commit();
	}catch(HibernateException e){
		tx.rollback();
	}finally{
		session.close();
	}
	System.out.println("aaaa"+books.get(0));
	return books;
}

}
