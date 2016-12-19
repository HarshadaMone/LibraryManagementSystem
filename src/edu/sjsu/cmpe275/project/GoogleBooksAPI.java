package edu.sjsu.cmpe275.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sjsu.cmpe275.project.bookapi.BookAPIObj;
import edu.sjsu.cmpe275.project.bookapi.Items;
import edu.sjsu.cmpe275.project.dao.BookDaoImpl;
import edu.sjsu.cmpe275.project.model.Book;


public class GoogleBooksAPI {

	 		
			long isbn;
			
			
			public GoogleBooksAPI(long isbn){
				
				this.isbn = isbn;
			}
	
	        public Book getBookInfo(){
	       	
	        //long isbn = 	;
			String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn;
	        
	        try{
	            HttpURLConnection connection = null;
	            // Build Connection.
	            try{
	                URL url = new URL(apiUrlString);
	                connection = (HttpURLConnection) url.openConnection();
	                connection.setRequestMethod("GET");
	                connection.setReadTimeout(5000); // 5 seconds
	                connection.setConnectTimeout(5000); // 5 seconds
	            } catch (MalformedURLException e) {
	                // Impossible: The only two URLs used in the app are taken from string resources.
	                e.printStackTrace();
	            } catch (ProtocolException e) {
	                // Impossible: "GET" is a perfectly valid request method.
	                e.printStackTrace();
	            }
	            int responseCode = connection.getResponseCode();
	            if(responseCode != 200){
	              //  Log.w(getClass().getName(), "GoogleBooksAPI request failed. Response Code: " + responseCode);
	                connection.disconnect();
	                return null;
	            }

	            // Read data from response.
	            StringBuilder builder = new StringBuilder();
	            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line = responseReader.readLine();
	            while (line != null){
	                builder.append(line);
	                line = responseReader.readLine();
	            }
	            String responseString = builder.toString();
	            
	            System.out.println("Json received :" + responseString);
	            //Log.d(getClass().getName(), "Response String: " + responseString);
	            JSONObject responseJson = new JSONObject(responseString);
	            // Close connection and return response code.
	            connection.disconnect();
	            System.out.println("JSON Onject : "+ responseJson);
	            
	            ObjectMapper mapper = new ObjectMapper();
	            
	            BookAPIObj bao = mapper.readValue(responseJson.toString(),BookAPIObj.class);
	            Items[] items = bao.getItems();
	            
	            int i = 0;
	            	
	            	Book b = new Book();
	            	String[] authors = items[i].getVolumeInfo().getAuthors();
	            	b.setAuthor(authors[0]);
	            	String title = items[i].getVolumeInfo().getTitle();
	            	b.setTitle(title);
	            	String publisher = items[i].getVolumeInfo().getPublisher();
	            	b.setPublisher(publisher);
	            	String[] category = items[i].getVolumeInfo().getCategories();
	            	b.setKeyword(category[0]);
	            	String year = items[i].getVolumeInfo().getPublishedDate();
	            	if(Integer.parseInt(year)>0){
	            	b.setYearOfPublication(Integer.parseInt(year));
	            	}
	            	else
	            	{
	            		b.setYearOfPublication(1998);
	            	}
	            	b.setCopies(1);
	            	BookDaoImpl bdi = new BookDaoImpl();
	            	System.out.println("Book: "+ b.getTitle() + "\n Authors:" + b.getAuthor() + "\n Keyword" + b.getKeyword());
	            
	            	//bdi.createBook(b);
	            	
	            
	            return b;
	        } catch (SocketTimeoutException e) {
	            //Log.w(getClass().getName(), "Connection timed out. Returning null");
	            return null;
	        } catch(IOException e){
	            //Log.d(getClass().getName(), "IOException when connecting to Google Books API.");
	            e.printStackTrace();
	            return null;
	        } catch (JSONException e) {
	          //log.d(getClass().getName(), "JSONException when connecting to Google Books API.");
	            e.printStackTrace();
	            return null;
	        }
	    }

}



