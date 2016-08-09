<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			//$(function(){
			//	setChkVal2('ruleDept');
			//});
        	var param = 'search.vcName=' + encodeURI('<@s.property value="search.vcName" escape="false"/>')
        		+ '&pageNum=<@s.property value="pageNum" escape="false" />'
				+ '&search.pmBscAreaInfo.pid=<@s.property value="search.pmBscAreaInfo.pid" escape="false"/>'
				+ '&search.pmBscAreaInfo.vcDepartmentCode=<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
        	function doCancel() {
        		var url = '${contextPath}/common/pageQuery.action?' + param;
        		document.location = url;
        	}
        	
        	function formSubmit() {
        		setcode();
        		//getChkVal('ruleDept');
				var loginForm = document.getElementById('loginForm');
				if(!Validator.Validate(loginForm, 3)) {
					return false;
				}
				loginForm.submit();
			}
			function stopOropen(loginId,openflag) {
	        	var url = '${contextPath}/common/stopOpen.action?' + param + '&loginId=' + loginId+ '&openFlag='+openflag;
        		if(openflag==1){
	        		if(confirm('确认启用?')) {
		        		document.location = url;
	        		}
        		}
        		if(openflag==2){
	        		if(confirm('确认停用?')) {
		        		document.location = url;
	        		}
        		}
        	}
        	function setcode(){
				$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>账号管理>>修改</strong></div>
		<#include "msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return">返回</a>
		</div>
		<@s.set name="edit" value="true" />
		<@s.set name="formUrl" value="'/common/update.action'" />
		<#include "form.ftl" />
	</body>
</html>
