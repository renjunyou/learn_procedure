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
    <title>学生列表信息</title>
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/pager.css">
	<script type="text/javascript" src="${basePath}static/plugins/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/paginator/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${basePath}static/js/user/user.js"></script>        
 </head>
 <body>
 	<table border="1" cellpadding="0" cellspacing="0" width="800px">
 		<tr>
	 		<th>用户id</th>
	 		<th>姓名</th>
	 		<th>年龄</th>
	 		<th>操作</th>
 		</tr>
 		<c:forEach items="${pb.dataList }" var="userList">
 			<tr align="center">
		 		<td>${userList.id }</td>
		 		<td>${userList.username }</td>
		 		<td>${userList.age }</td>
		 		<td>
		 			<a href="user/listById?id=${userList.id }">编辑</a>&nbsp;&nbsp;
		 			<a href="user/deleteById?id=${userList.id }">删除</a>&nbsp;&nbsp;
		 			<a href="user/add">添加</a>
		 		</td>
 			</tr>
 		</c:forEach>
 		
 	</table><br>
 	
 	<div style="float:left; width: 800px; text-align: center;">
 		<%@include file="../page/pager.jsp" %>
 	</div>
 	
 </body>
</html>