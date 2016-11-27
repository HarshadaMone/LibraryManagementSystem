package edu.sjsu.cmpe275.project.controller;




import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.User;
import edu.sjsu.cmpe275.project.service.UserService;


@Controller
@RequestMapping("/librarian")
public class LibrarianController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,value="/signUp/{sjsuId}",produces={"text/html"})
	public String createLibrarian(@PathVariable int sjsuId,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) throws SQLException{
		String role="librarian";
		User user= new User(sjsuId,firstName,lastName,email,password,role);
		userService.createUser(user);
		model.addAttribute("user",user);		
		return "user";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login/{email}",produces={"text/html"})
	public String loginLibrarian(@RequestParam("email") String email,
			@RequestParam("password") String password,
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
			if((user.getPassword()).equals(password))
			{
				System.out.println("true");
				List<Book> books=userService.getBooks(user.getSjsuId());
				model.addAttribute("user", user);
				model.addAttribute("books", books);
				return "librarian";
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


}
