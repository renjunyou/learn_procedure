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
	<script type="text/javascript" src="${basePath}static/plugins/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}static/plugins/paginator/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${basePath}static/js/user/user.js"></script>        
 </head>
 <body>
 	<table border="1" cellpadding="0" cellspacing="0" width="500px">
 		<tr>
	 		<th>用户id</th>
	 		<th>姓名</th>
	 		<th>年龄</th>
	 		<th>操作</th>
 		</tr>
 		<c:forEach items="${userList }" var="userList">
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
 		
 	</table>
 	
 	<div>总记录数：${count }</div>
 	
 	<hr>
 	
 	<div id="select" style="height:520px;">
	    <table class="table table-striped table-bordered table-hover" id="userTable">
	    	<!-- table table-bordered 带边框的样式 -->
	    </table>
    </div>
    <div style="text-align:center;">
        <ul id="useroption"></ul>
   	</div>
 	
 	
 	
 	<!-- 下面是控制分页控件，必须要是ul元素才行 -->
	<!-- <ul id='bp-element'></ul> -->
	
	<script type="text/javascript">
		queryUser();
		
	    function queryUser() {
	     $.ajax({
	        async: true,
	        type: "post",
	        url: "user/listUserByPage",//向后台发送请求
	        dataType: "json",
	        data: {page:'1'},
	        cache: false,
	        success: function(data) {
	            var result = JSON.parse(data);   
	            //data.json_data为后台返回的JSON字符串，这里需要将其转换为JSON对象
	             
                tbody="<tr style='background:#fff;'><th>序号</th><th>姓名</th>" +
                        "<th>年龄</th><th>操作</th></tr>";
                for (var i = 0; i <result.list.length; i++) {//拼接对应<th>需要的值
                     var trs = "";
                     trs+='<tr ><td >' + result.list[i].getId()   
                                       + '</td><td >' + result.list[i].getUsername()
                                       + '</td><td >' + result.list[i].getAge()
                                       + '</td><td><a href="user/listById?id='+ result.list[i].getId() 
                                       + '">编辑</a>&nbsp;&nbsp;<a href="user/deleteById?id=' 
                		 			   + result.list[i].getUsername() 
                		 			   + '">删除</a>&nbsp;&nbsp;<a href="user/add">添加</a></td></tr>';
                     tbody+=trs; 
                };
                $("#userTable").html(tbody);  
	               
	            var currentPage = result.CurrentPage; //当前页数
	            var pageCount = result.pageCount; //总页数
	            var options = {
	            bootstrapMajorVersion: 3, //版本
	            currentPage: currentPage, //当前页数
	            totalPages: pageCount, //总页数
	            numberOfPages: 5,
	            shouldShowPage:true,//是否显示该按钮
	            itemTexts: function (type, page, current) {
	                switch (type) {
	                    case "first":
	                        return "首页";
	                    case "prev":
	                        return "上一页";
	                    case "next":
	                        return "下一页";
	                    case "last":
	                        return "末页";
	                    case "page":
	                        return page;
	                }
	
	            },//点击事件，用于通过Ajax来刷新整个list列表
	            onPageClicked: function (event, originalEvent, type, page) {
	                $.ajax({
	                    async: true,
	                    url: "user/listUserByPage",
	                    type: "post",
	                    dataType : "json",
	                    data: {page:page},
	                    cache: false,
	                    success: function (data) {
	                        var result = JSON.parse(data);
	                       
	                        tbody="<tr style='background:#fff;'><th>序号</th><th>姓名</th>" +
	                        "<th>年龄</th><th>操作</th></tr>";
	                        for (var i = 0; i <result.list.length; i++) {
                                 var trs = "";
                                 trs+='<tr ><td >' + result.list[i].getId()   
                                  				   + '</td><td >' + result.list[i].getUsername()
                                  				   + '</td><td >' + result.list[i].getAge()
                                  				   + '</td><td><a href="user/listById?id='+ result.list[i].getId() 
                                  				   + '">编辑</a>&nbsp;&nbsp;<a href="user/deleteById?id=' 
           		 			   					   + result.list[i].getUsername() 
           		 			   					   + '">删除</a>&nbsp;&nbsp;<a href="user/add">添加</a></td></tr>';
                                 tbody+=trs; 
	                           
	                       };
	                       $("#userTable").html(tbody);  
	
	                    }/*success*/
	                });
	
	            }
	
	        };
	        $('#useroption').bootstrapPaginator(options);     
	            }/*success*/
	        
	    });
	    }
	</script>
 	
 </body>
</html>