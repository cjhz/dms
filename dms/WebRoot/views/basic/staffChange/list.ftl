<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false"/>')
				+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&search.vcOphone=<@s.property value="search.vcOphone" />&pageNum=<@s.property value="pageNum" escape="false" />'
				+'&search.isShowAll='+'<@s.property value="search.isShowAll"  escape="false"/>';
        
        	$(document).ready(function() {
				if('${search.isShowAll}' == 1){
					$("#isShowAll").attr("checked","checked");
				}
			});
        	
        	function add(staffId) {
        		var url = '${contextPath}/basic/staffChange/add.action?' + param + '&staffId=' + staffId;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		var staffchgForm = document.getElementById('staffchgForm');
        		$('#departmentid').val(getVcdepartmentId());
        		$('#areadisplay').val(getVcdepartmentcode());
				staffchgForm.submit();
        	}
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员调动管理>>人员调动</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<form id="staffchgForm" action="${contextPath}/basic/staffChange/pageQuery.action" method="post">
				<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
				<input type="hidden" id ="pageNum" name="pageNum" value=""/>
				<input type="hidden" name="search.vcOphone" id="departmentid" value="<@s.property value="search.vcOphone" />" />
				<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
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
				<input type="checkbox" id="isShowAll" name="search.isShowAll" <@s.if test="search.isShowAll == 1">selected</@s.if> value="1"/>显示下属机关人员
				姓名：<input type="text" name="search.vcRealName" value="<@s.property value="search.vcRealName" escape="false"/>" class="form_input" size="12"  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" /> 
				<input type="button" onclick="formSubmit('1');" size="12" value="　查询　" /> 
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
				<th>现所属机构</th>
				<th>姓名</th>
				<th>职务</th>
				<th>性别</th>
				<th>人员属性</th>
                <th>联系电话</th>
                <th>操作</th>
			</tr>
			<#if page.resultSet?exists&&(page.resultSet?size>0)>
			<#list page.resultSet as staff >
				<tr>
	                <td align="center">${staff_index+1}</td>
	                <td align="center">
	                <#list staff.pmBscStaffDepts  as staffDept >
	                	${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
	                </#list>&nbsp;
	                </td>
	                <td align="center">${staff.vcRealName!""}&nbsp;</td>
	                <td align="center">${staff.dictJobs.vcName}&nbsp;</td>
	                <td align="center">
	                <!--${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1")}-->
	                <@s.if test="${staff.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></@s.if>
	                <@s.if test="${staff.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></@s.if>
	                &nbsp;</td>
	                <td align="center">${staff.vcExtend1}&nbsp;</td>
	                <td align="center">
	                	<#if staff.vcOphone?exists>
	            			${staff.vcOphone!""}
	            			<#else>
	            			${staff.vcMphone!""}
	                	</#if>
	            		&nbsp;
	                </td>
					<td align="center" width="25%">
						<a href="javascript:add('${staff.pid}');" class="see" >人员调动</a>
					</td>
				</tr>
			</#list>
			<#else>
				<tr align="center">
					<td colspan="8">
						暂无符合数据...
					</td>
				</tr>
			</#if>
		</table>
		<div class="iner_title">
			<@s.url var="pageUrl" value="/basic/staffChange/pageQuery.action" escapeAmp="false">   
		    	<@s.param name="search.vcRealName" ><@s.property value="search.vcRealName" escape="false"/></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
