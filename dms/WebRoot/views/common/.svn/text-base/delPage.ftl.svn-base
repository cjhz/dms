<div style="position:absolute; display: none; width:138px; " id="deldiv">
	<div style="width:100%; height:6px; background-image:url(images/line_bg.gif); text-align:right; "><img src="${contextPath}/theme/${theme}/images/arrow2.gif" style="margin-right: 25px"></div>
	<table width="100%" style="border-left:1px #c79650 solid;border-right:1px #c79650 solid;border-bottom:1px #c79650 solid; background:#fbfabb;  font-size:12px ; ">
	<tr>
	    <td  align="left" valign="middle" height="25" style="padding:0 5px 0 5px;" id="deldivtxt">确定要<font color='red'>删除</font>吗？</td>
	</tr>
	<tr>
		<td valign="middle" >
	    	<div style="float: right;"><a href="javascript:delPro();">确定</a></div>
	    </td>
	</tr>
	</table>
</div>
	
<script>
//点击页面，将删除提示div隐藏
$(document).ready(function(){
 	$(document).bind('click',function(){del_hide();});
});

//删除
var delPro=new function(){};
var isload=false;

function divDel(delPro){//程序主调函数
	var mos = mouseMove();this.delPro=delPro;
	del_init();//重刷
	del_show(mos);
	isload=true;
}
function del_init(){$('#deldiv').css({'opacity':'1.0','display':'none'});}//初始化
function del_show(mos){//显示 div
	$('#deldiv').css('left',mos.x-99+'px');
	$('#deldiv').css('top',mos.y-5+'px');
	$('#deldiv').animate({opacity: "show", top: "+="+7}, 300);
}
function del_hide(){//隐藏 div
	if(isload){isload=false;return;}
	else if($('#deldiv').css('display')=='none'){return;}
	$('#deldiv').animate({opacity: "0.0", top: "-="+7}, 300,function(){del_init();});
}
function mouseMove(){//获取触发元素
	var ev = window.event.srcElement;//获取事件源。区分IE和DOM2浏览器
    var mousePos = mouseCoords(ev);         
    return mousePos;
}   
function mouseCoords(obj){//获取元素的坐标
	var selectedPosX = 0;
    var selectedPosY = 0;
  
    if (!obj) return;
    var theElemHeight = obj.offsetHeight;
  
    while(obj != null){   
	    selectedPosX += obj.offsetLeft;   
	    selectedPosY += obj.offsetTop;   
	    obj = obj.offsetParent;   
    }   
    var IEx = selectedPosX;
    var IEy = selectedPosY+theElemHeight;
    return{x:IEx,y:IEy}; 
}
</script>