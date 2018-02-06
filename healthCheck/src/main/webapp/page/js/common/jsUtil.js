//yyyy-MM-dd HH:mm:SS
function getDateTime2(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hh = date.getHours();
    if(hh.length == 1){
    	hh = '0' + hh;
    }
    var mm = date.getMinutes();
    if(mm.length == 1){
    	mm = '0' + mm;
    }
    var ss = date.getSeconds();
    return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
}

//yyyy-MM-dd HH:mm:SS
function getDateTime(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return year + "-" + month + "-" + day + " 00:00:00";
}

;!function(){  
    layer.config({//加载扩展模块  
        extend: 'extend/layer.ext.js'  
    });  
    layer.ready(function(){   
    });  
}();  
          
function ityzl_SHOW_LOAD_LAYER(){  
    return layer.msg('努力中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;  
}  
function ityzl_CLOSE_LOAD_LAYER(index){  
    layer.close(index);  
}  
function ityzl_SHOW_TIP_LAYER(){  
    layer.msg('恭喜您，加载完成！',{time: 1000,offset: '10px'});  
}


