package edu.sjsu.cmpe275.project.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.SendCheckoutEmail;
import edu.sjsu.cmpe275.project.sendReservationEmail;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.Waitlist;

@Repository
public class CheckoutDaoImpl implements CheckoutDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void createcheckout(Checkout checkout) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			checkout.setFlag("false");
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
	public void updateCheckoutDate(Checkout checkout) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			checkout.setFlag("false");
			System.out.println("here");
			session.update(checkout);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
			SendCheckoutEmail.checkoutDateUpdated(checkout.getUser(),checkout.getBook(),checkout);
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
	
	public void setreserve(Waitlist wl)
	{
		System.out.println("ajayajayajays");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			wl.setReserve("TRUE");
			java.util.Calendar cal=java.util.Calendar.getInstance();
			cal.add(Calendar.DATE, 3);
			java.sql.Date now = new Date(cal.getTimeInMillis());
			wl.setDate(now);
			session.update(wl);
			tx.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			tx.rollback();
		}finally {
			session.close();
			sendReservationEmail.sendEmail(wl.getUser().getEmail(), wl.getBook());
		}
	}
	
	public void reserve(int bookid)
	{
		System.out.println("ajayajayajays"+bookid);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql="select * from WAITLIST where BOOK_ID= :bookid having MIN(ID)";
			SQLQuery q=session.createSQLQuery(sql);
			q.setParameter("bookid", bookid);
			q.addEntity(Waitlist.class);
			List l=q.list();
			Iterator i=l.iterator();
			Waitlist wl=null;
			if(i.hasNext())
			{
				wl=(Waitlist)i.next();
				System.out.println("aaaaaa"+wl.getReserve()+"bbb"+wl.getDate());
				
			}
			tx.commit();
			if(wl!=null)
			{
				this.setreserve(wl);
			}
			
		}catch (HibernateException e) {
			// TODO: handle exception
			tx.rollback();
		}finally {
			session.close();
		}
		
	}
	
	public void check(int bookid){
		int a;
		System.out.println("ajayajay"+bookid);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql="SELECT COPIES FROM BOOK WHERE BOOK_ID= :bookid";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("bookid", bookid);
			//System.out.println("ajay"+(new java.sql.Date(System.currentTimeMillis()).toString()));
			//query.setParameter("date", new java.sql.Date(System.currentTimeMillis()));
			
				List l=query.list();
			 a=Integer.parseInt(l.get(0).toString());
			
			tx.commit();
			if(a==1)
			{
				this.reserve(bookid);
			}
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		
	}
	
	public void incbook(int bookid)
	{
		System.out.println("ajay"+bookid);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try{
			String sql="UPDATE BOOK SET COPIES=COPIES+1 where BOOK_ID = :bookid";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("bookid", bookid);
			query.executeUpdate();
			tx.commit();
			this.check(bookid);
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
				if(checkouts.get(i).getFlag().equals("false"))
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

	
	@SuppressWarnings("rawtypes")
	public int getbooksday(int userid) {
		// TODO Auto-generated method stub
		//return null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int a = -1;
		try{
			String sql="SELECT count(*) FROM CHECKOUT WHERE SJSU_ID= :sjsuid and DATE= :date";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("sjsuid", userid);
			System.out.println("ajay"+(new java.sql.Date(System.currentTimeMillis()).toString()));
			query.setParameter("date", new java.sql.Date(System.currentTimeMillis()));
			
				List l=query.list();
			 a=Integer.parseInt(l.get(0).toString());
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return a;
	}

	
	public void returnbook(int userid, int bookid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql="UPDATE CHECKOUT SET returnFlag= :v where BOOK_ID = :bookid and SJSU_ID= :sjsuid ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("bookid", bookid);
			query.setParameter("v", "true");
			query.setParameter("sjsuid", userid);
			query.executeUpdate();
			tx.commit();
			this.incbook(bookid);
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		
		
	}
	

	public List<Checkout> getcheckout(int userid)
	{
		int j=0;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Checkout> ll=new ArrayList<Checkout>();
		try{
			System.out.println(userid);
			String hql = "Select * FROM CHECKOUT where SJSU_ID= :sjsu_id and RETURNFLAG=:returnflag";
			SQLQuery query= session.createSQLQuery(hql);
			
			query.setParameter("sjsu_id", userid);
			query.setParameter("returnflag", "false");
			query.addEntity(Checkout.class);
			List l=query.list();
			for(Iterator i=l.iterator();i.hasNext();)
			{
				ll.add((Checkout) i.next());
			}
			//ll=query.getNamedParameters("");
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return ll;
		
	}
	public Checkout getCheckedOutBook(int sjsuId,int bookId)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Checkout checkout=new Checkout();
		List<Checkout> ll=new ArrayList<Checkout>();
		try{
			SQLQuery query = session.createSQLQuery("SELECT * from CHECKOUT where SJSU_ID= :sjsu_id and RETURNFLAG=:returnflag and BOOK_ID= :book_id");
			query.setParameter("sjsu_id", sjsuId);
			query.setParameter("returnflag", "false");
			query.setParameter("book_id", bookId);
			query.addEntity(Checkout.class);
			List l=query.list();
			for(Iterator i=l.iterator();i.hasNext();)
			{
				ll.add((Checkout) i.next());
			}
			checkout=ll.get(0);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return checkout;
		
	}
	public List<Checkout> getCheckouts() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		List<Checkout> ll=new ArrayList<Checkout>();
		try{
			SQLQuery query = session.createSQLQuery("SELECT * from CHECKOUT where RETURNFLAG=:returnflag");
			query.setParameter("returnflag", "false");
			query.addEntity(Checkout.class);
			List l=query.list();
			for(Iterator i=l.iterator();i.hasNext();)
			{
				ll.add((Checkout) i.next());
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		return ll;
	}
	public void updateFine(Checkout checkout) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			checkout.setFlag("false");
			System.out.println("here");
			session.update(checkout);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
			SendCheckoutEmail.checkoutFine(checkout.getUser(),checkout.getBook(),checkout);
		}
	}
}
