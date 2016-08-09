<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="pmBscDeptInfo.pid" id="vcDeptId" value="<@s.property value="pmBscDeptInfo.pid" escape="false"/>" />
		<input type="hidden" id="areadisplay" value="<@s.property value="pmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
		<input type="hidden" id="areacontrol" value="${user.areaCode}" />
		<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.pid" value="<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.pid" escape="false"/>" />
		<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" value="<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
		<tr>
			<th width="20%">行政区域：</th>
			<td colspan="3">
				<input type="hidden" name="pmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="" />
			  <select require="true" dataType="Require"  trim="true"  msg="请选择行政区域" class="serch" id="province" onchange="cascade(1, '${user.areaCode}', '${user.areaCode}');">
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
			<th>机构编号：</th>
			<td colspan="3">
				<input name="pmBscDeptInfo.vcDeptNo" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcDeptNo" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型"/>
			</td>
		</tr>-->
		<tr>
			<th>机构性质：</th>
			<td colspan="3">
				<select name="pmBscDeptInfo.pmBscDeptType.pid" require="true" dataType="Require"  trim="true" msg="请选择机构性质" class="add_input form_select">
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscdepttypelist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscDeptInfo.pmBscDeptType.pid==pid">selected="selected"</@s.if>>
		        		<@s.property value="vcTypeName"/></option>
		        	</@s.iterator>
				</select>
			</td>
		</tr>
		<tr>
			<th width="20%">机构名称：</th>
			<td width="40%">
				<input name="pmBscDeptInfo.vcDeptName" id="vcDeptName" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcDeptName" />" 
				class="add_input form_input" require="true" dataType="Require"  trim="true" msg="请填写机构名称"
				  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" />
				<br>
				<span id="roleNameMsg"></span>
			</td>
			<th width="10%">机构职级：</th>
			<td width="30%">
				<select name="pmBscDeptInfo.pmBscDict.pid" require="true" dataType="Require"  trim="true"  msg="请选择机构职级" class="add_input form_select">
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscdictlist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscDeptInfo.pmBscDict.pid==pid">selected="selected"</@s.if>>
		        		<@s.property value="vcName"/></option>
		        	</@s.iterator>
				</select>
			</td>
		</tr>
		<tr>
			<th>机构负责人：</th>
			<td>
				<input name="pmBscDeptInfo.vcChargeName" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcChargeName" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型" onKeyUp="checkMaxInput(this,20)" 
				   onBlur="clsInput(this)" />
			</td>
			<th>负责人电话：</th>
			<td>
				<input name="pmBscDeptInfo.vcPhone" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcPhone" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型" 
				 onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" />
			</td>
		</tr>
		<tr>
			<th>机构联系人：</th>
			<td>
				<input name="pmBscDeptInfo.vcContactName" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcContactName" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型" 
				 onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" />
			</td>
			<th>联系人电话：</th>
			<td>
				<input name="pmBscDeptInfo.vcContactPhone" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcContactPhone" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型" 
				 onKeyUp="checkMaxInput(this,20)"  onBlur="clsInput(this)" />
			</td>
		</tr>
		<tr>
			<th>机构详细地址：</th>
			<td colspan="3">
				<input name="pmBscDeptInfo.vcAddress" size="35" type="text" value="<@s.property value="pmBscDeptInfo.vcAddress" />" 
				class="add_input form_input" require="false" trim="true" msg="填写正确的类型" 
				 onKeyUp="checkMaxInput(this,100)" onBlur="clsInput(this)" />
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td colspan="3">
				<textarea id="vcmemo" name="pmBscDeptInfo.vcRemark" xxx="xxx"
				style="width: 400px;height: 50px;" maxLen="1000" 
				class="add_textarea" onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="pmBscDeptInfo.vcRemark" /></textarea>
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
			<td colspan="3">&nbsp;&nbsp;
				<input  type="button" value="　保存　" onclick="formSubmit()"/>　
				<input  type="button" onclick="javascript:doCancel();" value="　返回　"/>
			</td>
		</tr>
	</table>
</form>