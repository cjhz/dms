<form id="staffForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="staff.pid" id="staffId" value="<@s.property value="staff.pid" escape="false"/>" />
		<input type="hidden" name="search.vcRealName" value="<@s.property value="search.vcRealName" escape="false"/>" />
		<input type="hidden" name="pageNum" value="<@s.property value="pageNum" escape="false"/>" />
		<input type="hidden" name="search.vcOphone" value="<@s.property value="search.vcOphone" escape="false"/>" />
		<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
		<input type="hidden" name="staff.vcPersonProp" id="personProp" value="<@s.property value="staff.vcPersonProp" escape="false" />"  />
		<input type="hidden" name="staff.vcWorkDivision" id="workDivision" value="<@s.property value="staff.vcWorkDivision" escape="false" />" />
		<!--使用拓展字段1 存储工作分工名称-->
		<input type="hidden" name="staff.vcExtend1" id="workDivisionName" value="<@s.property value="staff.vcExtend1" escape="false" />" />
		
		<@s.set name="isAdmin" value="staff != null && staff.pid != null && staff.pid < @com.sunraysoft.hr.constant.Constant@SEQUENCE_START_WITH" />
		
		<tr>
	      <th colspan="4" width="80%" style="text-align:center;"><strong><#if ifedit?exists==true>修改<#else>新增</#if>禁毒人员信息</strong></th>
	      <th width="20%">&nbsp;</th>
	    </tr>
	    <tr>
	      <th width="20%">人员属性：</th>
	      <td colspan="3">&nbsp;
	      	<#list dictprops as dict>
				<input type="checkbox" name="personProp_${dict_index+1}" id="personProp_${dict_index+1}" value="${dict.pid}"/>${dict.vcName}
	        </#list>
		  </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>所属机构：</th>
	      <@s.if test = "#editDept==true">
		      <td colspan="3">
			      <input  type="hidden" name="staffdepts" id="deptIds" value=""   require="true" dataType="Require"  trim="true" msg="请选择所在机构"/>
				  <input class="add_input2 form_input" type="text" name="deptNames" id="deptNames" value="" readonly="true" />
				  &nbsp;&nbsp;&nbsp;&nbsp;
		      	  <input type="button" name="selBtn" id="selBtn" value="  选择  " />
			      <input type="button" name="canBtn" id="canBtn" value="  清除  " />
			  </td>
		      <td>&nbsp;</td>
	      </@s.if>
	      <@s.else>
		      <td colspan="3">
			      <#list deptList as dept>
			      	${dept.pmBscDeptInfo.vcDeptName}<#if dept_has_next>;</#if>
			      </#list>&nbsp;
			  </td>
		      <td>&nbsp;</td>
	      </@s.else>
	    </tr>
	    <tr>
	      <th width="20%">姓名：</th>
	      <td width="20%">
	      <input class="add_input form_input" type="text" name="staff.vcRealName" id="vcRealName" value="<@s.property value="staff.vcRealName" />"  require="true" dataType="Require"  trim="true" msg="姓名不能为空！"  onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this);" /></td>
	      <th width="20%">性别：</th>
	      <td width="20%">
	        <select class="add_input form_select" name="staff.vcSex" id="staff.vcSex">
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1" /> <@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></option>
	        	<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2" /> <@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">selected</@s.if>><@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></option>
	      	</select>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>出生日期：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcBirth" id="vcBirth" value="<@s.date name="staff.vcBirth" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择出生日期" onclick="displayCalendar('',document.getElementById('vcBirth'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      <th>政治面貌：</th>
	      <td>
	        <select class="add_input form_select" name="staff.dictPoliticals.pid" id="select2">
	        <#list dictPoliticals as dict>
	        	<option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictPoliticals.pid">selected</@s.if>>${dict.vcName}</option>
	        </#list>
	      </select></td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>文化程度：</th>
	      <td>
	        <select class="add_input form_select" name="staff.dictCulturals.pid" id="select3">
		        <#list dictCulturals as dict>
		        	<option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictCulturals.pid">selected</@s.if>>${dict.vcName}</option>
		        </#list>
	      	</select>
	      	</td>
	      <th>手机号码：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcMphone" id="vcMphone" value="<@s.property value="staff.vcMphone" escape="false" />"  onClick="checkMaxInput(this,20)" onKeyDown="checkMaxInput(this,20)" onKeyUp="checkMaxInput(this,20)" onBlur="clsInput(this)" /></td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>办公外线：</th>
	      <td>
	      <input class="form_input" style="width:40px;margin-left:5px;" type="text" name="staff.phone_area" id="phone_area" value="<@s.property value="staff.phone_area" escape="false" />"  require="true" dataType="Require"  trim="true" msg="区号不能为空！"   onClick="checkMaxInput(this,4)" onKeyDown="checkMaxInput(this,4)" onKeyUp="checkMaxInput(this,4)" onBlur="clsInput(this)" />-
	      <input class="form_input" type="text" name="staff.phone_code" id="phone_code" value="<@s.property value="staff.phone_code" escape="false" />"  require="true" dataType="Require"  trim="true" msg="电话号码不能为空！" onClick="checkMaxInput(this,10)" onKeyDown="checkMaxInput(this,10)" onKeyUp="checkMaxInput(this,10)" onBlur="clsInput(this)" />
	      </td>
	      <th>传真：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcFax" id="vcFax" value="<@s.property value="staff.vcFax" escape="false"/>"  onClick="checkMaxInput(this,20)" onKeyDown="checkMaxInput(this,20)" onKeyUp="checkMaxInput(this,20)" onBlur="clsInput(this)" /></td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>办公专线：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcOfficeOnline" id="vcOfficeOnline" value="<@s.property value="staff.vcOfficeOnline" escape="false" />" onClick="checkMaxInput(this,20)" onKeyDown="checkMaxInput(this,20)" onKeyUp="checkMaxInput(this,20)" onBlur="clsInput(this)" /></td>
	      <th>虚拟号：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcVirtualNum" id="vcVirtualNum" value="<@s.property value="staff.vcVirtualNum" escape="false"/>" onClick="checkMaxInput(this,20)" onKeyDown="checkMaxInput(this,20)" onKeyUp="checkMaxInput(this,20)" onBlur="clsInput(this)" /></td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>身份证号：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcCardNo" id="vcCardNo" value="<@s.property value="staff.vcCardNo" escape="false"/>"   require="true" dataType="Require"  trim="true" msg="身份证号码不能为空！" onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" /></td>
	      <th>警号：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcAlarm" id="vcAlarm" value="<@s.property value="staff.vcAlarm" escape="false"/>"  onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" /></td>
	      <td>&nbsp;</td>
	    </tr>
	    <#if ifedit?exists==true>
	    	<tr>
	      <th>编制：</th>
	      <td>
	        <@s.property value="staff.dictFormations.vcName" escape="false"/>&nbsp;
	      </td>
	      <th>任现编制时间：</th>
	      <td>
	      	<@s.date name="staff.vcFormationDate" format="yyyy-MM-dd"/>&nbsp;
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>职级：</th>
	      <td>
	        <@s.property value="staff.dictRanks.vcName" escape="false"/>&nbsp;
	      </td>
	      <th> 任现职级时间：</th>
	      <td>
	      <@s.date name="staff.vcRankDate" format="yyyy-MM-dd"/>&nbsp;
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>职务：</th>
	      <td>
	      <@s.property value="staff.dictJobs.vcName" escape="false"/>&nbsp;
	      
	      </td>
	      
	      <th> 任现职务时间：</th>
	      <td>
	      <@s.date name="staff.vcJobDate" format="yyyy-MM-dd"/>&nbsp;
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>工作分工：</th>
	      <td colspan="3">
			<@s.property value="staff.vcExtend1" escape="false"/>&nbsp;
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>工作分工时间：</th>
	      <td>
	      	<@s.date name="staff.vcWorkDate" format="yyyy-MM-dd"/>&nbsp;
	      </td>
	      <th>参加禁毒工作时间：</th>
	      <td>
	      	<input class="add_input form_input" type="text" name="staff.vcDrugDate" id="vcDrugDate" value="<@s.date name="staff.vcDrugDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择参加禁毒工作时间" onclick="displayCalendar('',document.getElementById('vcDrugDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <#else>	
	    <tr>
	      <th>编制：</th>
	      <td>
	        <select class="add_input form_select" name="staff.dictFormations.pid" id="select4">
	         <#list dictFormations as dict>
		        <option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictFormations.pid">selected</@s.if>>${dict.vcName}</option>
		     </#list>
	      </select>
	      </td>
	      <th>任现编制时间：</th>
	      <td>
	      	<input class="add_input form_input" type="text" name="staff.vcFormationDate" id="vcFormationDate" value="<@s.date name="staff.vcFormationDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择任现编制时间" onclick="displayCalendar('',document.getElementById('vcFormationDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>职级：</th>
	      <td>
	        <select class="add_input form_select" name="staff.dictRanks.pid" id="select5">
	         <#list dictRanks as dict>
		        <option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictRanks.pid">selected</@s.if>>${dict.vcName}</option>
		     </#list>
	      </select>
	      </td>
	      <th> 任现职级时间：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcRankDate" id="vcRankDate" value="<@s.date name="staff.vcRankDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择任现职级时间" onclick="displayCalendar('',document.getElementById('vcRankDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>职务：</th>
	      <td>
	      <!--<input class="add_input form_input" type="text" name="staff.vcJob" id="vcJob" value="<@s.property value="staff.vcJob" escape="false"/>" />-->
	      <select class="add_input form_select" name="staff.dictJobs.pid" id="select5">
	         <#list dictjobs as dict>
		        <option value=${dict.pid} <@s.if test="${dict.pid}==staff.dictJobs.pid">selected</@s.if>>${dict.vcName}</option>
		     </#list>
	      </select>
	      
	      </td>
	      
	      <th> 任现职务时间：</th>
	      <td>
	      <input class="add_input form_input" type="text" name="staff.vcJobDate" id="vcJobDate" value="<@s.date name="staff.vcJobDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择任现职务时间" onclick="displayCalendar('',document.getElementById('vcJobDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>工作分工：</th>
	      <td colspan="3">
			<#list dictworks as dict>
				<input type="checkbox" name="workDivision_${dict_index+1}" id="workDivision_${dict_index+1}" value="${dict.pid}" propname="${dict.vcName}"/>${dict.vcName}
	        </#list>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <th>工作分工时间：</th>
	      <td>
	      	<input class="add_input form_input" type="text" name="staff.vcWorkDate" id="vcWorkDate" value="<@s.date name="staff.vcWorkDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择工作分工时间" onclick="displayCalendar('',document.getElementById('vcWorkDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <th>参加禁毒工作时间：</th>
	      <td>
	      	<input class="add_input form_input" type="text" name="staff.vcDrugDate" id="vcDrugDate" value="<@s.date name="staff.vcDrugDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择参加禁毒工作时间" onclick="displayCalendar('',document.getElementById('vcDrugDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
	      </td>
	      <td>&nbsp;</td>
	    </tr>
	    </#if>
	    <tr>
	      <th>备注：</th>
	      <td colspan="3">
	      <textarea name="staff.vcRemark" id="vcRemark" cols="100" rows="5" require="false" trim="true" class="add_textarea"  onKeyUp="testTextareaLength(this,200)" onBlur="testLeaveTextareaLength(this,200)"><@s.property value="staff.vcRemark" escape="false"/></textarea></td>
	      <td>&nbsp;</td>
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
	      <th></th>
	      <th><input type="button" onclick="formSubmit()" value="  保存  " /></th>
	      <th><input type="button" onclick="doCancel()" value="  返回  " /></th>
	      <th></th>
	      <th>&nbsp;</th>
	    </tr>
	</table>
</form>
<#include "../../common/footer.ftl" />