package edu.sjsu.cmpe275.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.SendCheckoutEmail;
import edu.sjsu.cmpe275.project.model.Checkout;

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

}
