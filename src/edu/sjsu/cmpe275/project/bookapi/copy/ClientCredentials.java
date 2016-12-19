package edu.sjsu.cmpe275.project.bookapi.copy;

public class ClientCredentials {
	  /** Value of the "API key" shown under "Simple API Access". */
	  static final String API_KEY =
	      "AIzaSyBGx2Jq3TyXQL0o6fr01rQQbw2l1wjLefo";

	  static void errorIfNotSpecified() {
	    if (API_KEY.startsWith("Enter ")) {
	      System.err.println(API_KEY);
	      System.exit(1);
	    }
	  }
}
