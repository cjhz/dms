<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
        	var param = 'search.vcName=' + encodeURI('<@s.property value="search.vcName" escape="false"/>') 
						+ '&pageNum=<@s.property value="pageNum" escape="false" />'
						+ '&search.pmBscAreaInfo.pid=<@s.property value="search.pmBscAreaInfo.pid" escape="false"/>'
						+ '&search.pmBscAreaInfo.vcDepartmentCode=<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
        	function doCancel() {
        		var url = '${contextPath}/common/pageQuery.action?' + param;
        		document.location = url;
        	}
     
        	function formSubmit() {
        		$("#roleNameMsg").html("");
        		setcode();
        		//getChkVal('ruleDept');
				var loginForm = document.getElementById('loginForm');
				if(!Validator.Validate(loginForm, 3)) {
					return false;
				}
				var vcLoginName = $.trim($('#vcLoginName').val());
				var url = '${contextPath}/common/ajaxCheckLoginNameUnique.action';
				$.post(url, {'pmbsclogininfo.vcLoginName': vcLoginName}, function(jsonObj) {
					if(jsonObj.timeout) {
						timeoutRedirect('${contextPath}');
					} else if(!jsonObj.flag){
						$('#roleNameMsg').css('color', 'red');	
						$('#roleNameMsg').html(jsonObj.msg);
						return false;
					}else{
						loginForm.submit();
					}
				}, 'json');
				
			}
			function setcode(){
				$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>账号管理>>新增</strong></div>
		<#include "msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return">返回</a>
		</div>
		<@s.set name="formUrl" value="'/common/create.action'" />
		<@s.set name="editDept" value="true" />
		<#include "form.ftl" />
	
	</body>
</html>
