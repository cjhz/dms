
/**
 * 省-市-县 三级机关联动函数
 * 
 * 在引用此JS的页面必须包含以下HTML元素:
 * 
 *  <select class="serch" id="province" onchange="cascade(1);">
 *		<option value="" selected="selected">省</option>
 *	</select>
 *	<select class="serch" id="city" onchange="cascade(2);">
 *		<option value="" selected="selected">市</option>
 *	</select>
 *	<select class="serch" id="county">
 *		<option value="" selected="selected">区/县</option>
 *	</select>
 * 
 * 
 */

var province = "33";//设置某省，也可以使用initProvince()获取

$(function(){
	if($("#province").length>0&&$("#city").length>0&&$("#county").length>0){//如果有在用则加载
		//initProvince();
		if($("#areadisplay").length>0){
//			alert($("#areadisplay").val());
			if($("#areacontrol").length>0){
				var areadisplay;
				if($("#areadisplay").val()==''){
					areadisplay = $("#areacontrol").val();
				}else{
					areadisplay = $("#areadisplay").val();
				}
				cascade(0, areadisplay, $("#areacontrol").val()); //得到所属机关选项中'省'下拉框的值； 0：指定某省；非0、1、2则显示全部
			}else{
				cascade(0, $("#areadisplay").val(), '33000021'); //得到所属机关选项中'省'下拉框的值； 0：指定某省；非0、1、2则显示全部
			}
		}else{
			cascade(0, '33000021', '33000021');
		}
		//alert('11111111111111111111');
		//selectProvince(); //选择默认省份
	}
});

function initProvince() {
	$.ajax({
		url: "get_system_param.do",
		data: {
			paramName: "MY_PROVINCE"
		},
		type: "get",
		dataType: "text",
		async: false,
		success: function(data) {
			province = data;
		}
	});
}

function cascade(type, areadisplay, areacontrol){
//	alert('areadisplay['+areadisplay+']');
//	alert('areacontrol['+areacontrol+']');
	var id = 0;
	var text;
	var dest;
	if(type == 1) {
		if($("#province").val() != "") {
			//排除选择空的情况
//			alert('in');
			id = $.parseJSON($("#province").val()).id;
//			alert('id[' + id + ']');
		}
		text = "市";
		dest = $("#city");
		dest.val("");
//		cascade(2, areadisplay);//省改变关联到县
	} else if(type == 2) {
		if($("#city").val() != "") {
			id = $.parseJSON($("#city").val()).id;
		}
		text = "区/县";
		dest = $("#county");
	} else {
		id = 1;
		text = "省";
		dest = $("#province");
	}
	if(id == 0) {
		dest.empty();
		dest.append("<option value='' selected='selected'>"+text+"</option>");
		return;
	}
	$.ajax({
		url:$("#areaDepartmentUrl").val(),
		data: {
			vclevelcode: id,
			areaparam:areadisplay
		},
		type: "get",
		dataType: "json",
		async: false,
		success: function(data) {
			dest.empty();
			dest.append("<option value='' selected='selected'>"+text+"</option>");
			$(data).each(function(i){
				if(type == 0) {
					//只取当前省(如果要取全部省份可去掉此if语句仅保留else字句内容)
//					alert('my [' + areadisplay.substring(0, 2) + '] now [' + data[i].vcDepartmentCode.substring(0, 2) + ']');
					if(data[i].vcDepartmentCode.substring(0, 2) == areadisplay.substring(0, 2)) {
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}' selected='selected'>"+data[i].vcAreaName+"</option>");
//						alert('areacontrol [' + areacontrol.substring(0, 2) + '] now [' + data[i].vcDepartmentCode.substring(0, 2) + ']');
						if(data[i].vcDepartmentCode.substring(0, 2) == areacontrol.substring(0, 2)){
							dest.attr('disabled','disabled');
						}
						cascade(1, areadisplay, areacontrol);
					}else{
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}'>"+data[i].vcAreaName+"</option>");
					}
				} else if(type == 1) {
					if(data[i].vcDepartmentCode.substring(0, 4) == areadisplay.substring(0, 4)) {
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}' selected='selected'>"+data[i].vcAreaName+"</option>");
						if(data[i].vcDepartmentCode.substring(0, 4) == areacontrol.substring(0, 4)){
							dest.attr('disabled','disabled');
						}
						cascade(2, areadisplay, areacontrol);
					}else{
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}'>"+data[i].vcAreaName+"</option>");
					}
				} else if(type == 2) {
					//alert('my [' + areadisplay.substring(0, 6) + '] now [' + data[i].vcDepartmentCode.substring(0, 6) + ']');
					if(data[i].vcDepartmentCode.substring(0, 6) == areadisplay.substring(0, 6)) {
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}' selected='selected'>"+data[i].vcAreaName+"</option>");
						if(data[i].vcDepartmentCode.substring(0, 6) == areacontrol.substring(0, 6)){
							dest.attr('disabled','disabled');
						}
					}else{
						dest.append("<option value='{\"id\":\""+data[i].pid+"\",\"code\":\""+data[i].vcDepartmentCode+"\"}'>"+data[i].vcAreaName+"</option>");
					}
				}
			});
		}
	});
}

