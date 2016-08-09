<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<link href="${contextPath}/theme/${theme}/css/ltree.checkbox.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${contextPath}/js/ltree.packed.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRoleName=' + encodeURI('<@s.property value="search.vcRoleName" escape="false"/>')
        		+ '&pageNum=<@s.property value="pageNum" escape="false" />';
        	function doCancel() {
        		var url = '${contextPath}/permission/role/pageQuery.action?' + param;
        		document.location = url;
        	}
        	
        	function modifyRole() {
        		var url = '${contextPath}/permission/role/edit.action?' + param + '&roleId=<@s.property value="role.pid" />';
        		document.location = url;
        	}
        	
			$(document).ready(function() {
				var lTree = new lTREE();
				lTree.config({path: "dl dd"});
				lTree.tagName({ folder: "DD", file: "DT"});
				lTree.className({folderClose: "folderClose", lastChild: "lastChild"});
				lTree.build("treeContainer");
			});
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>角色管理>>查看</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel()" class="return">返回</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:modifyRole()" class="edit">编辑</a>
		</div>
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			<tr>
				<th width="20%">角色名：</th>
				<td width=" 80%"><@s.property value="role.vcRoleName" escape="false"/></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<@s.property value="role.vcMemo" escape="false" />
				</td>
			</tr>
			<tr>
				<th>操作：</th>
				<td>
					<div class="lTREEMenu lTREENormal" id="treeContainer" style="width:500px;border:1px solid #ccc;margin:3px;padding:3px;height:250px;overflow:auto;" >
						<@s.property value="jsonText" escape="false" />
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
