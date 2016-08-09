<form id="roleForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="role.pid" id="roleId" value="<@s.property value="role.pid" escape="false"/>" />
		
		<input type="hidden" name="search.vcRoleName" value="<@s.property value="search.vcRoleName" escape="false"/>" />
		<input type="hidden" name="pageNum" value="<@s.property value="pageNum" escape="false"/>" />
		
		<@s.set name="isAdmin" value="role != null && role.pid != null && role.pid < @com.sunraysoft.hr.constant.Constant@SEQUENCE_START_WITH" />
		
		<tr>

			<th width="20%"><span class="require">*</span>角色名：</th>
			<td width=" 80%">
				<@s.if test="#isAdmin">
					<@s.property value="role.vcRoleName" escape="false"/>
					<input type="hidden" value="<@s.property value="role.vcRoleName" escape="false"/>" onblur="checkRoleNameUnique()" msg="角色名不能为空！" trim="true" require="true" dataType="Require" 
					 onKeyUp="checkMaxInput(this,50)"/>
				</@s.if>
				<@s.else>
					<input  type="text" class="add_input form_input" name="role.vcRoleName" id="vcRoleName" value="<@s.property value="role.vcRoleName" escape="false"/>" onblur="checkRoleNameUnique()" msg="角色名不能为空！" trim="true" require="true" dataType="Require"  
					 onKeyUp="checkMaxInput(this,50)"/>
					<a href="javascript:checkRoleNameUnique()">验证唯一</a>
					<span id="roleNameMsg"></span>
				</@s.else>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td>
				<textarea name="role.vcMemo" cols="5" class="add_textarea" rows="5" min="0" max="100" onKeyDown="checkMaxInput(this,100)" onKeyUp="checkMaxInput(this,100)" onBlur="clsInput(this)"><@s.property value="role.vcMemo" /></textarea>

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
			<th>&nbsp;</th>
			<td>
				<input  type="button" value="　保存　" onclick="formSubmit()"/>　<input  type="button" value="　返回　" onclick="doCancel()"/>
			</td>
			</tr>
	</table>
</form>