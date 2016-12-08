package edu.sjsu.cmpe275.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name="CHECKOUT")
public class Checkout {

	@Id
	@Column(name="id")
    private int checkoutId;
	
	@Column(name="returndate")
    private Date returnDate;
	
	@Column(name="date")
    private Date date;
	
	@Column(name="fine")
    private int fine;
	
	@Column(name="returnFlag")
    private String flag;
	
	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	private User user ;
	
	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	private Book book;
	
	
	
	public Checkout()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Checkout(Date returnDate, Date date, int fine) {
		super();
		this.returnDate = returnDate;
		this.date = date;
		this.fine = fine;
		
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
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
