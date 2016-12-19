package edu.sjsu.cmpe275.project.controller;





import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.model.Volume.VolumeInfo;
import com.sun.xml.internal.ws.transport.http.ResourceLoader;

import ch.qos.logback.core.net.SyslogOutputStream;
import edu.sjsu.cmpe275.project.bookapi.copy.GoogleBooksAPI;
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
		model.addAttribute("user",userService.getUser(sjsuId));
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
		User user=userService.getUser(sjsuId);
		String image=null;
		try {
			byte[] encodeBase64 = Base64.encodeBase64(file.getBytes());
			image= new String(encodeBase64, "UTF-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String imageName=file.getOriginalFilename();
		Book book= new Book(author,title,callNumber,publisher,yearOfPublication,location,copies,status,keyword,image,imageName);
		book.setUser(user);
		bookService.createBook(book);
	    model.addAttribute("image",image);	
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
	//Request Mapping for delete book
	@RequestMapping(method=RequestMethod.POST,value="/updateBook/{bookId}",produces={"text/html"})
	@ResponseBody
	public String updateBook(@PathVariable int bookId, 
			@RequestParam("sjsuId") int sjsuId,
			@RequestParam("author") String author,
			@RequestParam("title") String title,
			@RequestParam("callNumber") int callNumber,
			@RequestParam("publisher") String publisher,
			@RequestParam("yearOfPublication") int yearOfPublication,
			@RequestParam("location") String location,	
			@RequestParam("copies") int copies,
			@RequestParam("status") String status,
			@RequestParam("keyword") String keyword,
			Model model,HttpServletResponse res){
		Book book=bookService.getBook(bookId);
		book.setAuthor(author);
		book.setTitle(title);
		book.setCallNumber(callNumber);
		book.setPublisher(publisher);
		book.setYearOfPublication(yearOfPublication);
		book.setLocation(location);
		book.setCopies(copies);
		book.setStatus(status);
		book.setKeyword(keyword);
		bookService.createBook(book);
	    model.addAttribute("image",book.getImage());	
		model.addAttribute("book",book);		
		return "book";
	
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
	    model.addAttribute("image",book.getImage());	
		model.addAttribute("book",book);
		return "book";
		
	}
	 @RequestMapping(value = "/doSearch", method = RequestMethod.POST)
	   public String search(
	      @RequestParam("search")
	      String search,
	   Model model) 
	   {
	      List<Book> books = bookService.searchForBook(search);
	      
	      model.addAttribute("books", books);
	      return "librarianSearch";
	   }
	 @RequestMapping(value = "patron/doSearch", method = RequestMethod.POST)
	   public String searchPatron(
	      @RequestParam("search")
	      String search,
	   Model model) 
	   {
	      List<Book> books = bookService.searchForBook(search);
	      
	      model.addAttribute("books", books);
	      return "patronSearch";
	   }
		@RequestMapping(method = RequestMethod.POST, value = "/isbnbook/{sjsuId}/{isbn}", produces={"text/html"})
		public String createBookByIsbn(@PathVariable("sjsuId") int sjsuId, @PathVariable("isbn") String isbn,
				Model model, HttpServletRequest req){
			/*@PathVariable int sjsuId,*/
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			Book book=new Book();
			GoogleBooksAPI gba = new GoogleBooksAPI(); 
			//User user;
		      String prefix = null;
		      String query =isbn;
		          prefix = "isbn:";
		      if (prefix != null) {
		        query = prefix + query;
		      }

		        try {
					VolumeInfo volumeInfo=gba.queryGoogleBooks(jsonFactory, query);
					book.setAuthor(volumeInfo.getAuthors().get(0));
					book.setPublisher(volumeInfo.getPublisher());
					book.setTitle(volumeInfo.getTitle());
					book.setImage(volumeInfo.getImageLinks().getThumbnail());
					System.out.println("image: "+volumeInfo.getImageLinks().getThumbnail());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("sjsuId",sjsuId);
				model.addAttribute("user",userService.getUser(sjsuId));
		        model.addAttribute("book", book);
		        return "createBookisbn";

		    }

			
		}
	 
