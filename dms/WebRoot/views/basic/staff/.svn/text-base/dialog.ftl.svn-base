<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/deptDialog.js"></script>
		<script type="text/javascript">
        	var api = frameElement.api, W = api.opener;
			api.button({
			    id:'valueOk',
				name:'确定',
				callback:ok
			},{name:'取消'});
			
			function ok()
			{
				
				var dId = W.document.getElementById('deptIds').value;
				var returnkey = getChkKeys('dept',dId);				
			    W.document.getElementById('deptIds').value += returnkey;
			    if(returnkey){
				    W.document.getElementById('deptNames').value += getChkVals('dept','Ndept');
			    }
			};
			
			function formSubmit(pagenum) {
				$("#pageNum").val(pagenum);
        		$('#vcDepartmentCode').val(getVcdepartmentId());
        		var goldForm = document.getElementById('dialogForm');
				goldForm.submit();
        	}
		</script>
	</head>

	<body>
		<div class="iner_title_sub_03">
			<form action="${contextPath}/basic/staff/dialog.action" id="dialogForm" method="post">
				<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
				<input type="hidden" id ="pageNum" name="pageNum" value=""/>
				<input type="hidden" name="areainfoId" id="vcDepartmentCode" value="${dialogArea}" />
				<input type="hidden" id="areadisplay" value="${dialogAreaDisplay}" />
				<input type="hidden" id="areacontrol" value="${dialogArea}" />
				<input type="hidden" id="allparam" name="allparam" value="${allparam}" />
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
				<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" /> 
			</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table">
			<tr>
				<th>&nbsp</th>
				<th>序号</th>
				<th>机构名称</th>
			</tr>
			<#list page.resultSet as dept>
				<tr>
	                <td align="center"><input type="checkbox" name="dept_${dept_index+1}" id="dept_${dept_index+1}" value="${dept.pid}"/></td>
	                <td align="center">${dept_index+1}</td>
	                <td align="center">${dept.vcDeptName!""}<input type="hidden" id="Ndept_${dept_index+1}" value="${dept.vcDeptName!""}" />&nbsp;</td>
				</tr>
			</#list>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/basic/staff/dialog.action" escapeAmp="false">   
		    	<@s.param name="search.vcRealName" ><@s.property value="search.vcRealName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
