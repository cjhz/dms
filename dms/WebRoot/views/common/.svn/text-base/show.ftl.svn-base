<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
		<script type="text/javascript">
        	var param = 'search.vcName=' + encodeURI('<@s.property value="search.vcName" escape="false"/>')
        		+ '&pageNum=<@s.property value="pageNum" escape="false" />'
        		+ '&search.pmBscAreaInfo.pid=<@s.property value="search.pmBscAreaInfo.pid" escape="false"/>'
				+ '&search.pmBscAreaInfo.vcDepartmentCode=<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
        	function modify(loginId) {
        		document.location = '${contextPath}/common/edit.action?' + param + '&loginId=' + loginId;
        	}
        	function query() {
        		document.location = '${contextPath}/common/pageQuery.action?' + param;
        	}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>账号管理>>查看</strong></div>
		<#include "msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:modify(<@s.property value="pmbsclogininfo.pid" escape="false"/>)" class="edit">编辑</a>
		</div>
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			 <tr>
			    <th colspan="4" style="text-align:center;"><strong>账号信息</strong> </th>
			  </tr>
			  <tr>
		      <th>管辖区域：</th>
		      <td colspan="3"><@s.property value="pmbsclogininfo.pmBscAreaInfo.vcAreaName" escape="false"/>&nbsp;</td>
		    </tr>
		    <#--<tr>
		      <th>管辖机构：</th>
		      <td colspan="3">
		      <#list ruleDeptList as dept >
			    	<@s.if test="${dept!0}==@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_KEY_1"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_1"/></@s.if>
			    	<@s.if test="${dept!0}==@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_KEY_2"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_2"/></@s.if>
			    	<@s.if test="${dept!0}==@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_KEY_3"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_3"/></@s.if>
			    	<#if dept_has_next>,</#if>&nbsp;
			   </#list>
		    </tr>-->
		    <tr>
		      <th>身份证号码：</th>
		      <td><@s.property value="pmbsclogininfo.vcCardNo.substring(0,pmbsclogininfo.vcCardNo.length()-4)+'XXXX'" escape="false"/>&nbsp;</td>
		      <th>姓名：</th>
		      <td><@s.property value="pmbsclogininfo.vcName" escape="false"/>&nbsp;</td>
		    </tr>
		    <tr>
		      <th>账号：</th>
		      <td><@s.property value="pmbsclogininfo.vcLoginName" escape="false"/>&nbsp;</td>
		      <th>账号状态：</th>
		      <td>
		      <@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NOTOPEN"/></@s.if>
		      <@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NORMAL"/></@s.if>
		      <@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_DISABLE"/></@s.if>
		      &nbsp;</td>
		    </tr>
		    <tr>
		      <th>账号权限：</th>
		      <td colspan="3"><@s.property value="pmbsclogininfo.pmMngRoleInfo.vcRoleName" escape="false"/>&nbsp;</td>
		    </tr>
		    <tr>
		      <th>备注：</th>
		      <td colspan="3"><@s.property value="pmbsclogininfo.vcRemark" escape="false"/>&nbsp;</td>
		    </tr>
		    <tr>
		      <th>&nbsp;</th>
		      <th colspan="2" style="text-align:center;"><input type="button" onclick="query()" value="  返回  " />&nbsp;</th>
		      <th>&nbsp;</th>
		    </tr>
		</table>
	</body>
</html>
