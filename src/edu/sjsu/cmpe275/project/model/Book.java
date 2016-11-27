package edu.sjsu.cmpe275.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;





@Entity
@Table(name="BOOK")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOOK_ID")
    private int bookId;
	@Column(name="AUTHOR")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String author;
	@Column(name="TITLE")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String title;
	@Column(name="CALL_NUMBER")
    private int callNumber;
	@Column(name="PUBLISHER")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String publisher;
	@Column(name="YEAR_OF_PUBLICATION")
    private int yearOfPublication;
	@Column(name="LOCATION")
    private String location;
	@Column(name="COPIES")
    private int copies;
	@Column(name="STATUS")
    private String status;
	@Column(name="KEYWORD")
    private String keyword;
	@Column(name="IMAGE")
    private String image;
	@Column(name="IMAGE_NAME")
	private String imageName;
	@ManyToOne
	@JoinColumn(name="SJSU_ID")
	private User user;
	
	public Book() {
		super();
	}
	public Book(String author, String title, int callNumber, String publisher, int yearOfPublication, String location,
			int copies, String status, String keyword, String image, String imageName) {
		super();
		this.author = author;
		this.title = title;
		this.callNumber = callNumber;
		this.publisher = publisher;
		this.yearOfPublication = yearOfPublication;
		this.location = location;
		this.copies = copies;
		this.status = status;
		this.keyword = keyword;
		this.image = image;
		this.imageName = imageName;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(int callNumber) {
		this.callNumber = callNumber;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
