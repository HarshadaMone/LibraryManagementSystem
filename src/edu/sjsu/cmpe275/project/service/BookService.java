package edu.sjsu.cmpe275.project.service;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.List;

>>>>>>> refs/remotes/origin/master
import edu.sjsu.cmpe275.project.model.Book;


public interface BookService {
	public void createBook(Book book);
	public Book getBook(int id);
	public String deleteBook(int id);
	public int getMaxId();
<<<<<<< HEAD
=======
	public List<Book> searchForBook(String searchText);
>>>>>>> refs/remotes/origin/master
	public List<Book> getBooks();
}
