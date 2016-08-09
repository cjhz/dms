<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
    <head>
    	<title><@s.text name="pm.project.name" /></title>
       	<link href="${contextPath}/theme/${theme}/css/home.css" type="text/css" rel="stylesheet" />
		<link href="${contextPath}/theme/${theme}/css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
		<#--
		<script type="text/javascript" src="${contextPath}/js/datepicker/WdatePicker.js"></script>
		-->
        <script type="text/javascript">
            $(document).ready(function(){
                $(".list_menu").hide();
                var h3number = $("h3").length;
                var element = $("h3").height();
                var bolt_left = $("#bolt_left").height();
                $(".list_menu").css("height", (bolt_left-(element*(h3number+1)))+"px");
                
                $(".bolt_left_menu h3").click(function() {
                    var current = $(this).next();
                    $(".bolt_left_menu h3").each(function(){
                        if (current != $(this).next()) {
                            this.className = "";
                        }
                    });
					this.className = "select";
					if($(this).next().is(':visible')) {
						this.className = "";
					}
                    
                    $('.list_menu').each(function(){ //合上其他已经展开的菜单
                        if ($(this) != current && $(this).is(':visible')) {
                            $(this).slideUp("slow"); //合上
                        }
                    })
                    if ($(this).next().is(':visible')) { //如果对象可见
                        $(this).next().slideUp("slow"); //合上
                    }
                    else {
                        $(this).next().slideDown("slow");
                    }
					
                });
               if(${ifalert?string('true','false')}){
               	   alert("请先进行机构新增！");
               	   var url='${contextPath}/department/deptinfo/searchDepType.action';
               	   mainframe.location=url;
               }
            });
            // 修改密码
            function modifyPassword(loginId) {
	    		var url ='${contextPath}/common/passwordEdit.action?loginId=' + loginId ;
	    		mainframe.location = url;
	    	}
        </script>
    </head>
    <body>
		<div class="bolt_header">
			<div class="logo"><img src="${contextPath}/theme/${theme}/images/logo.jpg" alt="<@s.text name="pm.project.name" />" /></div>
			<div class="close">
				<span><a target="mainframe" href="${contextPath}/common/navigation.action">[回首页]</a></span><span class="user"><a target="mainframe" href="${contextPath}/common/showuser.action">[<@s.property value="user.roleName" escape="false" />]</a>　<@s.property value="user.realName" escape="false" /> 您好！</span><span>  　|　<a href="javascript:modifyPassword(<@s.property value="user.id" escape="false" />);">修改密码</a>  　|　<a href="${contextPath}/common/logout.action">退出</a></span><br />
				<!--<p>${version}</p>-->
			</div>
		</div>
		
		<div class="bolt_left" id="bolt_left">
			<div class="bolt_left_top"><img src="${contextPath}/theme/${theme}/images/left_top_img.jpg" alt="左边菜单" /></div>
			<div class="bolt_left_menu">
				<@s.property value="jsonText" escape="false" />
			</div>
			
			<div class="bolt_left_bottom"><img src="${contextPath}/theme/${theme}/images/left_bottom_img.jpg" alt="左边菜单" /></div>
		</div>
		
		<div class="bolt_main">
			<iframe frameborder="no" allowtransparency="yes" id="mainframe" name="mainframe" src="${contextPath}/common/navigation.action"></iframe>
		</div>
    </body>
</html>
