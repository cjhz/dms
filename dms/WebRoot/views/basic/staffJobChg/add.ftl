<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			$(function(){
				setChkVal2('workDivision');
			});
			
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
			+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&staff.pid=<@s.property value="staff.pid" escape="false"/>';
        		
        	function query() {
        		document.location = '${contextPath}/basic/staffJobChg/pageQueryJobChg.action?' + param;
        	}
        	
			function formSubmit() {
				getChkVal2('workDivision');
				getChkHtm('workDivision','workDivisionName');
				if(compareif()==false){
					return;
				}else{
					var staffChangeForm = document.getElementById('staffChangeForm');
					if(!Validator.Validate(staffChangeForm, 3)) {
						return false;
					}
					staffChangeForm.submit();
				}
			}
			function compareif(){
				var bwork = intercept($.trim($("#nowJob").val()));
				var ework = intercept($.trim($("#workDivision").val()));
				if(bwork==ework){
					alert("工作分工如果调整，则不能相同！");
					return false;
				}
			}
			function intercept(arg){
				var len = arg.split("").length-1;
				var last = arg.lastIndexOf(";")
				if(last==len){
					return arg.substring(0,last);
				}else{
					return arg;
				}
				
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动>>工作分工调整>>个人工作分工调整</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staffJobChg/create.action'" />
		<form id="staffChangeForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
		<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
		<!--<input type="hidden" name="staffJob.pmBscStaffInfo.pid"  value="<@s.property value="staff.pid" escape="false"/>"  />-->
		<input type="hidden" name="staff.pid"  value="<@s.property value="staff.pid" escape="false"/>"  />
		<input type="hidden" name="staffJob.pid"  value="<@s.property value="chgid" escape="false"/>"  />
		<input type="hidden" name="staffsearch.vcOphone" id="departmentid" value="<@s.property value="staffsearch.vcOphone" />" />
		<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
		<div>
			<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			 <tr>
			     <th colspan="4" style="text-align:center;"><strong>禁毒人员工作分工调动</strong> </th>
			  </tr>
			  <tr>
			  	<th>现所在机构：</th>
			  	<td colspan="3">
			  		<#list staff.pmBscStaffDepts as staffDept>
			  			${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
			  		</#list>
			  	&nbsp;</td>
			  </tr>
			  <tr>
			    <th width="25%">姓名：</th>
			    <td width="25%"><@s.property value="staff.vcRealName" escape="false"/>&nbsp;</td>
			    <th width="25%">警号：</th>
			    <td width="25%"><@s.property value="staff.vcAlarm" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>身份证号码：</th>
			    <td><@s.property value="staff.vcCardNo.substring(0,staff.vcCardNo.length()-4)+'XXXX'" escape="false"/>&nbsp;</td>
			    <th>性别：</th>
			    <td>
			    	<@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">
			    		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/>
			    	</@s.if>
			    	<@s.elseif test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">
			    		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/>
			    	</@s.elseif>
			    	<@s.else>&nbsp;</@s.else>
				</td>
			  </tr>
			  <tr>
			    <th>职务：</th>
			    <td><@s.property value="staff.dictJobs.vcName" escape="false"/>&nbsp;</td>
			    <th>人员属性：</th>
			    <td>
				    <#list newdictprops as dict >
				    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
				    </#list>
			    </td>
			  </tr>
			  <tr>
			  <#if isedit>
			    <th>调动前工作分工：</th>
			    <td>
			    <input type="hidden" name="staffJob.vcNowJob" id="nowJob" value="<@s.property value="staffJob.vcNowJob" escape="false"/>" />
			    <input type="hidden" name="staffJob.vcExtend1" id="nowJob" value="<@s.property value="staffJob.vcExtend1" escape="false"/>" />
			    <@s.property value="staffJob.vcExtend1" escape="false"/>&nbsp;
			    </td>
			    <th>任前工作分工时间：</th>
			    <td>
			    <input type="hidden" name="staffJob.vcNowDate"  value="<@s.date name="staffJob.vcNowDate" format="yyyy-MM-dd"/>" />
			    <@s.date name="staffJob.vcNowDate" format="yyyy-MM-dd"/>&nbsp;
			    </td>
			    <#else>
				    <th>现工作分工：</th>
				    <td>
				    <input type="hidden" name="staffJob.vcNowJob" id="nowJob" value="<@s.property value="staff.vcWorkDivision" escape="false"/>" />
				    <input type="hidden" name="staffJob.vcExtend1" id="nowJob" value="<@s.property value="staff.vcExtend1" escape="false"/>" />
				    <@s.property value="staff.vcExtend1" escape="false"/>&nbsp;
				    </td>
				    <th>现任工作分工时间：</th>
				    <td>
				    <input type="hidden" name="staffJob.vcNowDate"  value="<@s.date name="staff.vcWorkDate" format="yyyy-MM-dd"/>" />
				    <@s.date name="staff.vcWorkDate" format="yyyy-MM-dd"/>&nbsp;
				    </td>
			    </#if>
			  
			  </tr>
			  <tr>
			  
			  	<th>调动后工作分工：</th>
			  	<#--<#if isedit>
				    <td>
				    	<@s.property value="staffJob.dictChgRanks.vcName" escape="false"/>&nbsp;
				     </td>
			  		<#else>-->
				    <td>
				    	<#list dictworks as dict>
							<input type="checkbox" name="workDivision_${dict_index+1}" id="workDivision_${dict_index+1}" value="${dict.pid}" propname="${dict.vcName}"/>${dict.vcName}
				        </#list>
						<input type="hidden" name="staffJob.vcChgJob" id="workDivision" value="<@s.property value="staffJob.vcChgJob" escape="false" />" />
						<input type="hidden" name="staffJob.vcExtend2" id="workDivisionName" value="<@s.property value="staffJob.vcExtend2" escape="false" />" />
				     </td>
			     <#--</#if>-->
			     
			    <th>调动时间：</th>
			    <td><input class="form_input" type="text" style="width:200px;margin-left:3px" name="staffJob.vcChgDate" id="vcChgDate" value="<@s.date name="staffJob.vcChgDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择调动时间" onclick="displayCalendar('',document.getElementById('vcChgDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
			  </tr>
			  <tr>
			    <th>备注：</th>
			    <td colspan="3">
					<textarea name="staffJob.vcRemark" id="vcRemark" cols="80" rows="5" require="false" trim="true" onKeyUp="testTextareaLength(this,200)" onBlur="testLeaveTextareaLength(this,200)" class="add_textarea2" width="75%"><@s.property value="staffJob.vcRemark" escape="false" /></textarea></td>
				</td>
			  </tr>
				<tr>
					<th>&nbsp;</th>
					<td colspan="5" >
							<font id="remLen1">&nbsp;</font>
							<font color=red id="remLen2">&nbsp;</font>
							<font id="remLen3">&nbsp;</font>
							<font color=red id="remLen4">&nbsp;</font>
							<font id="remLen5">&nbsp;</font>&nbsp;
					</td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>
				    <input type="button" onclick="formSubmit()" value="  保存  " />
				</td>
			    <td>
				    <input type="button" onclick="query()" value="  返回  " />
			    </td>
			    <td>&nbsp;</td>
			  </tr>
		</table>
		</div>
		</form>
	</body>
</html>
<#include "../../common/footer.ftl" />