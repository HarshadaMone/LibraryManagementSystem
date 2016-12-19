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

<title>ISBN Based Book import</title>
<script type="text/javascript">
	function changeAction() {
			
			document.createForm.action = "${pageContext.request.contextPath}/book/isbnbook/${sjsuId}"
			console.log(document.getElementById("isbn").value);
			document.forms["createForm"].submit();	
	}
	function changeMethod(action_name) {		
		if (action_name == "") {
			location.pathname = "${pageContext.request.contextPath}/librarian/login/${user.email}/";			
		}		
	}
</script>
<style type="text/css">
.phone-input{
	margin-bottom:8px;
}
</style>
</head>
<body style="
    background-color: rgb(12, 12, 12);
    color: white;">
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sjsu Library</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a onclick="changeMethod('')">Home</a></li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${pageContext.request.contextPath}/">Log Out</a></li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a onclick="changeMethod('')"><span class="glyphicon glyphicon-user"></span> ${user.firstName}</a></li>
      
    </ul>
  </div>
</nav>
<div class="container">
	<h1 align="center">ISBN </h1><br>
	<form class="form-horizontal" name="createForm" method="post" style="max-width:450px;">
		<div class="form-group">
			<label class="col-sm-4 control-label">SJSU ID</label>
			<div class="col-sm-8">
				<input type="text" name="sjsuId" id="sjsuId" value="${sjsuId}" readonly class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">ISBN :</label>
			<div class="col-sm-8">
				<input type="text" id="isbn" name="isbn" class="form-control">
			</div>
		</div>	
	</form>
	<label class="col-sm-4 control-label"></label>
		<button type="button" value="Create" onclick="changeAction()" class="col-sm-2 btn btn-success ">Get Book</button>
		
</div>
<hr>
</body>
</html>