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

import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.Waitlist;


@Repository
public class WaitlistDaoImpl implements WaitlistDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void createWaitList(Waitlist waitlist){
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
	
	public Waitlist getWaitlist(int sjsuid,int bookid)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Waitlist waitlist=null;
		try{
			String hql="select * from WAITLIST where (SJSU_ID = :sjsuid and BOOK_ID = :bookid)";
			SQLQuery q=session.createSQLQuery(hql);
			q.setParameter("sjsuid", sjsuid);
			q.setParameter("bookid", bookid);
			q.addEntity(Waitlist.class);
			Iterator i=q.list().iterator();
			if(i.hasNext())
			{
			waitlist=(Waitlist) i.next();
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return waitlist;
	}

	@Override
	public List<Waitlist> getWaitlist(int bookid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Waitlist> waitlist=null;
		try{
			String hql="select * from WAITLIST where (BOOK_ID = :bookid)";
			SQLQuery q=session.createSQLQuery(hql);
			//q.setParameter("sjsuid", sjsuid);
			q.setParameter("bookid", bookid);
			q.addEntity(Waitlist.class);
			Iterator i=q.list().iterator();
			while(i.hasNext())
			{
			waitlist.add((Waitlist) i.next());
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return waitlist;
	}
	public int getWaitlistCountFromBookId(int bookId)
	{
		int j=0;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Waitlist> ll=new ArrayList<Waitlist>();
		try{
			System.out.println(bookId);
			String hql = "Select * FROM WAITLIST where BOOK_ID= :book_id";
			SQLQuery query= session.createSQLQuery(hql);
			
			query.setParameter("book_id", bookId);
			query.addEntity(Waitlist.class);
			List l=query.list();
			for(Iterator i=l.iterator();i.hasNext();)
			{
				ll.add((Waitlist) i.next());
			}
			//ll=query.getNamedParameters("");
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return ll.size();
		
	}

}
