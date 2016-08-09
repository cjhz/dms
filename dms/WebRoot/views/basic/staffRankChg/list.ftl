<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'staffsearch.vcRealName=' + encodeURI('<@s.property value="staffsearch.vcRealName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&staffsearch.vcWorkDivision=<@s.property value="staffsearch.vcWorkDivision" escape="false" default=""/>'
			+'&staffsearch.dictRanks.pid=<@s.property value="staffsearch.dictRanks.pid" escape="false" default="0"/>'
			+'&staffsearch.vcCardNo=<@s.property value="staffsearch.vcCardNo" escape="false" default="0"/>'
			+'&staffsearch.dictCulturals.pid=<@s.property value="staffsearch.dictCulturals.pid" escape="false" default="0"/>'
			+'&staffsearch.vcSex=<@s.property value="staffsearch.vcSex" escape="false" default="0"/>'
			+'&staffsearch.isShowAll=<@s.property value="staffsearch.isShowAll" escape="false" default="0"/>'
			+'&staffsearch.bbirth=<@s.property value="staffsearch.bbirth" escape="false" default=""/>'
			+'&staffsearch.ebirth=<@s.property value="staffsearch.ebirth" escape="false" default=""/>'
			+'&staffsearch.vcOphone=<@s.property value="staffsearch.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay};
			
			$(document).ready(function() {
				setChkVal2('workDivision');
					if('${staffsearch.isShowAll}' == 1){
					$("#isShowAll").attr("checked","checked");
				}
			});
        	function showBystaff(staffId,type,method) {
        		var url='';
        		if(method==''){
	        		url = '${contextPath}/basic/staffRankChg/pageQueryRankChg.action?' + param +'&search.vcType='+type+ '&staff.pid=' + staffId;
        		}else if(method=='work'){
        			url = '${contextPath}/basic/staffJobChg/pageQueryJobChg.action?' + param + '&staff.pid=' + staffId;
        		}
        		if(url!=''){
	        		document.location = url;
        		}
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		getChkVal('personProp','workDivision');
        		$('#departmentid').val(getVcdepartmentId());
        		$("#areadisplay").val(getVcdepartmentcode());
        		var goldForm = document.getElementById('staffForm');
				goldForm.submit();
        	}
        	
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<form id="staffForm" action="${contextPath}/basic/staffRankChg/pageQuery.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
				<!-- 这里用来保存地区id -->
				<input type="hidden" name="staffsearch.vcOphone" id="departmentid" value="<@s.property value="staffsearch.vcOphone" />" />
			<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
			<input type="hidden" id="areacontrol" value="${user.areaCode}" />
				<input type="hidden" name="staffsearch.vcWorkDivision" id="workDivision" value="<@s.property value="staffsearch.vcWorkDivision" escape="false" />" />
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
				<input type="checkbox" id="isShowAll" name="staffsearch.isShowAll" <@s.if test="staffsearch.isShowAll == 1">selected</@s.if> value="1"/>显示下属机关人员
				人员职级：<select class="serch" name="staffsearch.dictRanks.pid" id="select5">
					<option value="0">==请选择==</option>
					<#if dictRanks?exists>
						<#list dictRanks as dict>
							<option value=${dict.pid} <@s.if test="${dict.pid}==staffsearch.dictRanks.pid">selected</@s.if>>${dict.vcName}</option>
						</#list>
					</#if>
				</select><br>&nbsp;&nbsp;
				工作分工：
				<#if dictworks?exists>
				<#list dictworks as dict>
					<input type="checkbox" name="workDivision_${dict_index+1}" id="workDivision_${dict_index+1}" value="${dict.pid}"/>${dict.vcName}
		        </#list>
		        </#if><br>&nbsp;&nbsp;
		          人员立功表彰情况：<select class="serch" name="staffsearch.vcCardNo" id="select2">
		         <option value="0">==请选择==</option>
		         <#if pmBscObserTypeList?exists>
		         <#list pmBscObserTypeList as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==staffsearch.vcCardNo">selected</@s.if>>${obserType.vcName}</option>
			     </#list>
			     </#if>
		      </select>
		      文化程度：<select class="serch" name="staffsearch.dictCulturals.pid" id="select3">
		      <option value="0">==请选择==</option>
		      <#if dictCulturals?exists>
		        <#list dictCulturals as dict>
		        	<option value=${dict.pid} <@s.if test="${dict.pid}==staffsearch.dictCulturals.pid">selected</@s.if>>${dict.vcName}</option>
		        </#list>
		       </#if>
	      	</select>
	      	性别：<select class="serch" name="staffsearch.vcSex" id="staff.vcSex">
	      	<option value="0">==请选择==</option>
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1" /> <@s.if test="staffsearch.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></option>
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2" /> <@s.if test="staffsearch.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></option>
	      	</select><br>&nbsp;&nbsp;
	      	年龄情况：<input type="text" class="form_input" size="12" name="staffsearch.bbirth" value="<@s.property value="staffsearch.bbirth" />" onKeyUp="checkAgeNumber(this)"/>—<input class="form_input" size="12" type="text" name="staffsearch.ebirth" value="<@s.property value="staffsearch.ebirth" />" onKeyUp="checkAgeNumber(this)"/>
				姓名：<input type="text" name="staffsearch.vcRealName" value="<@s.property value="staffsearch.vcRealName" escape="false"/>" class="form_input" size="12" onClick="checkMaxInput(this,50)" onKeyDown="checkMaxInput(this,50)" onKeyUp="checkMaxInput(this,50)" onBlur="clsInput(this)" /> 
				<input type="button" onclick="formSubmit('1');" size="12" value=" 查询 " /> 
				<font id="remLen1">&nbsp;</font>
				<font color=red id="remLen2">&nbsp;</font>
				<font id="remLen3">&nbsp;</font>
				<font color=red id="remLen4">&nbsp;</font>
				<font id="remLen5">&nbsp;</font>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table" id="tab1">
			<tr>
				<th>序号</th>
				<th>所属机构</th>
				<th>姓名</th>
				<th>职务</th>
				<th>性别</th>
				<th>职级</th>
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
	                <td align="center">${staff.dictRanks.vcName}&nbsp;</td>
	                <td align="center">
	                	<#if staff.vcOphone?exists>
	            			${staff.vcOphone!""}
	            			<#else>
	            			${staff.vcMphone!""}
	                	</#if>
	            		&nbsp;
	                </td>
					<td align="center" width="35%">
						<a href="javascript:showBystaff('${staff.pid}',${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_1")},'')" class="edit" >职级变动</a>
						<a href="javascript:showBystaff('${staff.pid}',${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_2")},'')" class="edit" >职务变动</a>
						<a href="javascript:showBystaff('${staff.pid}',${stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_3")},'')" class="edit" >编制变动</a>
						<a href="javascript:showBystaff('${staff.pid}','','work')" class="edit" >分工调整</a>
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
			<@s.url var="pageUrl" value="/basic/staffRankChg/pageQuery.action" escapeAmp="false">   
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
