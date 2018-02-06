<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>网站监控</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="page/plugins/jquery1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="page/js/common/jsUtil.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!--自定义样式 -->
<link rel="stylesheet" type="text/css" href="page/css/website.css">
<script type="text/javascript" src="page/plugins/layer1.8.5/layer.min.js"></script>
<script type="text/javascript" src="page/plugins/layer1.8.5/extend/layer.ext.js"></script>
<script type="text/javascript" src="page/js/website.js"></script>
<script type="text/javascript">
	$(function() {

		//重置时间输入框
		$("#btn2").click(function() {
			$("#startTime").val("");
			$("#endTime").val("");
		});
		
		
		//手动拨测
		$("#btn3").bind("click", function () {  
	        var i ;  
	        $.ajax({  
	            type: "GET",  
	            dataType: "text", 
	            data : {
					type : "1" //1-网站拨测   2-链接拨测
				},
	            url: "site/handlerCheck",  
	            beforeSend: function () {  
	                i = ityzl_SHOW_LOAD_LAYER();  
	            },  
	            success: function (msg) {  
	                ityzl_CLOSE_LOAD_LAYER(i);  
	                ityzl_SHOW_TIP_LAYER();  
	            },  
	            error: function (e, jqxhr, settings, exception) {  
	                ityzl_CLOSE_LOAD_LAYER(i);  
	            }  
	        });
	        
	        //刷新下iframe页面
	        $("#website").click(function(){
	        	$("#ifr").attr("src","linksite.jsp");
	        });
	        
	    }); 
		
		
		//手动补发邮件     由于邮件模板是固定的，发送前先查询确认下所在时间段 只包含一次数据
		$("#btn4").bind("click", function () {    
	        $.ajax({  
	            type: "GET",
	            data : {
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},
	            dataType: "text", 
	            url: "site/handWebEmail",  
	            success: function (msg) {  
	                if(msg == '0'){
	                	layer.msg('邮件发送成功！',2);
	                }else{
	                	layer.msg('邮件发送失败！',2);
	                }  
	            }
	        });
	        
	    });
		

		//查询
		$("#btn1").click(function() {
			
			if($("#startTime").val() == ""){
				layer.msg('开始时间不能为空!',2,-1);
				return;
			}
			if($("#endTime").val() == ""){
				layer.msg('结束时间不能为空!',2,-1);
				return;
			}

			$.ajax({
				type : "GET",
				url : "site/zrweb",
				data : {
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},
				dataType : "json",
				success : function(data) {
					$('#mytableBody').empty(); //清空mytableBody里面的所有内容
					var html = '';
					$.each(data, function(commentIndex, comment) {
						html += '<tr><td>'
								+ comment['linkName']
								+ '</td><td>'
								+ comment['linkUrl'] 
								+ '</td><td '+ ((comment['result'] == '成功') ? ' class=\"succ\">' : ' class=\"err\">')
								+ comment['result']
								+ '</td><td>'
								+ new Date(comment['createTime']).getHours() + ':' + new Date(comment['createTime']).getMinutes()
								+ '</td></tr>';  
					});
					//alert(html.length);
					if(html.length == 0){
						layer.msg('没有查询到数据',2);
					}else{
						$('#mytableBody').html(html);
					}
					
				}

			});

		});

	});
</script>
</head>

<body>
	<div class="container">
		<div id="condition">
			<br> &nbsp;&nbsp;&nbsp;&nbsp; 开始时间： <input type="text"
				id="startTime" class="sang_Calender" /> &nbsp;&nbsp; 结束时间： <input
				type="text" id="endTime" class="sang_Calender" /> &nbsp;&nbsp;
			<button id="btn1">查询</button>
			<button id="btn2">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="btn3">手动拨测</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="btn4">手动补发邮件</button>

		</div>
		<!-- 日期时间插件 -->
		<script type="text/javascript" src="page/js/datetime.js"></script>
		<div id="content">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>网站名称</th>
						<th>链接地址</th>
						<th>响应结果</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody id="mytableBody">


				</tbody>
			</table>
		</div>
	</div>
</body>

</html>
