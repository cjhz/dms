<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title>机构信息管理>>机构立功表彰信息管理</title>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'pmBscObserving.pmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="pmBscObserving.pmBscDeptInfo.vcDeptName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&pmBscObserving.pmBscDeptInfo.pmBscDeptType.pid=<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscDeptType.pid" escape="false" default="0"/>'
			+'&pmBscObserving.pmBscDeptInfo.pmBscDict.pid=<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscDict.pid" escape="false" default="0"/>'
			+'&pmBscObserving.pmBscObserType.pid=<@s.property value="pmBscObserving.pmBscObserType.pid" escape="false" default="0"/>'
			+'&pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.pid" escape="false" default=""/>';
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
        	function exp() {
        		var url = '${contextPath}/department/deptobserving/export.action?'+param;
        		document.location = url;
        	}
        	function editDeptType(pid,canceltype){
				document.location = '${contextPath}/department/deptobserving/toViewDeptObserving.action?pid='+pid+'&canceltype='+canceltype+'&'+param;
			}
		</script>
	</head>
	<body>
	<div class="iner_title_name"><strong>机构信息管理>>机构立功表彰>>机构立功表彰查询</strong></div>
	<#include "../../common/msg_panel.ftl" />
	<#include "../../common/export/expform.ftl" />
	<div class="iner_title_sub_03">
			<form id="deptForm" action="${contextPath}/department/deptobserving/list_detail_observing.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
			<input type="hidden" name="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="" />
			<input type="hidden" name="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="" />
			<input type="hidden" id="areadisplay" value="<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
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
				 机构职级：<select class="form_select" name="pmBscObserving.pmBscDeptInfo.pmBscDict.pid" id="select3">
			   	<option value="0">==请选择==</option>
			   	<#if pmbscdictlist?exists>
		        <#list pmbscdictlist as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==pmBscObserving.pmBscDeptInfo.pmBscDict.pid">selected</@s.if>>${dict.vcName!""}</option>
			     </#list>
			     </#if>
		      </select><br>&nbsp;&nbsp;
		      机构性质：<select class="form_select" name="pmBscObserving.pmBscDeptInfo.pmBscDeptType.pid" id="select1">
			   	<option value="0">==请选择==</option>
			   	<#if pmbscdepttypelist?exists>
		        <#list pmbscdepttypelist as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==pmBscObserving.pmBscDeptInfo.pmBscDeptType.pid">selected</@s.if>>${dict.vcTypeName!""}</option>
			     </#list>
			     </#if>
			     </select>
			    
		      机构立功表彰情况：<select class="form_select" name="pmBscObserving.pmBscObserType.pid" id="select2">
		         <option value="0">==请选择==</option>
		         <#if pmbscobsertypelist?exists>
		         <#list pmbscobsertypelist as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==pmBscObserving.pmBscObserType.pid">selected</@s.if>>${obserType.vcName!""}</option>
			     </#list>
			     </#if>
		      </select><br>&nbsp;&nbsp;
		      机构名称：<input tpye="text" id="pmBscObserving.pmBscDeptInfo.vcDeptName" name="pmBscObserving.pmBscDeptInfo.vcDeptName" value="<@s.property value="pmBscObserving.pmBscDeptInfo.vcDeptName" escape="false"/>" class="form_input" size="35"  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" />
		       &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" />
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
				<th>立功表彰情况</th>
				<th>立功表彰时间</th>
				<th>联系电话</th>
			</tr>
			<@s.if test="page.resultSet == null || page.resultSet.size()<1">
				<tr align="center">
					<td colspan="9">
						暂无符合数据...
					</td>
				</tr>
			</@s.if>
			<@s.if test="page.resultSet != null && page.resultSet.size()>0">
				<#list page.resultSet as pmbscobserving>
		            <tr>
		            	<td align="center">
		            		${pmbscobserving_index+1}
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscAreaInfo.vcAreaName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.vcDeptName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscDeptType.vcTypeName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscDict.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscObserType.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">${pmbscobserving.vcObservTime?string("yyyy-MM-dd")}
		            		&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.vcContactPhone!""}&nbsp;
		            	</td>
		            </tr>
		        </#list>
			</@s.if>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/department/deptobserving/list_detail_observing.action" escapeAmp="false">   
		    	<@s.param name="pmBscObserving.pmBscDeptInfo.vcDeptName" ><@s.property value="pmBscObserving.pmBscDeptInfo.vcDeptName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>
</html>
