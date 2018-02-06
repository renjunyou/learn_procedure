<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">    
    <title>编辑学生</title>        
 </head>
 <body>
 	<form action="user/update" method="post">
	 	<table border="1" cellpadding="0" cellspacing="0" width="500px">
	 		<tr>
		 		<td>用户id：<input type="text" name="id" value="${user.id }" readonly="readonly" /></td>
	 		</tr>
	 		<tr>
		 		<td>用户名：<input type="text" name="username" value="${user.username }" /></td>
	 		</tr>
	 		<tr>
		 		<td>年&nbsp;&nbsp;&nbsp;龄：<input type="text" name="age" value="${user.age }" /></td>
	 		</tr>
	 		<tr>
		 		<td><input type="submit" value="保存" /></td>
	 		</tr>
	 	</table>
 	</form>
 </body>
</html>