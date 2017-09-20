<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="POST" action="/sendmail" enctype="multipart/form-data">
File : <input type="file" name="file" /><br/><br/>
Email: <input type="text" name="email"/>
    
    <input type="submit" value="Submit" /><br/><br/>
<a href="/pdf">Download Pdf File</a><br/><br/> 
<a href="/modelview">Download Excel File</a><br/> <br/>  
<a href="/download">Download attached file</a><br/><br/> 
</form> 
${msg}
</body>
</html>