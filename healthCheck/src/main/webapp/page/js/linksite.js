$(function() {
	//进入页面添加开始、结束时间默认值
	var startTime = getDateTime(new Date());
	var endTime = getDateTime2(new Date());

	$("#startTime").val(startTime);
	$("#endTime").val(endTime);
	
	//执行下查询
	$.ajax({
		type : "GET",
		url : "site/link",
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
					+ comment['lcode']
					+ '</td><td>'
					+ comment['linkName']
					+ '</td><td>'
					+ comment['linkUrl'] 
					+ '</td><td '+ ((comment['result'] == 0) ? ' class=\"succ\">' : ' class=\"err\">')
					+ ((comment['result'] == 0) ? '成功' : '失败')
					+ '</td><td>'
					+ new Date(comment['createTime']).getHours() + ':' + new Date(comment['createTime']).getMinutes()
					+ '</td></tr>';  
			});
			
			if(html.length == 0){
				layer.msg('没有查询到数据',2);
			}else{
				$('#mytableBody').html(html);
			}
			
			
		}

	});

});






