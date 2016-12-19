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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="WAITLIST")
public class WaitList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WAIT_ID")
	private int waitId;
	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	private Book book;
	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	//@JsonIgnore
	private User user;
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	
	public WaitList(){
		
	}
	
	public WaitList(Book book, User user) {
		super();
		this.book = book;
		this.user = user;
	}
	public WaitList(Book book, User user, Date requestDate) {
		super();
		this.book = book;
		this.user = user;
		this.requestDate = requestDate;
	}
	public int getWaitId() {
		return waitId;
	}
	public void setWaitId(int waitId) {
		this.waitId = waitId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}	
	
	
	
}
