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

<title>Add New Book</title>
<script type="text/javascript">
	function changeAction() {
			document.createForm.action = "${pageContext.request.contextPath}/book/createBook/";
			document.forms["createForm"].submit();	
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
<div class="container">
	<h1 align="center">Add Book</h1><br>
	<form class="form-horizontal" name="createForm" enctype="multipart/form-data" method="post" style="max-width:450px;">
		<div class="form-group">
			<label class="col-sm-4 control-label">Author</label>
			<div class="col-sm-8">
				<input type="text" name="author" id="author" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Title</label>
			<div class="col-sm-8">
				<input type="text" name="title" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Call Number</label>
			<div class="col-sm-8">
				<input type="text" name="callNumber" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Publisher</label>
			<div class="col-sm-8">
				<input type="text" name="publisher" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Year Of Publication</label>
			<div class="col-sm-8">
				<input type="text" name="yearOfPublication" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Location</label>
			<div class="col-sm-8">
				<input type="text" name="location" class="form-control">
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-4 control-label">Copies</label>
			<div class="col-sm-8">
				<input type="text" name="copies" class="form-control">
			</div>
		</div>				
		<div class="form-group">
			<label class="col-sm-4 control-label">Status</label>
			<div class="col-sm-8">
				<input type="text" name="status" class="form-control">
			</div>
		</div>				
		<div class="form-group">
			<label class="col-sm-4 control-label">Keyword</label>
			<div class="col-sm-8">
				<input type="text" name="keyword" class="form-control">
			</div>
		</div>				
		<div class="form-group">
			<label class="col-sm-4 control-label">Image</label>
			<div class="col-sm-8">
				<input type="file" name="image"  accept="image/*" class="form-control">
			</div>
		</div>									
	</form>
	<label class="col-sm-4 control-label"></label>
		<button type="button" value="Create" onclick="changeAction()" class="col-sm-2 btn btn-success ">Add</button>
		
</div>
<hr>
</body>
</html>