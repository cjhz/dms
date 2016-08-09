<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRoleName=' + encodeURI('<@s.property value="search.vcRoleName" escape="false"/>');
        	function addRole() {
        		var url = '${contextPath}/permission/role/add.action?' + param;
        		document.location = url;
        	}
        	function modifyRole(roleId) {
        		var url = '${contextPath}/permission/role/edit.action?' + param + '&roleId=' + roleId;
        		document.location = url;
        	}
        	function removeRole(roleId) {
        		if(confirm('确认删除?')) {
        			var url = '${contextPath}/permission/role/destory.action?' + param + '&roleId=' + roleId;
        			document.location = url;
        		}
        	}
        	function assignOper(roleId) {
        		var url = '${contextPath}/permission/role/roleOperPage.action?' + param + '&roleId=' + roleId;
        		document.location = url;
        	}
        	function show(roleId) {
        		var url = '${contextPath}/permission/role/show.action?' + param + '&roleId=' + roleId;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		var roleForm = document.getElementById('roleForm');
				roleForm.submit();
        	}
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>系统管理>>角色管理</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<form id="roleForm" action="${contextPath}/permission/role/pageQuery.action" method="post">
				<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
				<input type="hidden" id ="pageNum" name="pageNum" value=""/>
				角色名：<input type="text" name="search.vcRoleName" value="<@s.property value="search.vcRoleName" escape="false"/>" class="form_input" size="20" 
				 onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)"/> 
				<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" /> 
				<input type="button" size="12" value="　新增　" onclick="addRole()"/>
				<font id="remLen1">&nbsp;</font>
				<font color=red id="remLen2">&nbsp;</font>
				<font id="remLen3">&nbsp;</font>
				<font color=red id="remLen4">&nbsp;</font>
				<font id="remLen5">&nbsp;</font>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table">
			<tr>
				<th>角色名</th>
                <th>备注</th>
                <th>操作</th>
			</tr>
			<@s.iterator value="page.resultSet" status="status">
				<tr>
	                <td align="center"><@s.property value="vcRoleName" escape="false"/>&nbsp;</td>
					<td align="left"><@s.property value="vcMemo" escape="false"/>&nbsp;</td>
					<td align="center" width="35%">
						<a href="javascript:show(<@s.property value="pid" escape="false" />)" class="see" >查看</a>
						<a href="javascript:modifyRole(<@s.property value="pid" escape="false" />)" class="edit" >修改</a>
						<a href="javascript:assignOper(<@s.property value="pid" escape="false" />)" class="edit" >权限分配</a>
						<a href="javascript:removeRole(<@s.property value="pid" escape="false" />)" class="delete" >删除</a>
					</td>
				</tr>
			</@s.iterator>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/permission/role/pageQuery.action" escapeAmp="false">   
		    	<@s.param name="search.vcRoleName" ><@s.property value="search.vcRoleName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
