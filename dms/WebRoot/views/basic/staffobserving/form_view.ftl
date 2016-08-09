<form id="staffForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
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
				<th>立功表彰</th>
				<th colspan="3">&nbsp;</th>
			</tr>
	    	<tr>
			<th>立功、受表彰时间：</th>
			<td>
				<@s.date name="pmBscObservingStaff.vcObservTime" format="yyyy-MM-dd"/>
			</td>
			<th>立功、受表彰情况：</th>
			<td>
				<@s.property value="pmBscObservingStaff.pmBscObserType.vcName" escape="false"/>&nbsp;
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td colspan="3">
				<@s.property value="pmBscObservingStaff.vcRemark" />&nbsp;
			</td>
		</tr>
	</table>
</form>