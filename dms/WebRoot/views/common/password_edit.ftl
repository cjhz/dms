<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			function formSubmit() {
				var message_hint = document.getElementById("message_hint");
				if(message_hint){
					message_hint.style.display = 'none';
					$("#passwordMsg").html("");
				};
				var staffForm = document.getElementById('staffForm');
				if(!Validator.Validate(staffForm, 3)) {
					return false;
				}
				if($('#password1').val() != $('#password2').val()) {
					$('#passwordMsg').text('两次输入的密码不一致');
					return false;
				}
				
				var staffId = '<@s.property value="user.id" escape="false" />';
				var passwordMd5 = $('#password1').val();
				var url = '${contextPath}/common/ajaxModifyPassword.action';
        		
        		$.post(url, {'pmbsclogininfo.pid': staffId, 'pmbsclogininfo.vcLoginPwd': passwordMd5}, function(jsonObj){
        			if(jsonObj.timeout) {
						timeoutRedirect('${contextPath}');
					} else {
						showMsgPanel(jsonObj.flag, jsonObj.msg);
					}
        		}, "json");
			}
		</script>
	</head>
	<body>
		
		<div class="iner_title_name"><strong>修改密码</strong></div>
		<#include "msg_panel.ftl" />
		<form id="staffForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
				<tr>
					<th style="width:30%">
						<span class="require">*</span>密码：
					</th>
					<td>
						<input type="password" name="pmbsclogininfo.vcLoginPwd" id="password1" require="true"  class="add_input form_input" onKeyUp="checkMaxInput(this,8)" onBlur="clsInput(this)" msg="密码不能为空！" trim="true" dataType="Require" /><br/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="require">*</span>二次输入密码：
					</th>
					<td>
						<input type="password" id="password2" class="add_input form_input" onKeyUp="checkMaxInput(this,8)" onBlur="clsInput(this)" msg="二次密码不能为空！" trim="true" require="true" dataType="Require" /><br/>
						<span id="passwordMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td colspan="5" >
							<font id="remLen1">&nbsp;</font>
							<font color=red id="remLen2">&nbsp;</font>
							<font id="remLen3">&nbsp;</font>
							<font color=red id="remLen4">&nbsp;</font>
							<font id="remLen5">&nbsp;</font>&nbsp;
					</td>
				</tr>
				<tr>
			<th>&nbsp;</th>
			<td>
				<input  type="button" value="　保存　" onclick="formSubmit()"/>
			</td>
		</tr>
			</table>
		</form>
	</body>
</html>
