<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title>机构立功表彰信息管理</title>
		<script type="text/javascript">
			var param = 'searchpmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="searchpmBscDeptInfo.vcDeptName" escape="false"/>')
			+'&searchpmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.pid" escape="false" default="0"/>'
			+'&searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false" default="0"/>';
			function editDeptType(pid,type){
				//if(type == 'view'){
					document.location = '${contextPath}/department/deptobserving/searchDept_observing.action?pid='+pid +'&'+param;
				//}else if(type == 'add'){
				//	document.location = '${contextPath}/department/deptobserving/toAddDepType.action?pid='+pid;
				//}
				
			}
			
			function formSubmit(pagenum) {
				$("#pageNum").val(pagenum);
				$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
        		var goldForm = document.getElementById('deptForm');
				goldForm.submit();
        	}
		</script>
	</head>
	<body>
	<div class="iner_title_name"><strong>机构信息管理>>机构立功表彰>>机构立功表彰维护</strong></div>
	<#include "../../common/msg_panel.ftl" />
	<div class="iner_title_sub_03">
			<form id="deptForm" action="${contextPath}/department/deptobserving/searchDepType.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
		    <input type="hidden" name="searchpmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="" />
			<input type="hidden" name="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="" />
			<input type="hidden" id="areadisplay" value="<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
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
				</select><br>&nbsp;&nbsp;
				机构名称：<input tpye="text" id="searchpmBscDeptInfo.vcDeptName" name="searchpmBscDeptInfo.vcDeptName" value="<@s.property value="searchpmBscDeptInfo.vcDeptName" escape="false"/>" class="form_input" size="35"  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" />
		       &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" />
				<font id="remLen1">&nbsp;</font>
				<font color=red id="remLen2">&nbsp;</font>
				<font id="remLen3">&nbsp;</font>
				<font color=red id="remLen4">&nbsp;</font>
				<font id="remLen5">&nbsp;</font>
			</form>
		</div>
		<table width="100px" cellpadding="0" cellspacing="0" class="main_table" border="0">
			<tr>
				<th>序号</th>
				<th>地区</th>
				<th>机构名称</th>
				<th>机构性质</th>
				<th>机构职级</th>
				<th>联系电话</th>
				<th>操作</th>
			</tr>
			<@s.if test="page.resultSet == null || page.resultSet.size()<1">
				<tr align="center">
					<td colspan="8">
						暂无符合数据...
					</td>
				</tr>
			</@s.if>
			<@s.if test="page.resultSet != null && page.resultSet.size()>0">
				<#list page.resultSet as pmbscdeptinfo>
		            <tr>
		            	<td align="center">
		            		${pmbscdeptinfo_index+1}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptinfo.pmBscAreaInfo.vcAreaName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptinfo.vcDeptName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptinfo.pmBscDeptType.vcTypeName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptinfo.pmBscDict.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptinfo.vcContactPhone!""}&nbsp;
		            	</td>
		            	<td align="center"> 
			            	<!--<a href="javascript:editDeptType(${pmbscdeptinfo.pid},'add');" class="add" >新增</a>-->
			            	<a href="javascript:editDeptType(${pmbscdeptinfo.pid},'view');" class="see" title="查看该机构立功表彰">立功表彰管理</a>
		            	</td>
		            </tr>
		        </#list>
			</@s.if>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/department/deptobserving/searchDepType.action" escapeAmp="false">   
		    	<@s.param name="pmBscDeptInfo.vcDeptName" ><@s.property value="pmBscDeptInfo.vcDeptName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>
</html>
