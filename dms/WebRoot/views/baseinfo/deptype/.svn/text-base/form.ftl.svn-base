<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="pmBscDeptType.pid" id="roleId" value="<@s.property value="pmBscDeptType.pid" escape="false"/>" />
		<tr>
			<th width="30%">机构性质：</th>
			<td width="70%">
				<input name="pmBscDeptType.vcTypeName" id="vcTypeName" size="50" type="text" value="<@s.property value="pmBscDeptType.vcTypeName" />" 
				class="add_input form_input" require="true" dataType="Require"  trim="true" msg="机构性质不能为空"
				 onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" />
				<br>
				<span id="roleNameMsg"></span>
			</td>
		</tr>
		<tr>
			<th width="30%">机构属性：</th>
			<td width="70%">
				<select name="pmBscDeptType.vcDeptProp"  require="true" dataType="Require"  trim="true" msg="请选择机构属性" class="form_select" style="margin-left:10px;">
					<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_1" /> 
						<@s.if test="pmBscDeptType.vcDeptProp == @com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_1">selected</@s.if>>
						<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_1"/>
					</option>
					<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_2" /> 
						<@s.if test="pmBscDeptType.vcDeptProp == @com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_2">selected</@s.if>>
						<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_2"/>
					</option>
					<option value=<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_3" /> 
						<@s.if test="pmBscDeptType.vcDeptProp == @com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_3">selected</@s.if>>
						<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_3"/>
					</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="30%">&nbsp;</th>
			<td width="70%">
					<font id="remLen1">&nbsp;</font>
					<font color=red id="remLen2">&nbsp;</font>
					<font id="remLen3">&nbsp;</font>
					<font color=red id="remLen4">&nbsp;</font>
					<font id="remLen5">&nbsp;</font>&nbsp;
			</td>
		</tr>
		<tr>
			<th width="40%">&nbsp;</th>
			<td width="60%">&nbsp;&nbsp;
				<input  type="button" value="　保存　" onclick="formSubmit()"/>　
				<input  type="button" onclick="javascript:doCancel();" value="　返回　"/>
			</td>
		</tr>
	</table>
</form>