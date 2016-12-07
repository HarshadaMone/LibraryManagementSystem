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

<title>Create User</title>
<script type="text/javascript">
	function changeAction() {
		var myVal = document.getElementById("email").value;
		var tmp = myVal.substr ( myVal.indexOf ( '@' ) + 1 ); // tmp now contains whatever is after ?
		if(tmp == 'sjsu.edu' )
			{
			document.createForm.action = "${pageContext.request.contextPath}/librarian/signUp/"+document.getElementById("sjsuId").value;
			document.forms["createForm"].submit();	}
		else
			{
			document.createForm.action = "${pageContext.request.contextPath}/patron/signUp/"+document.getElementById("sjsuId").value;
			document.forms["createForm"].submit();	
			}
	}
</script>
<style type="text/css">
.phone-input{
	margin-bottom:8px;
}
</style>
</head>
<body>
<div class="container">
	<h1 align="center">Create User</h1><br>
	<form class="form-horizontal" name="createForm" method="post" style="max-width:450px;">
		<div class="form-group">
			<label class="col-sm-4 control-label">SJSU ID</label>
			<div class="col-sm-8">
				<input type="text" name="sjsuId" id="sjsuId" class="form-control">
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-sm-4 control-label">First Name</label>
			<div class="col-sm-8">
				<input type="text" name="firstName" id="firstName" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Last Name</label>
			<div class="col-sm-8">
				<input type="text" name="lastName" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Email</label>
			<div class="col-sm-8">
				<input type="email" id="email" name="email" class="form-control">
			</div>
		</div>	
				<div class="form-group">
			<label class="col-sm-4 control-label">Password</label>
			<div class="col-sm-8">
				<input type="password" name="password" class="form-control">
			</div>
		</div>			
	</form>
	<label class="col-sm-4 control-label"></label>
		<button type="button" value="Create" onclick="changeAction()" class="col-sm-2 btn btn-success ">Create</button>
		
</div>
<hr>
</body>
</html>