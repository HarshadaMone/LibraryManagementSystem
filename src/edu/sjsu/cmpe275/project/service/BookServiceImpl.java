package edu.sjsu.cmpe275.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.sjsu.cmpe275.project.dao.BookDao;
import edu.sjsu.cmpe275.project.model.Book;

/**
 * service - transactional
 *
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	//create profile service method
	public void createBook(Book book) {
		bookDao.createBook(book);
	}

	//get profile service method
	public Book getBook(int id) {
		return bookDao.getBook(id);
	}

	//delete profile service method
	public String deleteBook(int id) {
		
		//getting the profile of the person to be deleted
		Book book = bookDao.getBook(id);
		if(book == null){
			return "false";
		}
		
		bookDao.deleteBook(book);
		return "true";
	}
	public int getMaxId() {		 
		return bookDao.getMaxId();
	}
	public List<Book> searchForBook(String searchText){
		return bookDao.searchForBook(searchText);
	}
	public void indexBooks(){
		bookDao.indexBooks();
	}
}