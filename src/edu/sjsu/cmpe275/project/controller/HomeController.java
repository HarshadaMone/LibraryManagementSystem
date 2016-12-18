package edu.sjsu.cmpe275.project.controller;






import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(method=RequestMethod.GET,value="",produces={"text/html"})
	public String getHomeView(){
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/signUpUser",produces={"text/html"})
	public String getSignUpAsLibrarianView(){
		return "signUpUser";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/loginLibrarian",produces={"text/html"})
	public String getLoginLibrarianView(){
		return "loginLibrarian";
	}

	@RequestMapping(method=RequestMethod.GET,value="/loginPatron",produces={"text/html"})
	public String getLoginPatronView(){
		return "loginPatron";
	}
	
}
