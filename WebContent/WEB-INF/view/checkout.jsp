<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sjsu Library</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li ><a href="#">Return Book</a></li> 
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
          		<li class="divider" id="line"></li>
              	<li><a class="text-center" id="checkout" onclick="checkout()" href="">CheckOut</a></li>
          </ul>
        </li>
    </ul>
  </div>
</nav>
<h1> Books in cart</h1><br>

	<c:forEach items="${books}" var="current">	
			<div class="col-sm-4">
				<img src="data:image/jpeg;base64,${current.image}" alt="" width="200" height="200" />
				<br>${current.author}
				<br><button type="button" id="add${current.bookId}" onclick="event.preventDefault();add('${current.image}','${current.title}','${current.bookId}','${user.sjsuId}')" class="btn btn-default">Add to Cart</button>
				</div>
		</c:forEach>



</body>
</html>