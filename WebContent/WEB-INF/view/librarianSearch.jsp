<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>Patron Profile</title>

<script type="text/javascript">
	function changeAction() {
			document.searchForm.action = "${pageContext.request.contextPath}/book/doSearch";
			document.forms["searchForm"].submit();	
	}
	function newBook(sjsuId) {
				location.pathname = "${pageContext.request.contextPath}/book/createBookView/${user.sjsuId}";
	}
	function getBook(bookId) {
		location.pathname = "${pageContext.request.contextPath}/book/getBook/"+bookId;
}
	function changeMethod(action_name) {		
		if (action_name == "") {
			location.pathname = "${pageContext.request.contextPath}/librarian/login/${user.email}/";			
		}		
	}
</script>

</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sjsu Library</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a onclick="changeMethod('')">Home</a></li> 
    </ul>
    <ul class="nav navbar-nav">
      <li class="active"><a onclick="newBook('${user.sjsuId}')">Add New Book</a></li> 
    </ul>
        <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="${pageContext.request.contextPath}/">Log Out</a></li> 
    </ul>
    <form class="navbar-form navbar-left" name="searchForm" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" id="search" name="search">
      </div>
      <button type="button" onclick="changeAction()" class="btn btn-default">Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a onclick="changeMethod('')"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.firstName}</a></li>
      
    </ul>
  </div>
</nav>
<h1>Search Results</h1><br>

	<c:forEach items="${books}" var="current">	
			<div class="col-sm-4">
				<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
				<br>${current.author}
				<br><a onclick="getBook('${current.bookId}')">${current.bookId} CLick to Update/Delete Book</a>
			</div>
		</c:forEach>

</body>
</html>