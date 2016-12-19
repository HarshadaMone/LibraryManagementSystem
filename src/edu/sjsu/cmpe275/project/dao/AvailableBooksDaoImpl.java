package edu.sjsu.cmpe275.project.dao;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.model.AvailableBooks;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;

@Repository
public class AvailableBooksDaoImpl implements AvailableBooksDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void makeAvailable(AvailableBooks av) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(av);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		
	}

	
	public void makeUnavailable(AvailableBooks av) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try{
			System.out.println("in available Books delete to delete book with id : "+ av.getAvailId());
			session.delete(av);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<AvailableBooks> getAvailableBooks() {
		// TODO Auto-generated method stub
	
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

		
			List<AvailableBooks> results = null;
			try{
				java.util.Calendar cal=java.util.Calendar.getInstance();
				java.sql.Date now = new Date(cal.getTimeInMillis());
				String hql = "FROM AvailableBooks where availableTo != :nowdate";
				Query query = session.createQuery(hql);
				query.setParameter("nowdate", now);
				results=query.list();
			
				 /*results = session.createQuery("FROM AVAILABLE").list(); */
				tx.commit();
			}catch(HibernateException e){
				tx.rollback();
			}finally{
				session.close();
			}
			
			for(int i=0; i<results.size();i++){
				
				System.out.println("In for loop for fetching books in Query DAo :" + results.get(i).getBook().getBookId());
				
			}
			return results;
		
	}

	@Override
	public List<AvailableBooks> getBooksForUser(int userId) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			User user = null;
			List<AvailableBooks> availbooks = new ArrayList<AvailableBooks>();
			try{
				user = (User) session.get(User.class, userId); 
				availbooks.addAll(user.getAvailableBooks());
				tx.commit();
			}catch(HibernateException e){
				tx.rollback();
				throw e;
			}finally{
				session.close();
			}
			return availbooks;
	}

	
}
