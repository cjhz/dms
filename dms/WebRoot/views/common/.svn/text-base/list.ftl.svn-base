<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'search.vcName=' + encodeURI('<@s.property value="search.vcName" escape="false"/>') 
						+ '&pageNum=<@s.property value="pageNum" escape="false" />'
						+ '&search.pmBscAreaInfo.pid=<@s.property value="search.pmBscAreaInfo.pid" escape="false"/>'
						+ '&search.pmBscAreaInfo.vcDepartmentCode=<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
        	function addLoginInfo() {
        		var url = '${contextPath}/common/add.action?' + param;
        		document.location = url;
        	}
        	function modifyLoginInfo(loginId) {
        		var url = '${contextPath}/common/edit.action?' + param + '&loginId=' + loginId;
        		document.location = url;
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
        	function removeLoginInfo(loginId) {
        		if(confirm('确认删除?')) {
        			var url = '${contextPath}/common/destory.action?' + param + '&loginId=' + loginId;
        			document.location = url;
        		}
        	}
        	function show(loginId) {
        		var url = '${contextPath}/common/show.action?' + param + '&loginId=' + loginId;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		setcode();
        		var loginForm = document.getElementById('loginForm');
				loginForm.submit();
        	}
        	function setcode(){
				$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
			}
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>系统管理>>账号管理</strong></div>
		<#include "msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<form action="${contextPath}/common/pageQuery.action" method="post" id="loginForm" >
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
			<input type="hidden" name="search.pmBscAreaInfo.pid" id="departmentid" value="" />
			<input type="hidden" name="search.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="" />
			<input type="hidden" id="areadisplay" value="<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
			<input type="hidden" id="areacontrol" value="${user.areaCode}" />

		地区：<select class="serch" id="province" onchange="cascade(1, '${user.areaCode}', '${user.areaCode}');">
					<option value="" selected="selected">
						省
					</option>
				</select>
				<select class="serch" id="city" onchange="cascade(2, '${user.areaCode}', '${user.areaCode}');">
					<option value="" selected="selected">
						市
					</option>
				</select>
				<select class="serch" id="county">
					<option value="" selected="selected">
						区/县
					</option>
					</select>
				
				姓名：<input type="text" name="search.vcName" value="<@s.property value="search.vcName" escape="false"/>" class="form_input" size="12"
				  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" /> 
				<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" /> 
				<input type="button" size="12" value="　新增　" onclick="addLoginInfo()"/>
				<font id="remLen1">&nbsp;</font>
				<font color=red id="remLen2">&nbsp;</font>
				<font id="remLen3">&nbsp;</font>
				<font color=red id="remLen4">&nbsp;</font>
				<font id="remLen5">&nbsp;</font>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table">
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>身份证号码</th>
				<th>账号</th>
				<th>账号状态</th>
				<th>管辖地区</th>
                <th>操作</th>
			</tr>
			<#list page.resultSet as loginInfo >
				<tr>
	                <td align="center">${loginInfo_index+1}</td>
	                <td align="center">${loginInfo.vcName!""}&nbsp;</td>
	                <td align="center"><#if loginInfo.vcCardNo?exists && (loginInfo.vcCardNo?length>4)>${loginInfo.vcCardNo.substring(0,loginInfo.vcCardNo.length()-4)+'XXXX'!""}</#if>
	                				   <#if loginInfo.vcCardNo?exists && (loginInfo.vcCardNo?length<=4)>${loginInfo.vcCardNo!""}</#if>&nbsp;</td>
	                <td align="center">${loginInfo.vcLoginName!""}&nbsp;</td>
	                <td align="center">
	                <!--${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1")}-->
	                <@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NOTOPEN"/></@s.if>
	                <@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_NORMAL"/></@s.if>
	                <@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_VAL_DISABLE"/></@s.if>
	                &nbsp;</td>
	                 <td align="center">${loginInfo.pmBscAreaInfo.vcAreaName!""}&nbsp;</td>
					<td align="center" width="35%">
						<a href="javascript:show(${loginInfo.pid})" class="see" >查看</a>
						<a href="javascript:modifyLoginInfo(${loginInfo.pid})" class="edit" >修改</a>
						<@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN"><a href="javascript:stopOropen(${loginInfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"/>)" class="edit" >启用</a></@s.if>
						<@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"><a href="javascript:stopOropen(${loginInfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"/>)" class="edit" >停用</a></@s.if>
						<@s.if test="${loginInfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"><a href="javascript:stopOropen(${loginInfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"/>)" class="edit" >启用</a></@s.if>
						<a href="javascript:removeLoginInfo(${loginInfo.pid})" class="delete" >删除</a>
					</td>
				</tr>
			</#list>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/common/pageQuery.action" escapeAmp="false">   
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "pagination.ftl" />
		</div>
	</body>

</html>
