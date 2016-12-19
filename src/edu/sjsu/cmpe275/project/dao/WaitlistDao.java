package edu.sjsu.cmpe275.project.dao;


import java.util.List;

import edu.sjsu.cmpe275.project.model.Waitlist;

public interface WaitlistDao {

	public void createWaitList(Waitlist waitlist);
	public Waitlist getWaitlist(int sjsuid,int bookid);
	public List<Waitlist> getWaitlist();
	public void delete(Waitlist w);
	public List<Waitlist> getWaitlist(int bookid);
	public int getWaitlistCountFromBookId(int bookId);
}
