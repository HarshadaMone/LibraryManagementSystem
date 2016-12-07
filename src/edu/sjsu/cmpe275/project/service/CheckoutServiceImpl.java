package edu.sjsu.cmpe275.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.dao.CheckoutDao;
import edu.sjsu.cmpe275.project.model.Checkout;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private CheckoutDao checkoutDao;


	public void createcheckout(Checkout checkout) {
		// TODO Auto-generated method stub
		checkoutDao.createcheckout(checkout);
	}

}
