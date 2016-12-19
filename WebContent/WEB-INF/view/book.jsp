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
		
		if (action_name == "update") {
			$.ajax({
				url :'${pageContext.request.contextPath}/book/updateBook/${book.bookId}'+ "?sjsuId="
						+ document.updateForm.sjsuId.value + "&author="
						+ document.updateForm.author.value + "&title="
						+ document.updateForm.title.value + "&callNumber="
						+ document.updateForm.callNumber.value + "&publisher="
						+ document.updateForm.publisher.value + "&yearOfPublication="
						+ document.updateForm.yearOfPublication.value + "&location="
						+ document.updateForm.location.value + "&copies="
						+ document.updateForm.copies.value + "&status="
						+ document.updateForm.status.value + "&keyword="
						+ document.updateForm.keyword.value,
				method : 'POST',
				success : function(data){
					location.pathname = "${pageContext.request.contextPath}/book/getBook/"+ ${book.bookId};
				}
			});
		} else if (action_name == "delete") {
			$.ajax({
				url : '${pageContext.request.contextPath}/book/deleteBook/${book.bookId}',
				method : 'DELETE',
				dataType:'html',
				success : function(data) {
					if(data == "book"){
					location.pathname = "${pageContext.request.contextPath}/librarian/login/${book.user.email}/";}
					else{
						location.pathname = "${pageContext.request.contextPath}/book/errorDeleteBook/";
					}
				}
			});
			
		}else if (action_name == "") {
			location.pathname = "${pageContext.request.contextPath}/librarian/login/${book.user.email}/";			
		}		
	}
</script>
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
      <li><a onclick="changeMethod('')"><span class="glyphicon glyphicon-user"></span> ${book.user.firstName}</a></li>
      
    </ul>
  </div>
</nav>
<div class="container">
	<h1 align="center">Book</h1><br>
	
	<form class="form-horizontal" name="updateForm" method="post" >
		<div class="form-group">
			<label class="col-sm-4 control-label"></label>
			<div class="col-sm-8">
				<img src="data:image/jpeg;base64,${image}" alt="" width="200" height="200" />
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-4 control-label">SJSU Id</label>
			<div class="col-sm-8">
				<input type="text" name="sjsuId" id="sjsuId" value="${book.user.sjsuId}" readonly class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label">Book Id</label>
			<div class="col-sm-8">
				<input type="text" name="id" id="id" value="${book.bookId }" readonly class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Author</label>
			<div class="col-sm-8">
				<input type="text" name="author" id="author" value="${book.author}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Title</label>
			<div class="col-sm-8">
				<input type="text" name="title" value="${book.title}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Call Number</label>
			<div class="col-sm-8">
				<input type="text" name="callNumber" value="${book.callNumber}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Publisher</label>
			<div class="col-sm-8">
				<input type="text" name="publisher" value="${book.publisher}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Year Of Publication</label>
			<div class="col-sm-8">
				<input type="text" name="yearOfPublication" value="${book.yearOfPublication}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">Location</label>
			<div class="col-sm-8">
				<input type="text" name="location" value="${book.location}" class="form-control">
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-4 control-label">Copies</label>
			<div class="col-sm-8">
				<input type="text" name="copies" value="${book.copies}" class="form-control">
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-4 control-label">Status</label>
			<div class="col-sm-8">
				<input type="text" name="status" value="${book.status}" class="form-control">
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-4 control-label">Keyword</label>
			<div class="col-sm-8">
				<input type="text" name="keyword" value="${book.keyword}" class="form-control">
			</div>
		</div>	


	<label class="col-sm-2 control-label"></label>
		<button type="button" value="Update" onclick="changeMethod('update')" class="col-sm-2 btn btn-success ">Update</button>		
		<label class="col-sm-2 control-label"></label>
		<button type="button" value="Delete" onclick="changeMethod('delete')" class="col-sm-2 btn btn-success ">Delete</button>
</form>
</div>
</body>
</html>