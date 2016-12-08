package edu.sjsu.cmpe275.project.controller;




import java.io.StringReader;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.project.SendEmail;
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Books;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.Data;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.service.BookService;
import edu.sjsu.cmpe275.project.service.CheckoutService;
import edu.sjsu.cmpe275.project.service.UserService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Controller
@RequestMapping("/patron")
public class PatronController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CheckoutService checkoutService;

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
		List<Book> books=bookService.getBooks();
		model.addAttribute("books", books);
		model.addAttribute("user", user);
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
	
	@RequestMapping(method=RequestMethod.POST,value="/login",produces={"text/html"})
	public String loginPatron(@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model,HttpServletResponse res) throws SQLException{
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );   
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
			if(user.getStatus().equals("InActive")){				
				return "activationError";
			}
			else{
			if((user.getPassword()).equals(password))
			{
				System.out.println("true");
				List<Book> books=bookService.getBooks();
				model.addAttribute("books", books);
				model.addAttribute("user", user);
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
				model.addAttribute("user", user);
				model.addAttribute("books", books);
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
				List<Checkout> c=checkoutService.getcheckout(sjsuId);
				if(cbooks.size()>=10)
				{
					return "limit";
				}
				
				else{
					if(num>=5)
					{
						return "day limit";
					}
					else{
						System.out.println(c.size());
				for (int i = 0; i < data1.getBooks().size(); i++) {
					for(int j=0;j<c.size();j++)
					{
						System.out.println(data1.getBooks().get(i).getId());
						System.out.println(c.get(j).getBook().getBookId());
						System.out.println(sjsuId);
						System.out.println(c.get(j));
						if(data1.getBooks().get(i).getId()==c.get(j).getBook().getBookId() && sjsuId==c.get(j).getUser().getSjsuId())
						{
							data1.getBooks().remove(i);
							break;
						}
					}
					if(data1.getBooks().size()>0)
					{
					Book book=bookService.getBook(data1.getBooks().get(i).getId());
					Checkout checkout=new Checkout(returndate, now, 0);
					checkout.setBook(book);
					checkout.setUser(user);
					checkoutService.createcheckout(checkout);
					}
					else
					{
						return "checkoutpage";
					}
				}
				}
				}
				//List<Books> book=data.getBooks();
				System.out.println("ajay");
				return "checkoutpage";
		
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
		//System.out.println(books.get(0).getBookId());
		
		rd=checkoutService.getdates(sjsuId);
		System.out.println(rd.get(0));
		model.addAttribute("rd", rd);
		return "checkoutpage";
		
		
	//	return null;
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/return/{sjsuId}",produces={"text/html"})
	public String returnpage(@PathVariable int sjsuId,Model model,HttpServletResponse res) throws SQLException{
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );
		User user=userService.getUser(sjsuId);
		List<Book> books=checkoutService.getbooks(sjsuId);
		List<Date> rd=new ArrayList<Date>();
		model.addAttribute("books", books);
		model.addAttribute("user", user);
		//System.out.println(books.get(0).getBookId());
		
		rd=checkoutService.getdates(sjsuId);
		System.out.println(rd.get(0));
		model.addAttribute("rd", rd);
		return "return";
		
		
	//	return null;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/returnbook/{sjsuId}",produces={"text/html"})
	@ResponseBody
	public String returnbook(@RequestParam(value="myArray") String books,
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
				
				for (int i = 0; i < data1.getBooks().size(); i++) {
					checkoutService.returnbook(sjsuId, data1.getBooks().get(i).getId());
				}
				
				
				//List<Books> book=data.getBooks();
				System.out.println("ajay");
				return "return";
		
	}

	

}
