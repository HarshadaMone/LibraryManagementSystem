package edu.sjsu.cmpe275.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.dao.WaitlistDao;

import edu.sjsu.cmpe275.project.model.Waitlist;


@Service
@Transactional
public class WaitlistServiceImpl implements WaitlistService {

	@Autowired
	private WaitlistDao waitlistDao;
	
	public void createWaitList(Waitlist waitlist) {
		waitlistDao.createWaitList(waitlist);

	}

	
	public Waitlist getWaitlist(int sjsuid, int bookid) {
		// TODO Auto-generated method stub
		return waitlistDao.getWaitlist(sjsuid, bookid);
	}


	
	public List<Waitlist> getWaitlist(int bookid) {
		// TODO Auto-generated method stub
		
		return waitlistDao.getWaitlist(bookid);
	}

}
