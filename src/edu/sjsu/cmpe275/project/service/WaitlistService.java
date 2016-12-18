package edu.sjsu.cmpe275.project.service;


import edu.sjsu.cmpe275.project.model.Waitlist;

public interface WaitlistService {
	
	public void createWaitList(Waitlist waitlist);
	public Waitlist getWaitlist(int sjsuid,int bookid);

}
