<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript">
			var param = 'staffsearch.vcRealName=' + encodeURI('<@s.property value="staffsearch.vcRealName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&staffsearch.vcWorkDivision=<@s.property value="staffsearch.vcWorkDivision" escape="false" default=""/>'
			+'&staffsearch.dictRanks.pid=<@s.property value="staffsearch.dictRanks.pid" escape="false" default="0"/>'
			+'&staffsearch.vcCardNo=<@s.property value="staffsearch.vcCardNo" escape="false" default="0"/>'
			+'&staffsearch.dictCulturals.pid=<@s.property value="staffsearch.dictCulturals.pid" escape="false" default="0"/>'
			+'&staffsearch.vcSex=<@s.property value="staffsearch.vcSex" escape="false" default="0"/>'
			+'&staffsearch.bbirth=<@s.property value="staffsearch.bbirth" escape="false" default=""/>'
			+'&staffsearch.ebirth=<@s.property value="staffsearch.ebirth" escape="false" default=""/>'
			+'&staffsearch.vcOphone=<@s.property value="staffsearch.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay};
			
			function addJobChg(staffId) {
        		var url = '${contextPath}/basic/staffJobChg/add.action?' + param+ '&staff.pid=' + staffId;;
        		document.location = url;
        	}
        	
        	function show(chgid,staffId) {
        		var url = '${contextPath}/basic/staffJobChg/show.action?' + param + '&staff.pid=' + staffId+'&chgid='+chgid;
        		document.location = url;
        	}
        	
        	function modifyStaff(chgid,staffId) {
        		var url = '${contextPath}/basic/staffJobChg/edit.action?' + param + '&staff.pid=' + staffId+'&chgid='+chgid;
        		document.location = url;
        	}
        	function removeStaff(chgid,staffId) {
        		if(confirm('确认删除?')) {
        			var url = '${contextPath}/basic/staffJobChg/destory.action?' + param + '&staff.pid=' + staffId+'&chgid='+chgid;
        			document.location = url;
        		}
        	}
        	function doCancel() {
        		var url = '${contextPath}/basic/staffRankChg/pageQuery.action?' + param;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		//getChkVal('personProp','workDivision');
        		//$('#departmentid').val(getVcdepartmentId());
        		$("#pageNum").val(pagenum);
        		var workForm = document.getElementById('workForm');
				workForm.submit();
        	}
        	
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动>>工作分工个人列表</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
		 <form id="workForm" action="${contextPath}/basic/staffJobChg/pageQueryJobChg.action" method="post">
		 	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
		 	<input type="hidden" name="staffsearch.vcOphone" id="departmentid" value="<@s.property value="staffsearch.vcOphone" />" />
			<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
			<a href="javascript:addJobChg('${staff.pid}');" class="add" >新增</a>
			<a href="javascript:doCancel();" class="return" >返回</a>
		 </form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table" id="tab1">
			<tr>
				<th>序号</th>
				<th>所属机构</th>
				<th>姓名</th>
				<th>性别</th>
				<th>调动前工作分工</th>
				<th>调动后工作分工</th>
				<th>调动时间</th>
                <th>操作</th>
			</tr>
			<#if (page.resultSet?size>0)>
				<#list page.resultSet as jobchg >
					<tr>
		                <td align="center">${jobchg_index+1}</td>
		                <td align="center">
		                <#list jobchg.pmBscStaffInfo.pmBscStaffDepts  as staffDept >
		                	${staffDept.pmBscDeptInfo.vcDeptName!""}<#if staffDept_has_next>;</#if>
		                </#list>&nbsp;
		                </td>
		                <td align="center">${jobchg.pmBscStaffInfo.vcRealName!""}&nbsp;</td>
		                <td align="center">
		                <@s.if test="${jobchg.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></@s.if>
		                <@s.if test="${jobchg.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></@s.if>
		                &nbsp;</td>
		                <td align="center">${jobchg.vcExtend1!""}&nbsp;</td>
		                <td align="center">${jobchg.vcExtend2!""}&nbsp;</td>
		                <td align="center">${jobchg.vcChgDate?string('yyyy-MM-dd')}&nbsp;</td>
						<td align="center" width="25%">
							<a href="javascript:show(${jobchg.pid},'${staff.pid}')" class="see" >查看</a>
							<#if jobchg.vcIfedit==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_IFEIDT_YES")>
								<a href="javascript:modifyStaff(${jobchg.pid},'${staff.pid}')" class="edit" >修改</a>
								<#elseif jobchg.vcIfedit==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_IFEIDT_NO")>
								<a href="javascript:void(0)" class="edit" disabled='disabled' title='只能修改最后一次调动'>修改</a>
								<#else>&nbsp;
							</#if>
							<!--<a href="javascript:removeStaff(${jobchg.pid},'${staff.pid}')" class="delete" >删除</a>-->
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
			<@s.url var="pageUrl" value="/basic/staffJobChg/pageQueryJobChg.action" escapeAmp="false">   
				<@s.param name="staffsearch.pid" value="${staff.pid}"></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
