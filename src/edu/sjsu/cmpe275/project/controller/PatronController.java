package edu.sjsu.cmpe275.project.controller;




import java.sql.SQLException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.List;
import java.util.Random;
>>>>>>> refs/remotes/origin/master

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

<<<<<<< HEAD
=======
import edu.sjsu.cmpe275.project.SendEmail;
>>>>>>> refs/remotes/origin/master
import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.service.BookService;
import edu.sjsu.cmpe275.project.service.UserService;


@Controller
@RequestMapping("/patron")
public class PatronController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
<<<<<<< HEAD
	
=======

>>>>>>> refs/remotes/origin/master
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
		model.addAttribute("user",user);		
		return "user";
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
	
	@RequestMapping(method=RequestMethod.GET,value="/books",produces={"text/html"})
	public String getbooks(Model model,HttpServletResponse res){
		List<Book> books = new ArrayList<Book>();
		books=bookService.getBooks();
		if(books==null){
			res.setStatus(404);
			//model.addAttribute("id",sjsuId);
			model.addAttribute("res",res.getStatus());
			return "error";
		}
		model.addAttribute("books",books);
		return "showBooks";
		
	}


}
