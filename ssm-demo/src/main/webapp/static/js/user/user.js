//jQuery Ajax获取用户数据
function loadData(p){
	$.getJSON(
		"user/getUserByPage",
		{
			page:p,   //加载第几页的数据
			count:8   //每页显示的数据量  这里写死了
		},
		function(data) {
			if(data.length==0){
				alert("没有更多数据了");
				$("a[pages]").remove();
				return;
			}
			var str = '';
			for (i in data) {
				str += "<div class=\"item\"><div class=\"row\"><div class=\"col-md-9 col-sm-9 col-xs-9\"><a  class=\"subtitle\" href=\"GsServlet.action?method=NewsContent\" target=\"_blank\"title=\""+data[i]['newsTitle']+"\">"+data[i]['newsTitle']+"</a><div class=\"desc\">"+data[i]['newsAbstract']+"</div><div class=\"all\"><img src=\"images/time.png\" class=\"time\" alt=\"发布时间\" title=\"发布时间\"/><span class=\"data\">"+data[i]['publishTime']+"</span></div></div><div class=\"col-md-3 col-sm-3 col-xs-3\"><img alt=\"\" src=\""+data[i]['thumbImageUrl']+"\" class=\"img-wrap\"></div></div></div> ";
			}
			$("#item").append(str);
			bp(data['page'],30);
		});
	
			
	
}



//使用Bootstrap Paginator分页插件进行分页函数
function bp(curr, total) {
	var options = {
		bootstrapMajorVersion : 3,
		currentPage : curr,
		numberOfPages : 5,
		totalPages : total,
		itemTexts : function(type, page, current) {
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
		},
		onPageClicked : function(event, originalEvent, type, page) {
			loadData(page);

		}
	}

	$("#bp-3-page").bootstrapPaginator(options);
}