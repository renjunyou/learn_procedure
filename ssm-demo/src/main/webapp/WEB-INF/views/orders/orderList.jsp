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
    <meta charset="utf-8">    
    <title>订单列表信息</title>
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/pager.css">
	<script type="text/javascript" src="${basePath}static/plugins/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/paginator/bootstrap-paginator.js"></script>
 
 </head>
 <body>
 	<table border="1" cellpadding="0" cellspacing="0" width="800px">
 		<tr>
	 		<th>订单id</th>
	 		<th>订单名称</th>
	 		<th>下单时间</th>
	 		<th>下单人</th>
 		</tr>
 		<c:forEach items="${orderList }" var="orderList">
 			<tr align="center">
		 		<td>${orderList.order_id }</td>
		 		<td>${orderList.order_name }</td>
		 		<td>${orderList.order_time }</td>
		 		<td>${orderList.order_person }</td>
 			</tr>
 		</c:forEach>
 		
 	</table><br>
 	
 	<div style="float:left; width: 800px; text-align: center;">
 		<%@include file="../page/pager.jsp" %>
 	</div>
 </body>
</html>