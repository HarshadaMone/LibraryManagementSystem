package edu.sjsu.cmpe275.project.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name="BOOK")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOOK_ID")
    private int bookId;
	@Column(name="AUTHOR")
    private String author;
	@Column(name="TITLE")
    private String title;
	@Column(name="CALL_NUMBER")
    private int callNumber;
	@Column(name="PUBLISHER")
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
	@Lob
    private Blob image;
	@Column(name="IMAGE_NAME")
	private String imageName;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String author, String title, int callNumber, String publisher, int yearOfPublication, String location,
			int copies, String status, String keyword, Blob image, String imageName) {
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
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	

}
