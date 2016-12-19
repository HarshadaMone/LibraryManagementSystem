package edu.sjsu.cmpe275.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AVAILABLE")
public class AvailableBooks {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AVAIL_ID")
	private int availId;
	
	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	private User user;
	
	@Column(name="DATE_AVAILABLE")
	private Date availableFrom;
	
	@Column(name="AVAILABLE_TILL")
	private Date availableTo;
	
	public AvailableBooks(){
		
	}
	
	
	public AvailableBooks(Book book, User user, Date availableFrom, Date availableTo) {
		super();
		this.book = book;
		this.user = user;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
	}
	public int getAvailId() {
		return availId;
	}
	public void setAvailId(int availId) {
		this.availId = availId;
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
	public Date getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}
	public Date getAvailableTo() {
		return availableTo;
	}
	public void setAvailableTo(Date availableTo) {
		this.availableTo = availableTo;
	}
	
	
}
