<form id="staffForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
		<input type="hidden" name="pmBscObservingStaff.pmBscStaffInfo.pid" id="staffId" value="<@s.property value="staff.pid" escape="false"/>" />
		<input type="hidden" name="pmBscObservingStaff.pid" id="staffId" value="<@s.property value="pmBscObservingStaff.pid" escape="false"/>" />
		<input type="hidden" name="search.vcRealName" value="<@s.property value="search.vcRealName" escape="false"/>" />
		<input type="hidden" name="pageNum" value="<@s.property value="pageNum" escape="false"/>" />
	   	<input type="hidden" name="search.vcOphone" id="departmentid" value="<@s.property value="search.vcOphone" />" />
			<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
	    <tr>
			    <th>人员属性：</th>
			    <td colspan="3">
			    <#list newdictprops as dict >
			    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
			    </#list>&nbsp;
			  </tr>
			  <tr>
			    <th>所属机构：</th>
			    <td colspan="3">
			    	<#list deptList as dept>
			      		${dept.pmBscDeptInfo.vcDeptName}<#if dept_has_next>;</#if>
			       </#list>
				</td>
			  </tr>
			  <tr>
			    <th>姓名：</th>
			    <td><@s.property value="staff.vcRealName" escape="false"/>&nbsp;</td>
			    <th>身份证号码：</th>
			    <td><@s.property value="staff.vcCardNo.substring(0,staff.vcCardNo.length()-4)+'XXXX'" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>出生日期：</th>
			    <td><@s.date name="staff.vcBirth" format="yyyy-MM-dd"/>&nbsp;</td>
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
			    <th>警号：</th>
			    <td><@s.property value="staff.vcAlarm" escape="false"/>&nbsp;</td>
			    <th>文化程度：</th>
			    <td><@s.property value="staff.dictCulturals.vcName" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>政治面貌：</th>
			    <td><@s.property value="staff.dictPoliticals.vcName" escape="false"/>&nbsp;</td>
			    <th>手机号码：</th>
			    <td><@s.property value="staff.vcMphone" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>办公外线：</th>
			    <td><@s.property value="staff.vcOphone" escape="false"/>&nbsp;</td>
			    <th>传真：</th>
			    <td><@s.property value="staff.vcFax" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>编制：</th>
			    <td><@s.property value="staff.dictFormations.vcName" escape="false"/>&nbsp;</td>
			    <th>参加禁毒工作时间：</th>
			    <td><@s.date name="staff.vcDrugDate" format="yyyy-MM-dd"/>&nbsp;</td>
			  </tr>
			 <tr>
				<th>立功表彰</th>
				<th colspan="3">&nbsp;</th>
			</tr>
	   		 <tr>
			<th>立功、受表彰时间：</th>
			<td>
				<input type="text" name="pmBscObservingStaff.vcObservTime" class="add_input form_input" msg="请选择立功、受表彰时间" require="true" dataType="Require"  trim="true" 
				readonly id="vcObservTime" onclick="displayCalendar('',document.getElementById('vcObservTime'),'yyyy-mm-dd', this)"
				 value="<@s.date name="pmBscObservingStaff.vcObservTime" format="yyyy-MM-dd"/>" />
			</td>
			<th>立功、受表彰情况：</th>
			<td>
				<select class="serch" name="pmBscObservingStaff.pmBscObserType.pid" msg="请选择立功、受表彰情况" require="true" dataType="Require"  trim="true" >
				
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscobsertypelist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscObservingStaff.pmBscObserType.pid==pid">selected="selected"</@s.if>> <@s.property value="vcName"/></option>
		        	</@s.iterator>
				</select>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td colspan="3">
				<textarea id="vcmemo" name="pmBscObservingStaff.vcRemark" xxx="xxx"
				style="width: 400px;height: 50px;" maxLen="1000" 
				class="add_textarea" onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="pmBscObservingStaff.vcRemark" /></textarea>
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
			<td colspan="3">&nbsp;&nbsp;
				<input  type="button" value="　保存　" onclick="formSubmit()"/>　
				<input  type="button" onclick="javascript:doCancel();" value="　返回　"/>
			</td>
		</tr>
	</table>
</form>
<#include "../../common/footer.ftl" />