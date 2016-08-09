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
			+'&staffsearch.isShowAll=<@s.property value="staffsearch.isShowAll" escape="false" default="0"/>'
			+'&staffsearch.bbirth=<@s.property value="staffsearch.bbirth" escape="false" default=""/>'
			+'&staffsearch.ebirth=<@s.property value="staffsearch.ebirth" escape="false" default=""/>'
			+'&staffsearch.vcOphone=<@s.property value="staffsearch.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay};
			
			function addRankChg(staffId,type) {
        		var url = '${contextPath}/basic/staffRankChg/add.action?' + param+'&search.vcType='+type+ '&staff.pid=' + staffId;;
        		document.location = url;
        	}
        	
        	function show(chgid,staffId,type) {
        		var url = '${contextPath}/basic/staffRankChg/show.action?' + param +'&search.vcType='+type+ '&staff.pid=' + staffId+'&chgid='+chgid;
        		document.location = url;
        	}
        	
        	function modifyStaff(chgid,staffId,type) {
        		var url = '${contextPath}/basic/staffRankChg/edit.action?' + param +'&search.vcType='+type+ '&staff.pid=' + staffId+'&chgid='+chgid;
        		document.location = url;
        	}
        	function removeStaff(chgid,staffId,type) {
        		if(confirm('确认删除?')) {
        			var url = '${contextPath}/basic/staffRankChg/destory.action?' + param +'&search.vcType='+type+ '&staff.pid=' + staffId+'&chgid='+chgid;
        			document.location = url;
        		}
        	}
        	function doCancel() {
        		var url = '${contextPath}/basic/staffRankChg/pageQuery.action?' + param;
        		document.location = url;
        	}
        	function formSubmit(pagenum) {
        		$("#pageNum").val(pagenum);
        		//getChkVal('personProp','workDivision');
        		//$('#departmentid').val(getVcdepartmentId());
        		var rankForm = document.getElementById('rankForm');
				rankForm.submit();
        	}
        	
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动>>${typeName}变动个人列表</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
		<form id="rankForm" action="${contextPath}/basic/staffRankChg/pageQueryRankChg.action" method="post">
		<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" id ="pageNum" name="pageNum" value=""/>
			<input type="hidden" name="staffsearch.vcOphone" id="departmentid" value="<@s.property value="staffsearch.vcOphone" />" />
			<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
			<a href="javascript:addRankChg('${staff.pid}',${search.vcType});" class="add" >新增</a>
			<a href="javascript:doCancel();" class="return" >返回</a>
		</form>
		</div>
		<table cellpadding="0" cellspacing="0" class="main_table" id="tab1">
			<tr>
				<th>序号</th>
				<th>所属机构</th>
				<th>姓名</th>
				<th>性别</th>
				<th>调动前${typeName}</th>
				<th>调动后${typeName}</th>
				<th>调动时间</th>
                <th>操作</th>
			</tr>
			<#if (page.resultSet?size>0)>
				<#list page.resultSet as rankchg >
					<tr>
		                <td align="center">${rankchg_index+1}</td>
		                <td align="center">
		                <#list rankchg.pmBscStaffInfo.pmBscStaffDepts  as staffDept >
		                	${staffDept.pmBscDeptInfo.vcDeptName!""}<#if staffDept_has_next>;</#if>
		                </#list>&nbsp;
		                </td>
		                <td align="center">${rankchg.pmBscStaffInfo.vcRealName!""}&nbsp;</td>
		                <td align="center">
		                <@s.if test="${rankchg.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></@s.if>
		                <@s.if test="${rankchg.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2"><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></@s.if>
		                &nbsp;</td>
		                <td align="center">${rankchg.dictNowRanks.vcName!""}&nbsp;</td>
		                <td align="center">${rankchg.dictChgRanks.vcName!""}&nbsp;</td>
		                <td align="center">${rankchg.vcChgDate?string('yyyy-MM-dd')}&nbsp;</td>
						<td align="center" width="25%">
							<a href="javascript:show(${rankchg.pid},'${staff.pid}',${search.vcType})" class="see" >查看</a>
							<#if rankchg.vcIfedit==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_IFEIDT_YES")>
								<a href="javascript:modifyStaff(${rankchg.pid},'${staff.pid}',${search.vcType})" class="edit" >修改</a>
								<#elseif rankchg.vcIfedit==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_IFEIDT_NO")>
								<a href="javascript:void(0)" class="edit" disabled='disabled' title='只能修改最后一次调动'>修改</a>
								<#else>&nbsp;
							</#if>
							<!--<a href="javascript:removeStaff(${rankchg.pid},'${staff.pid}')" class="delete" >删除</a>-->
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
			<@s.url var="pageUrl" value="/basic/staffRankChg/pageQueryRankChg.action" escapeAmp="false">   
				<@s.param name="staffs.pid" value="${staff.pid}"></@s.param>
				<@s.param name="search.pid" value="${search.vcType}"></@s.param>
				<@s.param name="pageNum"></@s.param>
			</@s.url>
            <#include "../../common/pagination.ftl" />
		</div>
	</body>

</html>
