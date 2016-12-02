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
var i=0;
function changeAction() {
	document.searchForm.action = "${pageContext.request.contextPath}/book/patron/doSearch";
	document.forms["searchForm"].submit();	
}
function getBook(bookId) {
	location.pathname = "${pageContext.request.contextPath}/book/getBook/"+bookId;
}
function add(image,title,bookid,id)
{
	console.log("in");
	var a="data:image/jpeg;base64,"+image;
	var imgv="'"+a+"'";
		var menu=document.getElementById("books");
		var num=document.getElementById("cart");
		var li=document.createElement("li");
		var span=document.createElement("span");
		span.className="item";
		var span1=document.createElement("span");
		span1.className="item-left";
		var img=document.createElement("img");
		img.setAttribute('src',a);
		img.style.height="10px";
		img.style.width="10px";
		
		var span2=document.createElement("span");
		span2.className="item-info";
		var span3=document.createElement("span");
		span3.innerHTML=title;
		span2.appendChild(span3);
		span1.appendChild(img);
		span1.appendChild(span2);
		span.appendChild(span1);
		li.appendChild(span);
		menu.appendChild(li);
		num.text=" "+($("books").length+1)+" items";
		
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
    <form class="navbar-form navbar-left" name="searchForm" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" id="search" name="search">
      </div>
      <button type="button" onclick="changeAction()" class="btn btn-default">Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${user.firstName }</a></li>
      <li class="dropdown">
          <a href="#" id="cart" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> 0 - Items<span class="caret"></span></a>
          <ul class="dropdown-menu dropdown-cart" role="menu" id="books">
          </ul>
        </li>
    </ul>
  </div>
</nav>
<h1> Available Books</h1><br>

	<c:forEach items="${books}" var="current">	
			<div class="col-sm-4">
				<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
				<br>${current.author}
				<br><button type="button" onclick="add('${current.image}','${current.title}','${current.bookId}','${user.sjsuId}')" class="btn btn-default">Add to Cart</button>
				</div>
		</c:forEach>


</body>
</html>