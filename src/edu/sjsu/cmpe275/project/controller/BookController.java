package edu.sjsu.cmpe275.project.controller;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.service.BookService;
import edu.sjsu.cmpe275.project.service.UserService;


@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value="/createBookView/{sjsuId}",produces={"text/html"})
	public String getCreateBookView(@PathVariable int sjsuId,
			Model model){
		model.addAttribute("sjsuId",sjsuId);
		return "createBook";
	}	//Request Mapping for create book
	
	@RequestMapping(method=RequestMethod.POST,value="/createBook/{sjsuId}",produces={"text/html"})
	public String createBook(@PathVariable int sjsuId,
			@RequestParam("author") String author,
			@RequestParam("title") String title,
			@RequestParam("callNumber") int callNumber,
			@RequestParam("publisher") String publisher,
			@RequestParam("yearOfPublication") int yearOfPublication,
			@RequestParam("location") String location,	
			@RequestParam("copies") int copies,
			@RequestParam("status") String status,
			@RequestParam("keyword") String keyword,
			@RequestParam("image") MultipartFile file,
			Model model) throws SQLException{
		Blob image=null;
		User user=userService.getUser(sjsuId);
		try {
			image = Hibernate.createBlob(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int blobLength = (int) image.length();  
		String imageName=file.getOriginalFilename();
		Book book= new Book(author,title,callNumber,publisher,yearOfPublication,location,copies,status,keyword,image,imageName);
		book.setUser(user);
		bookService.createBook(book);
        FileOutputStream fos=null;
		try {
			File newFile = new File("/Users/siddharthgupta/Documents/workspace2/LibraryManagementSystem/WebContent/resources/"+file.getOriginalFilename());
			if (!newFile.exists()) {
			    newFile.createNewFile();
			   }
			fos = new FileOutputStream(newFile);
	        fos.write(file.getBytes());
	        fos.close();
	        TimeUnit.SECONDS.sleep(3);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("book",book);		
		return "book";
	}
	
	//Request Mapping for delete book
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteBook/{bookId}",produces={"text/html"})
	@ResponseBody
	public String deleteBook(@PathVariable int bookId, Model model,HttpServletResponse res){
		
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );   
		String isBookDeleted = bookService.deleteBook(bookId);
		if(isBookDeleted=="true")
			return "book";
		return "book"+bookId;	
	}
	@RequestMapping(method=RequestMethod.GET,value="/getBook/{bookId}",produces={"text/html"})
	public String getBook(@PathVariable int bookId,Model model,HttpServletResponse res){
		Book book = bookService.getBook(bookId);
		if(book==null){
			res.setStatus(404);
			model.addAttribute("id",bookId);
			model.addAttribute("res",res.getStatus());
			return "error";
		}
        FileOutputStream fos=null;
		try {
			File newFile = new File("/Users/siddharthgupta/Documents/workspace2/LibraryManagementSystem/WebContent/resources/"+book.getImageName());
			if (!newFile.exists()) {
			    newFile.createNewFile();
			   }
			fos = new FileOutputStream(newFile);
			int blobLength = (int) book.getImage().length();
	        fos.write(book.getImage().getBytes(1, blobLength));
	        fos.close();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("book",book);
		return "book";
		
	}

}
