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
		<div class="iner_title_name"><strong>统计分析>>禁毒人员统计>>按人员基本信息统计</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<#include "../../common/export/expform.ftl" />
		<div class="iner_title_sub_04">
			<form id="staffDtoForm" action="${contextPath}/report/staffFormation/searchbystaff.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" name="staffDto.areaid" id="departmentid" value="" />
			<input type="hidden" id="areadisplay" name="staffDto.areacode" value="${staffDto.areacode}"/>
			<input type="hidden" id="areacontrol" value="${user.areaCode}" />
		<div>

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
			   编制：<select class="form_select" name="staffDto.formation" id="select1">
			   	<option value="0">==请选择==</option>
			   	<#if dictFormations?exists>
		        <#list dictFormations as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==staffDto.formation">selected</@s.if>>${dict.vcName}</option>
			     </#list>
			     </#if>
		      </select>
		      职级：<select class="form_select" name="staffDto.rank" id="select1">
			   	<option value="0">==请选择==</option>
			   	<#if dictRanks?exists>
		        <#list dictRanks as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==staffDto.rank">selected</@s.if>>${dict.vcName}</option>
			     </#list>
			     </#if>
		      </select><br>&nbsp;&nbsp;
			  立功表彰情况：<select class="form_select" name="staffDto.observType" id="select2">
		         <option value="0">==请选择==</option>
		         <#if obserTypes?exists>
		         <#list obserTypes as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==staffDto.observType">selected</@s.if>>${obserType.vcName}</option>
			     </#list>
			     </#if>
		      </select>&nbsp;&nbsp;&nbsp;&nbsp;
			      <input type="button" onclick="formSubmit();" size="12" value="　查询　" /> 
			      <input type="button" size="12" value="　导出　" onclick="downXLS('tab1','按人员基本情况统计')" />
		      </div>
			</form>
		</div>
		<table align="center" cellpadding="0" cellspacing="0" class="report_table" id="tab1">
		  <#if staffmap?exists>
		  <#list staffmap?keys as key>  
		  	   <#assign reportArr=staffmap[key]>
		  	   <#if key_index+1==1>
		  	   	  <tr>
			  	   <th rowspan="${reportArr[0]}">地区</th>
				   <th colspan="${reportArr[1]}">性别</th>
				   <th colspan="${reportArr[2]}">文化程度</th>
				   <th colspan="${reportArr[3]}">年龄段</th>
				  </tr>
			  	   <#elseif key_index+1&gt;1>
			  	   <#list reportArr as array>
			  	   	  <tr>
						  <#list array as arr>
						  	<#if array_index+1==1&&arr_index+1&gt;1>
						  		<th>${arr}</th>
							<#elseif array_index+1&gt;1&&arr_index+1==1>
								<th width='${100/array?size}%'>${arr}</th>
							<#elseif array_index+1&gt;1&&arr_index+1&gt;1>
								<td width='${100/array?size}%'>${arr}</td>
							<#else>
							</#if>
							
						  </#list>
					  </tr>
			  	   </#list>
		  	   </#if>
		  </#list>
		  </#if>
			
		</table>
		
	</body>

</html>
