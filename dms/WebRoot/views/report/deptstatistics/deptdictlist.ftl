<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript">
			function formSubmit() {
				setcode();
				var staffDtoForm = document.getElementById('staffDtoForm');
				staffDtoForm.submit();
			}
			function setcode(){
				$('#departmentid').val(getVcdepartmentId());
				$('#areadisplay').val(getVcdepartmentcode());
			}
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>统计分析>>禁毒机构统计>>按机构职级统计</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<#include "../../common/export/expform.ftl" />
		<div class="iner_title_sub_03">
			<form id="staffDtoForm" action="${contextPath}/report/deptstatistics/searchdeptdict.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" name="deptdto.areaid" id="departmentid" value="" />
			<input type="hidden" name="deptdto.areacode" id="departmentcode" value="" />
			<input type="hidden" id="areadisplay" name="deptdto.areacode" value="${deptdto.areacode}"/>
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
				</select><br>
		     &nbsp;&nbsp; 
			 机构性质：<select class="form_select" name="deptdto.deptype" id="select1">
			   	<option value="0">==请选择==</option>
		        <#list deptypes as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==deptdto.deptype">selected</@s.if>>${dict.vcTypeName!""}</option>
			     </#list>
		      </select>机构立功表彰情况：<select class="form_select" name="deptdto.deptobserving" id="select2">
		         <option value="0">==请选择==</option>
		         <#list obserTypes as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==deptdto.deptobserving">selected</@s.if>>${obserType.vcName!""}</option>
			     </#list>
		      </select>
		       &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="formSubmit();" size="12" value="　查询　" /> 
			      <input type="button" size="12" value="　导出　" onclick="downXLS('tab1','按机构职级统计')" />
			</form>
		</div>
		<table align="center" cellpadding="0" cellspacing="0" class="report_table" id="tab1">
			<@s.if test="deptstatistics == null || deptstatistics.size()<1">
				<tr align="center">
					<td align="center">
						暂无符合数据....
					</td>
				</tr>
			</@s.if>
			<@s.if test="deptstatistics != null && deptstatistics.size()>0">
				<#list deptstatistics as depts>
					<tr align="center">
						<#list depts as dept>
							<#if depts_index+1==1||dept_index+1==1>
							<th width='${100/depts?size}%'>${dept}</th>
							<#else><td>${dept}</td>
							</#if>
						</#list>
					</tr>
				</#list>
			</@s.if>
		</table>
	</body>
</html>