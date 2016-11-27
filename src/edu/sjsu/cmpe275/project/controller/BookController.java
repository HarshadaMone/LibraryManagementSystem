package edu.sjsu.cmpe275.project.controller;





import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
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
	   Model model) throws Exception
	   {
	      List<Book> allFound = bookService.searchForBook(search);
	      List<Book> books = new ArrayList<Book>();
	      
	      for (Book b : allFound)
	      {
	         Book bm = new Book();
	         bm.setAuthor(b.getAuthor());
	         bm.setTitle(b.getTitle());
	         bm.setPublisher(b.getPublisher());
	         
	         books.add(bm);
	      }
	      model.addAttribute("foundBooks", books);
	      return "librarian";
	   }

}
