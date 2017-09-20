<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form method="POST" action="/saveAddress" modelattribute="msg" >
House No  : <input type="text" name="hno" /><br/><br/>
Street    : <input type="text" name="street"/><br/><br/>
City      : <input type="text" name="city"/> <br/><br/>  
<input type="submit" value="Submit" /><br/><br/>
</form:form> 
${msg}
</body>
</html>