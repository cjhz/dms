/**全局变量*/

$(document).ready(function(){
	$(".main_table tr:odd").addClass("main_table_bg"); 
	$(".main_table tr").hover(function () { 
		$(this).addClass("main_table_over");   //鼠标经过时增加样式P02
  	}, function () { 
        $(this).removeClass("main_table_over"); //鼠标离开时移除样式P02
   	});
});

function isShow(jQueryObj) {
	return jQueryObj.css('display') != 'none' && jQueryObj.css('display') != 'NONE'; 
}

$(document).ready(function(){
    $(".togglePage").click(function(){
        var current = $(this).parent().parent().next();
        $('.receipt_split_table').each(function() { //合上其他已经展开的菜单
            if ($(this) != current && isShow($(this))) {
            	var container = $(this); 
                $(this).find('.receipt_split').slideUp("slow", function(){
                	container.hide();	
                }); //合上
            }
        })
        if (isShow(current)) { //如果对象可见
            current.find('.receipt_split').slideUp("slow", function() {
            	current.hide();
            }); //合上
        } else {
        	current.show();
            current.find('.receipt_split').slideDown("slow");
        }
    });
});

$(document).ready(function() {
	$(".iner_title_sub ul li").hover(function () { //鼠标经过时增加样式P02
		$('.iner_title_sub_01').hide();
		$('#menu_2_' + $(this).attr('code')).show();
  	}, function () { //鼠标离开时移除样式P02
  		//$('#menu_2_' + $(this).attr('code')).hide();
  		//$('.cur_menu').show();
   	});
	
	$('#innerMenuContainer').hover(function() {
	}, function() {
		$('.iner_title_sub_01').hide();
  		$('.cur_menu').show();
	})
});


function timeoutRedirect(contextPath) {
	alert('登录超时');
	window.top.location = contextPath + '/common/loginPage.action';
}

function requestLoading(loading) {
	$("#"+loading).html("<font color='red'>正在操作，请稍后...</font>");
}

function requestloadComplete(loading) {
	$("#"+loading).html("");
}


/**
 * 选择所有选项 
 * @param check
 * @param checkboxName
 * @return
 */
function selectAllCheckbox(check, checkboxName) {
	var checkboxs = document.getElementsByName(checkboxName);
	for(var i = 0; i < checkboxs.length; i++) {
		checkboxs[i].checked = check.checked;
	}
}

/**
 * 获取选择项
 * @param checkboxName
 * @return
 */
function getSelectCheckbox(checkboxName) {
	var checkboxs = document.getElementsByName(checkboxName);
	var select = "";
	for(var i = 0; i < checkboxs.length; i++) {
		if(checkboxs[i].checked) {
			select += checkboxs[i].value+",";
		}
	}
	return select;
}

function numToTime(num) {
	var dd, hh, mm;
	
	if(num < 0){	// 今天前
		if(num%24 !=0){
			dd = -(parseInt((-num) / 100 / 24) + 1);
			mm = (-((dd+1) * 2400 - 2360 -num)) % 100;
			
		}else{
			dd = parseInt(num/100/24);
			mm = 0;
		}
		
		hh = parseInt((-((dd+1) * 2400 - 2360 -num)) / 100);
		if(mm == 60){
			hh = hh+1;
			mm = 0;
		}
	}else{
		dd = parseInt(num / 100 / 24);
		hh = parseInt((num - dd*2400) / 100);
		mm = parseInt((num - dd*2400) % 100);
	}
	
	
	return "{'day':" + dd + ", 'hour':" + hh + ", 'min':" + mm + "}"; 
}

function timeToNum(dd, hh, mm) {
	var today = hh*100 + mm;
	
	switch (dd) {
	case -1:
	case -2:
	case -3:
	case -4:
	case -5:
	case -6:
	case -7:
	case -8:
	case -9:
	case -10:
		return getSubInt(today) + (2400*(dd+1));
	case 0:
		return today;
	case 1:
	case 2:
	case 3:
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
	case 9:
	case 10:
		return today + 2400*dd;
	}
}

function getSubInt(compInt){
	var temp = compInt - 2360;
	if(temp %100 == -60){
		return (parseInt(temp / 100) -1) * 100;
	}else{
		return temp;
	}
}

function resetForm(formId) {
	$('#'+formId).each(function(){
         this.reset();
	});
}

function showDivInfo(max, id, orderId) {
	for(var i = 1;i <= max; i++) {
		if(i == id) {
			if ($("#parent_element_"+i+"_"+orderId).is(":hidden")) {
        		$("#parent_element_"+i+"_"+orderId).fadeIn("slow");
        		$("#child_element_"+i+"_"+orderId).slideDown("slow");
      		} else {
      			$("#parent_element_"+i+"_"+orderId).fadeOut("slow");
      			$("#child_element_"+i+"_"+orderId).slideUp("slow");
      		}
		} else {
			$("#parent_element_"+i+"_"+orderId).fadeOut("slow");
			$("#child_element_"+i+"_"+orderId).slideUp("slow");
		}
	}
}

/**
 * 显示遮盖层
 * @return
 */
function showBgLayer() {
	var temp_float = '';
	if (!document.getElementById("floatBoxBg")) {
		temp_float = "<div id='floatBoxBg' style='height:" + $(document).height() + "px;width:" + $(document).width() + "px;filter:alpha(opacity=60);opacity:0;'></div>";
		temp_float += "<div class='wait' id='waitDiv'><span>正在操作...</span></div>";
		$("body").append(temp_float);
	}
	
	$('#waitDiv').show();
	$("#floatBoxBg").show();
	$("#floatBoxBg").animate({opacity:"0.1"}, "normal");
}

/**
 * 隐藏遮盖层
 * @return
 */
function hideBgLayer() {
	if(document.getElementById("floatBoxBg")) {
		$("#floatBoxBg").animate({opacity:"0.1"}, "normal", function () {
			$("#floatBoxBg").hide();
			$('#waitDiv').hide();
		});
	}
}
/**
 * 高级查询
 */
var _clickCount = 0;
function conditionSwitch(){
	_clickCount++;
	if(_clickCount % 2 == 1) { //1 show
		showAdvancedPanel();
	} else { //0 hide
		hideAdvancedPanel();
	}
}
function showAdvancedPanel() {
	$('#advanced').slideDown('slow');
}

function hideAdvancedPanel() {
	$('#advanced').slideUp('slow', function() {
	});
}

function closeMsgPanel() {
	var message_hint = document.getElementById("message_hint");
	message_hint.style.display = 'none';
}
function showMsgPanel(flag, msg) {
	var message_hint = document.getElementById("message_hint");
	message_hint.style.display = '';
	var inner_msg = document.getElementById("inner_msg");
	inner_msg.innerHTML = msg;
	if(flag) {
		inner_msg.style.color = 'green';
	} else {
		inner_msg.style.color = 'red';
	}
	
}

