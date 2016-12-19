package edu.sjsu.cmpe275.project.service;


import java.util.List;

import edu.sjsu.cmpe275.project.model.Waitlist;

public interface WaitlistService {
	
	public void createWaitList(Waitlist waitlist);
	public Waitlist getWaitlist(int sjsuid,int bookid);
	public List<Waitlist> getWaitlist(int bookid);
	public int getWaitlistCountFromBookId(int bookId);
}
