<form id="loginForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="pmbsclogininfo.pid" id="pmbsclogininfo" value="<@s.property value="pmbsclogininfo.pid" escape="false"/>" />
		<input type="hidden" name="search.vcName" value="<@s.property value="search.vcName" escape="false"/>" />
		<input type="hidden" name="pageNum" value="<@s.property value="pageNum" escape="false"/>" />
			<input type="hidden" id="areadisplay" value="<@s.property value="pmbsclogininfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
			<input type="hidden" id="areacontrol" value="${user.areaCode}" />
		<input type="hidden" name="search.pmBscAreaInfo.pid" value="<@s.property value="search.pmBscAreaInfo.pid" escape="false" />" />
		<input type="hidden" name="search.pmBscAreaInfo.vcDepartmentCode" value="<@s.property value="search.pmBscAreaInfo.vcDepartmentCode" escape="false" />" />
    	<input type="hidden" name="pmbsclogininfo.pmBscAreaInfo.pid" id="departmentid" value="" />
		<input type="hidden" name="pmbsclogininfo.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="" />
		<input type="hidden" name="pmbsclogininfo.vcUserStatus" value="<@s.property value="pmbsclogininfo.vcUserStatus" escape="false" />" />
		<!--<input type="hidden" name="pmbsclogininfo.vcRuleDept" id="ruleDept" value="<@s.property value="pmbsclogininfo.vcRuleDept" escape="false" />"  />-->
		
		<@s.set name="isAdmin" value="staff != null && staff.pid != null && staff.pid < @com.sunraysoft.hr.constant.Constant@SEQUENCE_START_WITH" />
		
		<tr>
	      <th colspan="4" style="text-align:center;"><strong><@s.if test = "#edit==true">修改</@s.if><@s.else>新增</@s.else>账号信息</strong></th>
	    </tr>

		  <tr>
		    <th width="20%">管辖区域：</th>
		    <td colspan="3">
		    	<select class="serch" id="province" onchange="cascade(1, '${user.areaCode}', '${user.areaCode}');">
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
			</td>
		  </tr>
		  <!--<tr>
		    <th>管辖机构：</th>
		    <td colspan="3">
		    	<input type="checkbox" name="ruleDept_1" id="ruleDept_1" value="<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_KEY_1"/>" /><@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_1"/>
		    	<input type="checkbox" name="ruleDept_2" id="ruleDept_2" value="<@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_KEY_2"/>" /><@s.property value="@com.sunraysoft.hr.constant.BizConstant@DEPT_PROP_VAL_2"/>
			</td>
		  </tr>-->
		  <tr>
		    <th>身份证号码：</th>
		    <td width="20%">
		      <input class="add_input form_input"  type="text" name="pmbsclogininfo.vcCardNo" id="vcCardNo" value="<@s.property value="pmbsclogininfo.vcCardNo" escape="false" />"  onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" msg="身份证号不能为空！" trim="true" require="true" dataType="Require"  />&nbsp;</td>
		    <th width="20%">姓名：</th>
		    <td width="20%">
		      <input class="add_input form_input"  type="text" name="pmbsclogininfo.vcName" id="vcName" value="<@s.property value="pmbsclogininfo.vcName" escape="false" />"  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" msg="姓名不能为空！" trim="true" require="true" dataType="Require"  />&nbsp;</td>
		  </tr>
		  <tr>
		    <th>账号：</th>
		    <td>
		    <@s.if test = "#edit==true">
		    	<@s.property value="pmbsclogininfo.vcLoginName" escape="false" />
		    	<input class="add_input form_input"  type="hidden" name="pmbsclogininfo.vcLoginName" value="<@s.property value="pmbsclogininfo.vcLoginName" escape="false" />" msg="账号不能为空！" trim="true" require="true" dataType="Require"   onClick="checkAccount(this,20)" onKeyUp="checkAccount(this,20)" onBlur="clsInput(this)" />
		    </@s.if>
	      	<@s.else>
		      <input class="add_input form_input"  type="text" name="pmbsclogininfo.vcLoginName" id="vcLoginName" value="<@s.property value="pmbsclogininfo.vcLoginName" escape="false"/>" onClick="checkAccount(this,20)" onKeyUp="checkAccount(this,20)" onBlur="clsInput(this)" msg="账号不能为空！" trim="true" require="true" dataType="Require"  />
	      	</@s.else>
	      	<span id="roleNameMsg"></span>
	      	&nbsp;
		    </td>
		    <@s.if test = "#edit==true">
		    <td>&nbsp;</td>
		    <td>
		      &nbsp;<input type="hidden" name="pmbsclogininfo.vcLoginPwd" value="<@s.property value="pmbsclogininfo.vcLoginPwd" escape="false" />" />
		    </td>
		    </@s.if>
	      	<@s.else>
		    <th>密码：</th>
		    <td>
		      <input class="add_input form_input"  type="password" name="pmbsclogininfo.vcLoginPwd" id="vcLoginPwd" value="<@s.property value="pmbsclogininfo.vcLoginPwd" escape="false" />" onKeyUp="checkMaxInput(this,8)" onBlur="clsInput(this)" msg="密码不能为空！" trim="true" require="true" dataType="Require"  />&nbsp;
		    </td>
		    </@s.else>
		  </tr>
		  <tr>
		    <th>账号权限：</th>
		    <td colspan="3">
		      <select class="add_input form_select" name="pmbsclogininfo.pmMngRoleInfo.pid" id="select" msg="权限不能为空！" trim="true" require="true" dataType="Require"  >
		      	<#list rolelist as role>
		        	<option value=${role.pid} <@s.if test="${role.pid}==pmbsclogininfo.pmMngRoleInfo.pid">selected</@s.if>>${role.vcRoleName}</option>
		     	</#list>
		      </select>
		      </td>
		  </tr>
		  <tr>
		    <th>备注：</th>
		    <td colspan="3">
		      <textarea name="pmbsclogininfo.vcRemark" id="vcRemark" cols="45" rows="5" onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="pmbsclogininfo.vcRemark" escape="false"/></textarea>
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
	      <th colspan="2" style="text-align:center;">
	      <input type="button" onclick="formSubmit()" value="  保存  " />
	       <@s.if test = "#edit==true">
		   	<@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN"><input type="button" onclick="stopOropen(${pmbsclogininfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"/>)" value="  启用  " /></@s.if>
			<@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"><input type="button" onclick="stopOropen(${pmbsclogininfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"/>)" value="  停用  " /></@s.if>
			<@s.if test="${pmbsclogininfo.vcUserStatus!0}==@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE"><input type="button" onclick="stopOropen(${pmbsclogininfo.pid},<@s.property value="@com.sunraysoft.hr.constant.BizConstant@LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL"/>)" value="  启用  " /></@s.if>
		   </@s.if>
	      <input type="button" onclick="doCancel()" value="  返回  " />
	      <th>&nbsp;</th>
	    </tr>
	</table>
</form>