package edu.sjsu.cmpe275.project.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.dao.AvailableBooksDao;
import edu.sjsu.cmpe275.project.model.AvailableBooks;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;

@Service
//@Transactional(propagation = Propagation.REQUIRED)
@Transactional
public class AvailableBookServiceImpl implements AvailableBooksService{
	@Autowired
	private AvailableBooksDao availableBooksDao;
	
	@Autowired
	private WaitListService waitListService;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public List<AvailableBooks> getUserBooks(int userId) {
		// TODO Auto-generated method stub
		List<AvailableBooks> availableForUser = availableBooksDao.getBooksForUser(userId);
		return availableForUser;
	}

	@Override
	public List<AvailableBooks> getAllBooks() {
		// TODO Auto-generated method stub
		List<AvailableBooks> availablebooks = availableBooksDao.getAvailableBooks();
		return availablebooks;
	}

	@Override
	public void removeUserBook(AvailableBooks av ) {
		// TODO Auto-generated method stub
		System.out.println("In make unavailble : available service");
		availableBooksDao.makeUnavailable(av);
		List<User> waitingUsers = waitListService.getUsers(av.getBook().getBookId());
		if(waitingUsers != null){
			waitListService.getNextUser(av.getBook().getBookId());
		}else{
			
			Book book = bookService.getBook(av.getBook().getBookId());
			book.setStatus("Available");
			book.setAvailableCopies(book.getAvailableCopies()+1);
			bookService.createBook(book);
			//makeStatus of book Available 
			//copies+1
		}
	}


	@Override
	public List<AvailableBooks> checkDate() {
		// TODO Auto-generated method stub
		System.out.println("Date block : ");
		List<AvailableBooks> allBooks =  availableBooksDao.getAvailableBooks();
		List<AvailableBooks> booksToRemove = new ArrayList<AvailableBooks>() ;
		java.util.Calendar cal=java.util.Calendar.getInstance();
		java.sql.Date now = new Date(cal.getTimeInMillis());
		System.out.println("Date : "+ now);
		System.out.println("Array Size : "+ allBooks.size());
		for(int i = 0; i< allBooks.size();i++){
			System.out.println("Date compared with : "+allBooks.get(i).getAvailableTo());
			if(now.compareTo(allBooks.get(i).getAvailableTo())<=0){
			
				System.out.println("Date matched with id  : "+allBooks.get(i).getAvailId());
				booksToRemove.add(allBooks.get(i));
			}
			
		}
		return booksToRemove;
	}

	@Override
	public void addToAvailble(AvailableBooks av) {
		// TODO Auto-generated method stub
		availableBooksDao.makeAvailable(av);
		System.out.println("Book added to vailable table:Out of AvailableBooksService ");
	}

	public void removeAllUserBook(List<AvailableBooks> booksToRemove ) {
		// TODO Auto-generated method stub
		System.out.println("In make all books unavailble : available service");
		AvailableBooks av = new AvailableBooks();	
		for(int i=0; i<booksToRemove.size();i++){
			
			av = booksToRemove.get(i);
			System.out.println("In for loop for fetching books to removing :" + booksToRemove.get(i).getBook().getBookId());
			availableBooksDao.makeUnavailable(booksToRemove.get(i));
			
		
		   
			//availableBooksDao.makeUnavailable(av);
		
			List<User> waitingUsers = waitListService.getUsers(av.getBook().getBookId());
			if(waitingUsers != null){
				waitListService.getNextUser(av.getBook().getBookId());
			}else{
				
				Book book = bookService.getBook(av.getBook().getBookId());
				book.setStatus("Available");
				book.setAvailableCopies(book.getAvailableCopies()+1);
				bookService.createBook(book);
				//makeStatus of book Available 
				//copies+1
			}
		}
	}
	
}
