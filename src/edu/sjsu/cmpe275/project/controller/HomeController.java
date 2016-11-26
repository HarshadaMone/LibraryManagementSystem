package edu.sjsu.cmpe275.project.controller;






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
	
	@RequestMapping(method=RequestMethod.GET,value="/signUpAsLibrarian",produces={"text/html"})
	public String getSignUpAsLibrarianView(){
		return "signUpLibrarian";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/signUpAsPatron",produces={"text/html"})
	public String getSignUpAsPatronView(){
		return "signUpPatron";
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
