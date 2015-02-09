<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Students Data</h1>
<form:form action="/usercontroller/signup" method="POST" commandName="userreturn">
	<table>
		<tr>
			<td>username</td>
			<td><form:input path="username" /></td>
		</tr>
		<tr>
			<td>password</td>
			<td><form:input path="password" /></td>
		</tr>
		<tr>
			<td>firstname</td>
			<td><form:input path="firstname" /></td>
		</tr>
		<tr>
			<td>lastname</td>
			<td><form:input path="yearLevel" /></td>
		</tr>
		<tr>
			<td>email</td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
			</td>
		</tr>
	</table>
</form:form>
<br>
</body>
</html>