<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sjsu Library</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="#">Home</a></li> 
      <li><a class="active" onclick="changeAction('book')" >Books</a></li>
      <li><a href="#">Return Book</a></li>
    </ul>
    <form class="navbar-form navbar-left" name="searchForm" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" id="search" name="search">
      </div>
      <button type="button" onclick="changeAction('search')" class="btn btn-default">Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${user.firstName }</a></li>
      
    </ul>
  </div>
</nav>
<h1> Books Available in Library</h1><br>
<c:forEach items="${books}" var="current">	
			<div class="col-sm-4">
				<img src="<c:url value="/resources/${current.imageName}" />" alt="" width="200" height="200" />
				<br>${current.author}
				<br><a onclick="changeAction('${current.bookId}')">${current.bookId}</a>
			</div>
		</c:forEach>
</body>
</html>