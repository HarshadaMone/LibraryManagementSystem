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

<script type="text/javascript">
	function changeAction() {
			document.searchForm.action = "${pageContext.request.contextPath}/search/"+document.getElementById("search").value;
			document.forms["searchForm"].submit();	
	}
	function newBook(sjsuId) {
				location.pathname = "${pageContext.request.contextPath}/book/createBookView/${user.sjsuId}";
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
      <li class="active"><a href="#">Home</a></li> 
    </ul>
    <ul class="nav navbar-nav">
      <li class="active"><a onclick="newBook('${user.sjsuId}')">Add New Book</a></li> 
    </ul>
    <form class="navbar-form navbar-left" name="searchForm" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" id="search" name="search">
      </div>
      <button type="button" onclick="changeAction()" class="btn btn-default">Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${user.firstName }</a></li>
      
    </ul>
  </div>
</nav>


</body>
</html>