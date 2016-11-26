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

<title>Welcome</title>
<style type="text/css">
.phone-input{
	margin-bottom:8px;
}
</style>
</head>
<body style="
    background-color: rgb(12, 12, 12);
    color: white;">
    <div class="jumbotron text-center">
    <h1 align="center" style="color:black;">Welcome to SJSU Library System</h1><br><br><br>
    </div>
<div class="container">
	<div class="row">
	<div class="col-md-4 text-center"> 
		<a href="${pageContext.request.contextPath}/signUpAsLibrarian"><button type="button" class="btn btn-success">SignUp As Librarian</button></a>
	</div></div><br>
	<div class="row">
	<div class="col-md-4 text-center">
		<a href="${pageContext.request.contextPath}/signUpAsPatron"><button type="button" class="btn btn-success">SignUp as Patron</button></a><br>
	</div></div><br>
	<div class="row">	
	<div class="col-md-4 text-center">
		<a href="${pageContext.request.contextPath}/loginLibrarian"><button type="button"  class="btn btn-success">Login As Librarian</button></a><br>
	</div></div><br>
	<div class="row">
	<div class="col-md-4 text-center">
		<a href="${pageContext.request.contextPath}/loginPatron"><button type="button" class="btn btn-success">Login As Patron</button></a><br>
	</div></div>
</div>
<hr>
</body>
</html>