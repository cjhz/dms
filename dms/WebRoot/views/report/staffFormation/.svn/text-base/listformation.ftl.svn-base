<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript">
			function formSubmit() {
				setcode();
				var staffDtoForm = document.getElementById('staffDtoForm');
				if(compareAge()==false){
					//alert("年龄段选择错误！");
					return;
				}else{
					staffDtoForm.submit();
				}
			}
			function setcode(){
				//$('#departmentcode').val(getVcdepartmentcode());
				$('#departmentid').val(getVcdepartmentId());
				$('#areadisplay').val(getVcdepartmentcode());
			}
			
			function compareAge(){
				if(parseInt($("#age1").val(),10)!=0&&parseInt($("#age2").val(),10)!=0){
					if(parseInt($("#age1").val(),10)>parseInt($("#age2").val(),10)){
						alert("年龄段选择错误！");
						return false;
					}else{return true;}
				}
			}
		</script>
	</head>

	<body>
		<div class="iner_title_name"><strong>统计分析>>禁毒人员统计>>按人员编制统计</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<#include "../../common/export/expform.ftl" />
		<div class="iner_title_sub_04">
			<form id="staffDtoForm" action="${contextPath}/report/staffFormation/searchbyformation.action" method="post">
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<input type="hidden" name="staffDto.areaid" id="departmentid" value="" />
			<!--<input type="hidden" name="staffDto.areacode" id="departmentcode" value="" />-->
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
			   职级：<select class="form_select" name="staffDto.rank" id="select1">
			   	<option value="0">==请选择==</option>
			   	<#if dictRanks?exists>
		        <#list dictRanks as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==staffDto.rank">selected</@s.if>>${dict.vcName!""}</option>
			     </#list>
			     </#if>
		      </select>
		      立功表彰情况：<select class="form_select" name="staffDto.observType" id="select2">
		         <option value="0">==请选择==</option>
		         <#if obserTypes?exists>
		         <#list obserTypes as obserType>
			        <option value=${obserType.pid} <@s.if test="${obserType.pid}==staffDto.observType">selected</@s.if>>${obserType.vcName!""}</option>
			     </#list>
			     </#if>
		      </select>
			  &nbsp;&nbsp;&nbsp;文化程度：<select class="form_select" name="staffDto.cultural" id="select3">
		         <option value="0">==请选择==</option>
		         <#if dictCulturals?exists>
		         <#list dictCulturals as dict>
			        <option value=${dict.pid} <@s.if test="${dict.pid}==staffDto.cultural">selected</@s.if>>${dict.vcName!""}</option>
			     </#list>
			     </#if>
		      </select>
		      	  性别：
		      	  <select class="form_select" name="staffDto.sex" id="select4">
		      	  	<option value=0>==请选择==</option>
		      	  	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1" /> <@s.if test="staffDto.sex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></option>
	        		<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2" /> <@s.if test="staffDto.sex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></option>
		      	  </select>&nbsp;&nbsp;&nbsp;&nbsp;
	      	      年龄情况：<select class="form_select" name="staffDto.startnum" id="age1" onChange="compareAge();">
	      	      	  <option value=0>==请选择==</option>
		      	      <#list staffDto.startnumlist as startnum>
		      	      	<option value="${startnum}" <@s.if test="${startnum}==staffDto.startnum">selected</@s.if>>${startnum}</option>
		      	      </#list>
	      	      </select>&nbsp;&nbsp;--&nbsp;&nbsp;
	      	      <select class="form_select" name="staffDto.endnum" id="age2" onChange="compareAge();">
	      	      	  <option value=0>==请选择==</option>
		      	      <#list staffDto.endnumlist as endnum>
		      	      	<option value="${endnum}" <@s.if test="${endnum}==staffDto.endnum">selected</@s.if>>${endnum}</option>
		      	      </#list>
	      	      </select>&nbsp;&nbsp;&nbsp;&nbsp;
			      <input type="button" onclick="formSubmit();" size="12" value="　查询　" /> 
			      <input type="button" size="12" value="　导出　" onclick="downXLS('tab1','按人员编制统计')" />
		      </div>
			</form>
		</div>
		<table align="center" cellpadding="0" cellspacing="0" class="report_table" style="margin-bottom:20px;" id="tab1">
			<#if reportArray?exists>
				<#list reportArray as array>
					<tr>
					<#list array as arr>
						<#if array_index+1==1||arr_index+1==1>
							<th width='${100/array?size}%'>${arr}</th>
							<#else><td>${arr}</td>
						</#if>
					</#list>
					</tr>
				</#list>
			</#if>
		</table>
		
	</body>

</html>
