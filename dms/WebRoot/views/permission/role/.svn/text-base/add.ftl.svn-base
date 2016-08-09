<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
        	var param = 'search.vcRoleName=' + encodeURI('<@s.property value="search.vcRoleName" escape="false"/>')
        		+ '&pageNum=<@s.property value="pageNum" escape="false" />';
        	function doCancel() {
        		var url = '${contextPath}/permission/role/pageQuery.action?' + param;
        		document.location = url;
        	}
        	function checkRoleNameUnique() {
        		var roleName = $.trim($('#vcRoleName').val());
				var roleId = $('#roleId').val();
				
				if(roleName == '') return;
				$('#__ErrorMessagePanel').empty();
				if(roleName.length < 1 || roleName.length > 50) {
					$('#roleNameMsg').css('color', 'red');
					$('#roleNameMsg').html('长度不合法！');
					return;
				}
				
				var reg = /^[^\~\^\<\>\\\&\《\》]+$/;
//				if(!reg.test(roleName)) {
//					$('#roleNameMsg').css('color', 'red');
//					$('#roleNameMsg').html('格式不合法！');
//					return;
//				}
				
				var url = '${contextPath}/permission/role/ajaxCheckRoleNameUnique.action';
				$.post(url, {'role.vcRoleName': roleName, 'role.pid': roleId}, function(jsonObj) {
					if(jsonObj.timeout) {
						timeoutRedirect('${contextPath}');
					} else if(jsonObj.flag) {
						$('#roleNameMsg').css('color', 'green');
						$('#roleNameMsg').html(jsonObj.msg);
					} else {
						$('#roleNameMsg').css('color', 'red');	
						$('#roleNameMsg').html(jsonObj.msg);
					}
				}, 'json');
				clsInput($('#vcRoleName'));
        	}
        	function formSubmit() {
				var roleForm = document.getElementById('roleForm');
				$('#roleNameMsg').empty();
				if(!Validator.Validate(roleForm, 3)) {
					return false;
				}
				
				roleForm.submit();
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>角色管理>>新增</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return">返回</a>
		</div>
		<@s.set name="formUrl" value="'/permission/role/create.action'" />
		<#include "form.ftl" />
	</body>
</html>
