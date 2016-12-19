package edu.sjsu.cmpe275.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WAITLIST")
public class Waitlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int waitlistId;
	
	@Column(name="date")
    private Date date;
	
	@Column(name="RESERVE")
    private String reserve;
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	private User user ;
	
	

	public Waitlist() {
		super();
		// TODO Auto-generated constructor stub
	}

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
