<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>高校点点通产品运维平台</title>
    <link href="page/css/index.css" rel="stylesheet" type="text/css" />
    <script src="page/plugins/jquery1.11.1/jquery.min.js"></script>
    <script type="text/javascript">
	    $(function () {
	        $("#ulMenu> li").click(function () {
	            $("#ulMenu> li.current").attr("class", "");
	            $(this).attr("class", "current");
	        });

	        $("#website").click(function(){
	        	$("#ifr").attr("src","website.jsp");
	        });

	        $("#linksite").click(function(){
	        	$("#ifr").attr("src","linksite.jsp");
	        });

	        $("#test").click(function(){
	        	$("#ifr").attr("src","page/healthCheck.html");
	        });



	    });
	</script>
</head>

<body>
    <div id="container">
        <!--页面层容器-->
        <div id="Header">
            <!--页面头部-->
        </div>
        <!--页面主体-->
        <div id="PageBody">
            <!--侧边栏-->
            <div id="Sidebar">
                <div class="nav">
                    <ul id="ulMenu">
                        <li class="current"><a href="#" id="website"><span>网站监控</span></a></li>
                        <li><a href="#" id="linksite"><span>链接拨测</span></a></li>
                        <li><a href="#" id="test"><span>测试</span></a></li>
                    </ul>
                </div>
            </div>
            <!--主体内容-->
            <div id="MainBody">
                <iframe src="website.jsp" id="ifr" width="1200px" height="552px"  frameborder="0" border="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes"></iframe> 

            </div>
        </div>
        <!--页面底部-->
        <div id="Footer">
        	
            <span id="footspan">Copyright©2010-2017 www.kdzrit.com 版权所有 皖ICP备1501256号 &nbsp; 技术支持：科大智睿</span>

        </div>
    </div>
</body>

</html>
