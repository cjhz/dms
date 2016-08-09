<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title>机构信息管理</title>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'searchPmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="searchPmBscDeptInfo.vcDeptName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&searchPmBscDeptInfo.pmBscDeptType.pid=<@s.property value="searchPmBscDeptInfo.pmBscDeptType.pid" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.pmBscDict.pid=<@s.property value="searchPmBscDeptInfo.pmBscDict.pid" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.vcRender=<@s.property value="searchPmBscDeptInfo.vcRender" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.pid" escape="false"/>'
			+'&searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
			
			function editDeptType(pid,type){
				if(type == 'edit'){
					document.location = '${contextPath}/department/deptinfo/toEditDepType.action?pid='+pid+"&"+param;
				}else if(type == 'del'){
					document.location = '${contextPath}/department/deptinfo/deleteDepType.action?pid='+pid+"&"+param;
				}else if(type == 'view'){
					document.location = '${contextPath}/department/deptinfo/toViewDepType.action?pid='+pid+"&"+param;
				}
			}
			
			function formSubmit(pagenum) {
				$("#pageNum").val(pagenum);
        		setcode();
        		var goldForm = document.getElementById('deptForm');
				goldForm.submit();
        	}
        	
        	function setcode(){
				$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
			}
			
			function addDeptType(){
				document.location = '${contextPath}/department/deptinfo/toAddDepType.action?'+param;
			}
        	function exp() {
        		setcode();
        		var url = '${contextPath}/department/deptinfo/export.action?'+param;
        		document.location = url;
        	}
		</script>
	</head>
	<body>
	<div class="iner_title_name"><strong>机构信息管理>>机构信息管理</strong></div>
	<#include "../../common/msg_panel.ftl" />
	<#include "../../common/export/expform.ftl" />
	<div class="iner_title_sub_03">
	<form id="deptForm" action="${contextPath}/department/deptinfo/searchDepType.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
			<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="" />
			<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="" />
			<input type="hidden" id="areadisplay" value="<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
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
					 机构职级：<select class="form_select" name="searchPmBscDeptInfo.pmBscDict.pid" id="select3">
			   	<option value="0">==请选择==</option>
		        <#list pmbscdictlist as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==searchPmBscDeptInfo.pmBscDict.pid">selected</@s.if>>${dict.vcName!""}</option>
			     </#list>
		      </select><br>&nbsp;&nbsp;
				机构性质：<select class="form_select" name="searchPmBscDeptInfo.pmBscDeptType.pid" id="select1">
			   	<option value="0">==请选择==</option>
		        <#list pmbscdepttypelist as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==searchPmBscDeptInfo.pmBscDeptType.pid">selected</@s.if>>${dict.vcTypeName!""}</option>
			     </#list>
			     </select>
			    
		      机构立功表彰情况：<select class="form_select" name="searchPmBscDeptInfo.vcRender" id="select2">
		         <option value="0">==请选择==</option>
		         <#list pmBscObserTypelist as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==searchPmBscDeptInfo.vcRender">selected</@s.if>>${obserType.vcName!""}</option>
			     </#list>
		      </select><br>&nbsp;&nbsp;
		      机构名称：<input tpye="text" id="searchPmBscDeptInfo.vcDeptName" name="searchPmBscDeptInfo.vcDeptName" value="<@s.property value="searchPmBscDeptInfo.vcDeptName" escape="false"/>" class="form_input" size="35" onClick="checkMaxInput(this,50)" onKeyDown="checkMaxInput(this,50)" onKeyUp="checkMaxInput(this,50)" onBlur="clsInput(this)"/>
		       &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" />
		       <input type="button" onclick="addDeptType();" size="12" value="　新增　" /> 
			    <input type="button" size="12" value=" 导出 " onclick="exp()" />
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
			            	<td align="center">&nbsp;
			            		<a href="javascript:editDeptType(${pmbscdeptinfo.pid},'view');" class="see" >查看</a>
			            		<a href="javascript:editDeptType(${pmbscdeptinfo.pid},'edit');" class="edit" >修改</a>
								<a href="javascript:void(0)" onclick="javascript:divDel(function(){editDeptType(${pmbscdeptinfo.pid},'del')});" class="delete" >删除</a>
			            	</td>
			            </tr>
			         </#list>
				</@s.if>
			</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/department/deptinfo/searchDepType.action" escapeAmp="false">   
		    	<@s.param name="searchPmBscDeptInfo.vcDeptName" ><@s.property value="searchPmBscDeptInfo.vcDeptName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>
</html>
