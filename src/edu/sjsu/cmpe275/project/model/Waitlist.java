package edu.sjsu.cmpe275.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WAITLIST")
public class Waitlist {
	
	@Id
	@Column(name="id")
    private int waitlistId;
	
	

	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	private User user ;
	
	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	private Book book;
	
	public int getWaitlistId() {
		return waitlistId;
	}

	public void setWaitlistId(int waitlistId) {
		this.waitlistId = waitlistId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
