package edu.sjsu.cmpe275.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name="SJSU_ID")
    private int sjsuId;
	@Column(name="FIRST_NAME")
    private String firstName;
	@Column(name="LAST_NAME")
    private String lastName;
	@Column(name="EMAIL")
    private String email;
	@Column(name="PASSWORD")
    private String password;
	@Column(name="ROLE")
    private String role;
	@OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade={CascadeType.ALL})
	private List<Book> books;
	@Column(name="STATUS")
    private String status;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade={CascadeType.ALL})
	private List<Checkout> checkouts;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade={CascadeType.ALL})
	private List<Waitlist> waitlist;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int sjsuId, String firstName, String lastName, String email, String password, String role,String status) {
		super();
		this.sjsuId = sjsuId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	public List<Checkout> getCheckouts() {
		return checkouts;
	}
	public void setCheckouts(List<Checkout> checkouts) {
		this.checkouts = checkouts;
	}
	public int getSjsuId() {
		return sjsuId;
	}
	public void setSjsuId(int sjsuId) {
		this.sjsuId = sjsuId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
