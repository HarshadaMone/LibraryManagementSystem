package edu.sjsu.cmpe275.project.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.sjsu.cmpe275.project.model.Book;

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

public void indexBooks()
{
   try
   {
      Session session = sessionFactory.getCurrentSession();
   
      FullTextSession fullTextSession = Search.getFullTextSession(session);
      fullTextSession.createIndexer().startAndWait();
   }
   catch(Exception e)
   {
   }
}

public List<Book> searchForBook(String searchText)
{
   try
   {
      Session session = sessionFactory.openSession();
      
      FullTextSession fullTextSession = Search.getFullTextSession(session);
      QueryBuilder qb = fullTextSession.getSearchFactory()
        .buildQueryBuilder().forEntity(Book.class).get();
      org.apache.lucene.search.Query query = qb
        .keyword().onFields("author", "title", "publisher")
        .matching(searchText)
        .createQuery();

      org.hibernate.Query hibQuery = 
         fullTextSession.createFullTextQuery(query, Book.class);

      List<Book> results = hibQuery.list();
      return results;
   }
   catch(Exception e)
   {
      throw e;
   }
}

}
