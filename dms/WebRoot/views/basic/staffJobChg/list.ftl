<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript">
			var param = '';
        	function showBystaff(staffId) {
        		var url = '${contextPath}/basic/staffJobChg/pageQueryJobChg.action?' + param + '&staffsearch.pid=' + staffId;
        		document.location = url;
        	}
        	function exp() {
        		var url = '${contextPath}/basic/staffJobChg/export.action?' + param;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		getChkVal('personProp','workDivision');
        		$('#departmentid').val(getVcdepartmentId());
        		var goldForm = document.getElementById('staffForm');
				goldForm.submit();
        	}
        	
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员职务变动</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<#include "../../common/export/expform.ftl" />
		<div class="iner_title_sub_03">
			<form id="staffForm" action="${contextPath}/basic/staffJobChg/pageQuery.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
				<!-- 这里用来保存地区id -->
				<input type="hidden" name="search.vcOphone" id="departmentid" value="<@s.property value="search.vcOphone" />" />
				<input type="hidden" name="search.vcWorkDivision" id="workDivision" value="<@s.property value="search.vcWorkDivision" escape="false" />" />
				地区：<select class="serch" id="province" onchange="cascade(1);">
					<option value="" selected="selected">
						省
					</option>
				</select>
				<select class="serch" id="city" onchange="cascade(2);">
					<option value="" selected="selected">
						市
					</option>
				</select>
				<select class="serch" id="county">
					<option value="" selected="selected">
						区/县
					</option>
				</select>
				人员职级：<select class="serch" name="search.dictRanks.pid" id="select5">
					<option value="0">==请选择==</option>
					<#if dictRanks?exists>
					<#list dictRanks as dict>
						<option value=${dict.pid} <@s.if test="${dict.pid}==search.dictRanks.pid">selected</@s.if>>${dict.vcName}</option>
					</#list>
					</#if>
				</select><br>&nbsp;&nbsp;
				工作分工：
				<#if dictworks?exists>
				<#list dictworks as dict>
					<input type="checkbox" name="workDivision_${dict_index+1}" id="workDivision_${dict_index+1}" value="${dict.pid}"/>${dict.vcName}
		        </#list>
		        </#if><br>&nbsp;&nbsp;
		          人员立功表彰情况：<select class="serch" name="search.vcCardNo" id="select2">
		         <option value="0">==请选择==</option>
		         <#if pmBscObserTypeList?exists>
		         <#list pmBscObserTypeList as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==search.vcCardNo">selected</@s.if>>${obserType.vcName}</option>
			     </#list>
			     </#if>
		      </select>
		      文化程度：<select class="serch" name="search.dictCulturals.pid" id="select3">
		      <option value="0">==请选择==</option>
		      <#if dictCulturals?exists>
		        <#list dictCulturals as dict>
		        	<option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictCulturals.pid">selected</@s.if>>${dict.vcName}</option>
		        </#list>
		       </#if>
	      	</select>
	      	性别：<select class="serch" name="search.vcSex" id="staff.vcSex">
	      	<option value="0">==请选择==</option>
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1" /> <@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></option>
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2" /> <@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></option>
	      	</select><br>&nbsp;&nbsp;
	      	年龄情况：<input type="text" class="form_input" size="12" name="search.vcJob" value="<@s.property value="search.vcJob" />"/>—<input class="form_input" size="12" type="text" name="search.vcAlarm" value="<@s.property value="search.vcAlarm" />"/>
				姓名：<input type="text" name="search.vcRealName" value="<@s.property value="search.vcRealName" escape="false"/>" class="form_input" size="12"/> 
				<input type="button" onclick="formSubmit('1');" size="12" value=" 查询 " /> 
				<input type="button" size="12" value=" 导出 " onclick="exp()" />
			</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table" id="tab1">
			<tr>
				<th>序号</th>
				<th>所属机构</th>
				<th>姓名</th>
				<th>身份证号码</th>
				<th>性别</th>
				<th>职务</th>
                <th>联系电话</th>
                <th>操作</th>
			</tr>
			<#list page.resultSet as staff >
				<tr>
	                <td align="center">${staff_index+1}</td>
	                <td align="center">
	                <#list staff.pmBscStaffDepts  as staffDept >
	                	${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
	                </#list>&nbsp;
	                </td>
	                <td align="center">${staff.vcRealName!""}&nbsp;</td>
	                <td align="center">${staff.vcCardNo!""}&nbsp;</td>
	                <td align="center">
	                <!--${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1")}-->
	                <@s.if test="${staff.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></@s.if>
	                <@s.if test="${staff.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></@s.if>
	                &nbsp;</td>
	                <td align="center">${staff.vcJob!""}&nbsp;</td>
	                <td align="center">
	                	<#if staff.vcOphone?exists>
	            			${staff.vcOphone!""}
	            			<#else>
	            			${staff.vcMphone!""}
	                	</#if>
	            		&nbsp;
	                </td>
					<td align="center" width="25%">
						<a href="javascript:showBystaff(${staff.pid})" class="edit" >人员职务变动</a>
					</td>
				</tr>
			</#list>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/basic/staffJobChg/pageQuery.action" escapeAmp="false">   
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
