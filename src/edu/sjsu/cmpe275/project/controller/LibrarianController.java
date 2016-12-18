package edu.sjsu.cmpe275.project.controller;




import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.service.BookService;
import edu.sjsu.cmpe275.project.service.CheckoutService;
import edu.sjsu.cmpe275.project.service.UserService;


@Controller
@RequestMapping("/librarian")
public class LibrarianController {
	@Autowired
	private UserService userService;
	@Autowired
	private CheckoutService checkoutService;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/signUp/{sjsuId}",produces={"text/html"})
	public String createLibrarian(@PathVariable int sjsuId,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) throws SQLException{
		String role="librarian";
		String status="Active";
		User user= new User(sjsuId,firstName,lastName,email,password,role,status);
		userService.createUser(user);
		List<Book> books=userService.getBooks(user.getSjsuId());
		model.addAttribute("user", user);
		model.addAttribute("books", books);
		return "librarian";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login",produces={"text/html"})
	public String loginLibrarian(
		@RequestParam("email") String email,
		@RequestParam("password") String password,
		Model model,HttpServletResponse res,HttpServletRequest req) throws SQLException{
		System.out.println("email :"+email);
		User user = userService.getUser(email);
		if(user==null || user.getRole().equals("patron")){
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
				if((user.getPassword()).equals(password)){	
					System.out.println("true");
					req.getSession().setAttribute("user", user);
					List<Book> books=userService.getBooks(user.getSjsuId());
					model.addAttribute("user", user);
					model.addAttribute("books", books);
					return "librarian";
				}
				else{
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
	public String getLibrarianHome(@PathVariable String email,
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
				List<Book> books=userService.getBooks(user.getSjsuId());
				model.addAttribute("user", user);
				model.addAttribute("books", books);
				return "librarian";
		}
	
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteUser/{sjsuId}",produces={"text/html"})
	@ResponseBody
	public String deleteLibrarian(@PathVariable int sjsuId, Model model,HttpServletResponse res){
		
		res.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
		res.addHeader( "Access-Control-Allow-Origin", "*" );   
		String isUserDeleted = userService.deleteUser(sjsuId);
		if(isUserDeleted=="true")
			return "signUpAsLibrarian";
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
	 @RequestMapping(value = "/changeDate", method = RequestMethod.POST)
	   public String changeDate(
			   @RequestParam("datepicker") String date,
	   Model model,HttpServletRequest req) 
	   {
	      System.out.println("Current date is : "+date);
	      SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        String dateInString = date;
	        List<Checkout> checkouts=new ArrayList<Checkout>();
	        try {
	            Date date1 = (Date) formatter.parse(dateInString);
	            System.out.println(date1); 
	            checkouts=checkoutService.getCheckouts();
	            for(Checkout checkout : checkouts)
	            {
	            	int diffInDays = (int)( (date1.getTime() - checkout.getReturnDate().getTime()) 
	                        / (1000 * 60 * 60 * 24) );
	            	if(diffInDays >0)
	            	{
	            		checkout.setFine(diffInDays);
	            		checkoutService.updateFine(checkout);
	            	}
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	      User user=(User) req.getSession().getAttribute("user");
	      List<Book> books=userService.getBooks(user.getSjsuId());
	      model.addAttribute("user", user);
	      model.addAttribute("books", books);
		  return "librarian";
	   }

}
