<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create item</title>
</head>
<body>
	<form name="new_item" action="/service/item" method="post">
		Item Code:<br/>
		<input type="text" id="id_name=" name="code" value="" width=60/><br/>	
		Item name:<br/>
		<input type="text" id="id_name=" name="name" value="" width=60/><br/>
		Item description:<br/>
		<input type="text" id="id_description" name="description" value="" width=60/><br/>
		Item price:<br/>
		<input type="text" id="id_price" name="price" value="" /><br/>
		<br/>
		<input type="submit" name="submit" value="Create" />
	</form>
</body>
</html>