package edu.sjsu.cmpe275.project.dao;


import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;

import java.util.*;


/**
 * 
 Profile Dao Implementation
 *
 */
@Repository
public class UserDaoImpl implements UserDao{

	//Session Factory object
	@Autowired
	private SessionFactory sessionFactory;
	
	//Implementation for profile creation
	public void createUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(user);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	//Implementation for profile retrieval
	public User getUser(int sjsuId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user = null;
		try{
			user = (User) session.get(User.class, sjsuId);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return user;
	}
	
	public User getUser(String email) {
		System.out.println(email);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user = null;
		try{
			String sql="SELECT * FROM USER WHERE EMAIL = :email";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("email", email);
			//user = (User) session.get(User.class, email);
			List users = query.list();
			 for (Iterator iterator = 
					 users.iterator(); iterator.hasNext();){
     user= (User) iterator.next(); 
			 }
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		System.out.println(email+"asdasd"+user);
		return user;
	}

	//Implementation for profile deletion
	public void deleteUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try{
			session.delete(user);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	public List<Book> getBooks(int sjsuId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Book> books = new ArrayList<Book>();
		User user = null;
		try{
			user = (User) session.get(User.class, sjsuId);		
			books.addAll(user.getBooks());
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return books;
	}


}
