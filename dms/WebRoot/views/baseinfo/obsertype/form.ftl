<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="pmBscObserType.pid" id="roleId" value="<@s.property value="pmBscObserType.pid" escape="false"/>" />
		<input type="hidden" name="pmBscObserType.vcType" id="vcType" value="${vctype}" />
		<tr>
			<th width="30%">${titlename}：</th>
			<td width="70%">
				<input name="pmBscObserType.vcName" id="vcName" size="50" type="text" value="<@s.property value="pmBscObserType.vcName" />" 
				class="add_input form_input" require="true" dataType="Require"  trim="true" msg="${titlename}不能为空"  onKeyUp="checkMaxInput(this,50)"  onBlur="clsInput(this)" />
				<br>
				<span id="roleNameMsg"></span>
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
			<th width="30%">&nbsp;</th>
			<td width="70%">&nbsp;
				<input  type="button" value="　保存　" onclick="formSubmit()"/>　
				<input  type="button" onclick="javascript:doCancel();" value="　返回　"/>
			</td>
		</tr>
	</table>
</form>