var timeCount = 0;

function sleep(time, next, inner) {
	if(inner) {
		setTimeout(next, time);
	} else {
		timeCount += time;  
		setTimeout(next, timeCount);
	}
}     

function selectProvince() {
	//选择默认省份(由系统参数设置)	
	sleep(100, function(){
		$("#province option").each(function(i){
			var optionValue = $(this).val();
			if(optionValue!="" && $.parseJSON(optionValue).code.substring(0, 2)==province) {	
				$(this).attr("selected", true);
			}
		});
		//alert('kao');
		cascade(1); //选择省后关联到市
	});
}

function selectDepartment(vcdepartmentcode) {
	if(vcdepartmentcode.length < 2) {
		$("#province option").each(function(i){
			if($(this).val() == "") {	
				$(this).attr("selected", true);
			}
		});
		cascade(1);
		return;
	}
	$("#province option").each(function(i){
		var optionValue = $(this).val();
		if(optionValue!="" && $.parseJSON(optionValue).code.substring(0, 2)==vcdepartmentcode.substring(0, 2)) {	
			$(this).attr("selected", true);
		}
	});
	cascade(1);
	if(vcdepartmentcode.length < 4) {
		return;
	}
	sleep(200, function(){
		$("#city option").each(function(i){
			var optionValue = $(this).val();
			if(optionValue!="" && $.parseJSON(optionValue).code.substring(0, 4)==vcdepartmentcode.substring(0, 4)) {	
				$(this).attr("selected", true);
			}
		});
		cascade(2);
		if(vcdepartmentcode.length < 6) {
			return;
		}
		sleep(300, function(){
			$("#county option").each(function(i){
				var optionValue = $(this).val();
				if(optionValue!="" && $.parseJSON(optionValue).code.substring(0, 6)==vcdepartmentcode.substring(0, 6)) {	
					$(this).attr("selected", true);
				}
			});
		}, true);
	}, true);	
}

function selectAll(vcdepartmentcode) {
	sleep(100, function(){
		selectDepartment(vcdepartmentcode);
	});
	timeCount += 600; //选择机关后必须延迟600ms
}

function getVcdepartmentcode() {
	if($("#county").val() != "") {
		return $.parseJSON($("#county").val()).code;
	}
	if($("#city").val() != "") {
		return $.parseJSON($("#city").val()).code;
	}
	if($("#province").val() != "") {
		return $.parseJSON($("#province").val()).code;
	}else{
		return "00000000";
	}
	return "";
}

function getVcdepartmentId() {
	if($("#county").val() != "") {
		return $.parseJSON($("#county").val()).id;
	}
	if($("#city").val() != "") {
		return $.parseJSON($("#city").val()).id;
	}
	if($("#province").val() != "") {
		return $.parseJSON($("#province").val()).id;
	}else{
		return "1";
	}
	return "";
}
