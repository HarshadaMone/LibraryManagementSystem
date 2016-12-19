package edu.sjsu.cmpe275.project.controller;

import java.io.StringReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import edu.sjsu.cmpe275.project.SendEmail;
import edu.sjsu.cmpe275.project.ThreadForAvailability;
import edu.sjsu.cmpe275.project.model.AvailableBooks;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.Data;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.model.WaitList;
import edu.sjsu.cmpe275.project.service.AvailableBooksService;
import edu.sjsu.cmpe275.project.service.BookService;
import edu.sjsu.cmpe275.project.service.CheckoutService;
import edu.sjsu.cmpe275.project.service.UserService;
import edu.sjsu.cmpe275.project.service.WaitListService;

@Controller
@RequestMapping("/patron")
public class PatronController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CheckoutService checkoutService;
	
	@Autowired
	private WaitListService waitListService;
	
	@Autowired
	private AvailableBooksService availableBooksService;
	
	ThreadForAvailability t = new ThreadForAvailability();


	@RequestMapping(method=RequestMethod.POST,value="/signUp/{sjsuId}",produces={"text/html"})
	public String createPatron(@PathVariable int sjsuId,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) throws SQLException{
		Random rnd = new Random();
		int activationCode = 100000 + rnd.nextInt(900000);
		String role="patron";
		String status="InActive";
		User user= new User(sjsuId,firstName,lastName,email,password,role,status);
		userService.createUser(user);
		String mailTo = email;
		String mailFrom = "siddharth6258@gmail.com";	 
		SendEmail.sendEmail(mailTo, mailFrom,activationCode);
		model.addAttribute("user",user);
		model.addAttribute("activationCode",activationCode);
		return "activation";
	}
	@RequestMapping(method=RequestMethod.POST,value="/activatePatron/{sjsuId}",produces={"text/html"})
	public String activatePatron(@PathVariable int sjsuId,
			Model model) throws SQLException{
		User user = userService.getUser(sjsuId);
		user.setStatus("Active");
		userService.createUser(user);
		List<Book> waitbooks = waitListService.getBooks(user.getSjsuId());
		System.out.println("Waitbooks"+ waitbooks.toString());
		
		List<Book> books=bookService.getBooks();
		model.addAttribute("books", books);
		model.addAttribute("user", user);
		model.addAttribute("waitbooks",waitbooks);
		return "patron";
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteUser/{sjsuId}",produces={"text/html"})
	@ResponseBody
	public String deleteLibrarian(@PathVariable int sjsuId, Model model,HttpServletResponse res){
		
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );   
		String isUserDeleted = userService.deleteUser(sjsuId);
		if(isUserDeleted=="true")
			return "signUpAsPatron";
		return "user"+sjsuId;	
	}
	@RequestMapping(method=RequestMethod.GET,value="/getUser/{sjsuId}",produces={"text/html"})
	public String getLibrarian(@PathVariable int sjsuId,Model model,HttpServletResponse res){
		User user = userService.getUser(sjsuId);
		if(user==null){
			res.setStatus(404);
			model.addAttribute("id",sjsuId);
			model.addAttribute("res",res.getStatus());
			return "error";
		}
		model.addAttribute("user",user);
		return "user";
		
	}
	
	@SuppressWarnings("null")
	@RequestMapping(method=RequestMethod.POST,value="/login",produces={"text/html"})
	public String loginPatron(@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model,HttpServletResponse res) throws SQLException{
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );   
		System.out.println("email :"+email);
		User user = userService.getUser(email);
		//Gson gson = new Gson();
		if(user==null){
			System.out.println("no user");
			res.setStatus(404);
			model.addAttribute("id",email);
			model.addAttribute("res",res.getStatus());
			return "error";
		}
		else{
			if(user.getStatus().equals("InActive")){				
				return "activationError";
			}
			else{
			if((user.getPassword()).equals(password))
			{
				System.out.println("Inside ");
				System.out.println("In patron/login");
				List<Book> waitbooksList = waitListService.getBooks(user.getSjsuId());
				List<Book> books=bookService.getBooks();
				List<AvailableBooks> availbookslist = availableBooksService.getUserBooks(user.getSjsuId());
				int[] waitbooks = new int[50];
				int[] availbooks = new int[50];
				//==========================================
				
				/*java.util.Calendar cal=java.util.Calendar.getInstance();
				java.sql.Date now = new Date(cal.getTimeInMillis());
				cal.add(Calendar.DATE, -3);
				Date returndate=new Date(cal.getTimeInMillis());
				AvailableBooks av = new AvailableBooks(books.get(0),user,returndate,now);
				System.out.println("Add to avail");
				for(int k =0 ; k<5; k++){
					System.out.println("Adding records to available iteration: " + k);
					availableBooksService.addToAvailble(av);
				}*/
				//List<AvailableBooks> booksToRemove = availableBooksService.checkDate();
				/*for(int i=0; i<booksToRemove.size();i++){
					
					//waitbooks[i]= waitbooksList.get(i).getBookId();
					System.out.println("In for loop for fetching books to remove :" + booksToRemove.get(i).getBook().getBookId());
					availableBooksService.removeUserBook(booksToRemove.get(i));
					
				}
			
				//availableBooksService.removeAllUserBook(booksToRemove);
				System.out.println("Delete");
				availableBooksService.removeUserBook(av);*/
				//=========================================
				
				for(int i=0; i<waitbooksList.size();i++){
					
					waitbooks[i]= waitbooksList.get(i).getBookId();
					System.out.println("In for loop for fetching books :" +waitbooksList.get(i).getBookId());
				}
				
				for(int i=0; i<availbookslist.size();i++){
					
					availbooks[i]= availbookslist.get(i).getBook().getBookId();
					System.out.println("In for loop for fetching books :" +availbookslist.get(i).getBook().getBookId());
				}
				
				for(int i=0; i<books.size();i++){
					
					for(int j=0; j<availbookslist.size();j++){
						
						if(books.get(i).getBookId() == availbooks[j]){
							books.remove(books.get(i));
						}
					}
				}
				
				System.out.println(Arrays.toString(waitbooks));
				System.out.println("Waitbooks that I fetch on login" );
				model.addAttribute("books", books);
				model.addAttribute("user", user);
				model.addAttribute("waitbooks",waitbooks);
				model.addAttribute("availbooks",availbooks);
				return "patron";
			}
			else
			{
				System.out.println("wrong pass");
				res.setStatus(404);
				model.addAttribute("id","worng password");
				model.addAttribute("res",res.getStatus());
				return "error";
			}
		}
	
		}
	}


	
	
				

	
	@RequestMapping(method=RequestMethod.GET,value="/login/{email}/",produces={"text/html"})
	public String getPatronHome(@PathVariable String email,
			Model model,HttpServletResponse res) throws SQLException{
		//Gson gson = new Gson();
		System.out.println("email :"+email);
		User user = userService.getUser(email);
		if(user==null){
			System.out.println("no user");
			res.setStatus(404);
			model.addAttribute("id",email);
			model.addAttribute("res",res.getStatus());
			return "error";
		}
		else{
				System.out.println("true");
				List<Book> books=bookService.getBooks();
				List<Book> waitbooksList1 = waitListService.getBooks(user.getSjsuId());
				int[] waitbooks1 = null;
				for(int i=0; i<waitbooksList1.size();i++){
					
					waitbooks1[i]= waitbooksList1.get(i).getBookId();
				}
				System.out.println("Waitbooks" /*+ gson.toJson(waitbooks1)*/);
				System.out.println(Arrays.toString(waitbooks1));
				model.addAttribute("user", user);
				model.addAttribute("books", books);
				model.addAttribute("waitbooks",waitbooks1);
				return "patron";
		}
	
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/checkout/{sjsuId}",produces={"text/html"})
	@ResponseBody
	public String checkout(@RequestParam(value="myArray") String books,
			@PathVariable int sjsuId,
			Model model,HttpServletResponse res) throws SQLException{
		//int sjsuid=Integer.parseInt(sjsuId);
				res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
				res.addHeader( "Access-Control-Allow-Origin", "*" );
				Gson gson=new Gson();
				System.out.println(books);
				books="{books:"+books+"}";
				JsonReader reader = new JsonReader(new StringReader(books));
				reader.setLenient(true);
				Data data1=new Gson().fromJson(reader, Data.class);
				//System.out.println(data.getId());
				System.out.println(data1.getBooks());
				System.out.println(data1.getBooks().get(0).getId());
				User user=userService.getUser(sjsuId);
				java.util.Calendar cal=java.util.Calendar.getInstance();
				java.sql.Date now = new Date(cal.getTimeInMillis());
				cal.add(Calendar.DATE, 30);
				Date returndate=new Date(cal.getTimeInMillis());
				List<Book> cbooks=new ArrayList<Book>();
				cbooks=checkoutService.getbooks(sjsuId);
				int num=checkoutService.getbooksday(sjsuId);
				System.out.println("a"+num);
				if(cbooks.size()>=10)
				{
					return "limit";
				}
				
				else{
					
				for (int i = 0; i < data1.getBooks().size(); i++) {
					Book book=bookService.getBook(data1.getBooks().get(i).getId());
					if(book.getAvailableCopies()>0){
					book.setAvailableCopies(book.getAvailableCopies()-1);
						if(book.getAvailableCopies()==0){
							book.setStatus("Unavailable");
						}
					bookService.createBook(book);
					Checkout checkout=new Checkout(returndate, now, 0);
					checkout.setBook(book);
					checkout.setUser(user);
					checkoutService.createcheckout(checkout);
					}
					
				}
				}
				//List<Books> book=data.getBooks();
				System.out.println("ajay");
				return "checkoutpage";
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/addToWaitList/{sjsuId}",produces={"text/html"})
	@ResponseBody
	public String addToWaitList(@RequestParam(value="book") String bookNo,
			@PathVariable int sjsuId,
			Model model,HttpServletResponse res) throws SQLException{
				System.out.println("I am here..");
				res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
				res.addHeader( "Access-Control-Allow-Origin", "*" );
				System.out.println("Book Id to wish list"+bookNo);
				int bookId = Integer.parseInt(bookNo);
				User user = userService.getUser(sjsuId);
				Book book = bookService.getBook(bookId);
				java.util.Calendar cal=java.util.Calendar.getInstance();
				java.sql.Date now = new Date(cal.getTimeInMillis());
				WaitList w = new WaitList(book, user, now);
				waitListService.addToWaitlist(w);
				return "patron";			
	}
	
	@SuppressWarnings("null")
	@RequestMapping(method=RequestMethod.GET,value="/availList/{sjsuId}",produces={"text/html"})
	public String availList(
			@PathVariable int sjsuId,
			Model model,HttpServletResponse res) throws SQLException{
				System.out.println("I am here availlist");
				res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
				res.addHeader( "Access-Control-Allow-Origin", "*" );
				List<Book> waitbooksList = waitListService.getBooks(sjsuId);
				List<Book> orgbooks=bookService.getBooks();
				List<Book> books = new ArrayList<Book>();
				User user = userService.getUser(sjsuId);
				List<AvailableBooks> availbookslist = availableBooksService.getUserBooks(sjsuId);
				int[] waitbooks = new int[50];
				int[] availbooks = new int[50];
				
				for(int i=0; i<waitbooksList.size();i++){
					
					waitbooks[i]= waitbooksList.get(i).getBookId();
					System.out.println("In for loop for fetching books :" +waitbooksList.get(i).getBookId());
				}
				
				for(int i=0; i<availbookslist.size();i++){
					
					availbooks[i]= availbookslist.get(i).getBook().getBookId();
					System.out.println("In for loop for fetching books :" +availbookslist.get(i).getBook().getBookId());
				}
				
				for(int i=0; i<orgbooks.size();i++){
					
					for(int j=0; j<availbookslist.size();j++){
						
						if(orgbooks.get(i).getBookId() == availbooks[j]){
							books.add(orgbooks.get(i));
						}
					}
				}
				
				System.out.println("Out Of available" + Arrays.toString(waitbooks));
				System.out.println("Waitbooks that I fetch on login" );
				model.addAttribute("books", books);
				model.addAttribute("user", user);
				model.addAttribute("waitbooks",waitbooks);
				model.addAttribute("availbooks",availbooks);
				
				return "available";
	}
	
	
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/checkoutpage/{sjsuId}",produces={"text/html"})
	public String checkoutpage(@PathVariable int sjsuId,Model model,HttpServletResponse res) throws SQLException{
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );
		User user=userService.getUser(sjsuId);
		List<Book> books=checkoutService.getbooks(sjsuId);
		List<Date> rd=new ArrayList<Date>();
		model.addAttribute("books", books);
		model.addAttribute("user", user);
		System.out.println(books.get(0).getBookId());
		
		rd=checkoutService.getdates(sjsuId);
		System.out.println(rd.get(0));
		model.addAttribute("rd", rd);
		return "checkoutpage";
		
		
	//	return null;
		
	}


}
