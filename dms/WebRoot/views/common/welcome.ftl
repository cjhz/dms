<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
	</head>
	<body>
		<#include "msg_panel.ftl">
		<div class="iner_title_name"><strong>欢迎使用<@s.text name="pm.project.name" /></strong></div>
		<div class="iner_title_sub_03">
			<strong>账号信息</strong>
		</div>
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			<tr>
				<th width="20%">登录用户：</th>
				<td width="30%"><@s.property value="user.loginName" escape="false"/>&nbsp;</td>
				<th width="20%">登录时间：</th>
				<td width="30%"><@s.date name="user.loginTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
			</tr>
			
			<tr>
				<th>登录IP：</th>
				<td><@s.property value="user.ip" />&nbsp;</td>
				<th>账号状态：</th>
				<td>
				<#if user.userstatus?default(0)==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN")>
					${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NOTOPEN")}
				<#elseif user.userstatus?default(0)==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL")>
					${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NORMAL")}
				<#elseif user.userstatus?default(0)==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE")>
					${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_DISABLE")}
				</#if>
				</td>
			</tr>
			
			<tr>
				<th>角色：</th>
				<td><@s.property value="user.roleName" />&nbsp;</td>
				<th>管辖地区：</th>
				<td><@s.property value="user.areaName" escape="false"/>&nbsp;</td>
			</tr>
		</table>
	</body>
</html>
