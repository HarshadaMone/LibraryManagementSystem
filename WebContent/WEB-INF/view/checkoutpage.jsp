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
<script>
$(document).ready(function(){
console.log("${books}");
console.log("${reserved}");
});
function changeMethod(action_name) {		
	if (action_name == "") {
		location.pathname = "${pageContext.request.contextPath}/patron/login/${user.email}/";			
	}		
}
</script>

<title>CheckOut Page</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sjsu Library</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a onclick="changeMethod('')">Home</a></li>
      <li ><a href="${pageContext.request.contextPath}/patron/return/${user.sjsuId}">Return Book</a></li> 
    </ul>
    <form class="navbar-form navbar-left" name="searchForm" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" id="search" name="search">
      </div>
      <button type="button" onclick="changeAction()" class="btn btn-default">Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a onclick="changeMethod('')"><span class="glyphicon glyphicon-user"></span> ${user.firstName }</a></li>
      
    </ul>
  </div>
</nav>
<div class="container-fluid">
<h1> Checkedout Books</h1><br>

	<c:forEach items="${books}" var="current" varStatus="status">	
			<div class="col-sm-4">
				<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
				<br>${current.title}
				<br>${current.author}
				<br>return Date :${rd[status.index]}
				</div>
	</c:forEach>
</div>
	<c:choose>
		<c:when test="${not empty waitbooks }">
		<div class="container-fluid">
			<h1> Books added to waitlist in this transaction</h1><br>
			<c:forEach items="${waitlistbooks}" var="current" varStatus="status">	
				<div class="col-sm-4">
					<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
					<br>${current.title}
					<br>${current.author}
					
					</div><br>
			</c:forEach>
			</div>
		</c:when>
		<c:when test="${not empty alreadybooks || not empty reserved}">
		<div class="container-fluid">
			<h1> Books not checked out because they are either already with you or reserved for someone</h1><br>
			<c:forEach items="${alreadybooks}" var="current" varStatus="status">	
				<div class="col-sm-4">
					<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
					<br>${current.title}
					<br>${current.author}
					
					</div>
			</c:forEach>
			<c:forEach items="${reserved}" var="current" varStatus="status">	
				<div class="col-sm-4">
					<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
					<br>${current.title}
					<br>${current.author}
					
					</div>
			</c:forEach>
			</div>
		</c:when>
	</c:choose>



</body>
</html>