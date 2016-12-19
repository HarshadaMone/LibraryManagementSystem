package edu.sjsu.cmpe275.project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.Startable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import edu.sjsu.cmpe275.project.model.AvailableBooks;
import edu.sjsu.cmpe275.project.service.AvailableBooksService;

@Service
@Transactional
public class ThreadForAvailability extends Thread {


	@Autowired
	private AvailableBooksService availableBooksService;
	
	public ThreadForAvailability(){
	
		start();
		
	}
	
	public void run(){
		
		
		try {
			
			System.out.println("Thread is running");
			List<AvailableBooks> booksToRemove = new ArrayList<AvailableBooks>() ;
				
				
				booksToRemove= availableBooksService.checkDate();
				System.out.println("Size of books to be removed "+booksToRemove.size());
				if(booksToRemove !=null){
					
					availableBooksService.removeAllUserBook(booksToRemove);
				}
			
			sleep(1000*60*2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
}
