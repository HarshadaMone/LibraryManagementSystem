<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function changeMethod(action_name) {
		if (action_name == "delete") {
			if('${user.role}' == "librarian"){
			$.ajax({
				url : '${pageContext.request.contextPath}/librarian/deleteUser/${user.sjsuId}',
				method : 'DELETE',
				dataType:'html',
				success : function(data) {
					location.pathname = "${pageContext.request.contextPath}/"+data;
					
				}
			});
			
		}}
	}
</script>
</head>
<body style="
    background-color: rgb(12, 12, 12);
    color: white;">
<div class="container">
	<h1 align="center">User</h1><br>
	
	<form class="form-horizontal" name="updateForm" method="post" >
		<div class="form-group">
			<label class="col-sm-4 control-label">SJSU Id</label>
			<div class="col-sm-8">
				<input type="text" name="sjsuId" id="sjsuId" value="${user.sjsuId }" readonly class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">First Name</label>
			<div class="col-sm-8">
				<input type="text" name="firstName" id="firstName" value="${user.firstName}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Last Name</label>
			<div class="col-sm-8">
				<input type="text" name="lastName" value="${user.lastName}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Email</label>
			<div class="col-sm-8">
				<input type="text" name="email" value="${user.email}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Role</label>
			<div class="col-sm-8">
				<input type="text" name="role" value="${user.role}" readonly class="form-control">
			</div>
		</div>
	<label class="col-sm-2 control-label"></label>
		<button type="button" value="Update" onclick="changeMethod('update')" class="col-sm-2 btn btn-success ">Update</button>	
	<c:if test="${user.role == 'librarian'}">
	<label class="col-sm-2 control-label"></label>
	<a href="${pageContext.request.contextPath}/book">
		<button type="button" value="Delete" onclick="changeMethod('add')" class="col-sm-2 btn btn-success ">Add New Book</button>	</a>		
</c:if>
		<label class="col-sm-2 control-label"></label>
		<button type="button" value="Delete" onclick="changeMethod('delete')" class="col-sm-2 btn btn-success ">Delete</button>
</form>
</div>
</body>
</html>