<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<input type="hidden" name="pmBscObserving.pid" id="roleId" value="<@s.property value="pmBscObserving.pid" escape="false"/>" />
		<input type="hidden" name="pmBscObserving.vcObservType" id="roleId" value="<@s.property value="pmBscObserving.vcObservType" escape="false"/>" />
		<input type="hidden" name="pmBscObserving.pmBscDeptInfo.pid" id="roleId" value="<@s.property value="pmBscObserving.pmBscDeptInfo.pid" escape="false"/>" />
		<input type="hidden" name="searchpmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.pid" escape="false"/>" />
		<input type="hidden" name="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" id="departmentcode" value="<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
			
		<tr>
			<th>行政区域：</th>
			<td>&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.vcAreaName" escape="false"/>
			</td>
			<th>机构名称：</th>
			<td>&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcDeptName" escape="false"/>
			</td>
		</tr>
		<tr>
			<th>机构性质：</th>
			<td>&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscDeptType.vcTypeName" escape="false"/>
			</td>
			<th>机构职级：</th>
			<td>&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscDict.vcName" escape="false"/>
			</td>
		</tr>
		<tr>
			<th>机构负责人：</th>
			<td>
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcChargeName" />&nbsp;
			</td>
			<th>负责人电话：</th>
			<td>
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcPhone" />&nbsp;
			</td>
		</tr>
		<tr>
			<th>机构联系人：</th>
			<td>
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcContactName" />&nbsp;
			</td>
			<th>联系人电话：</th>
			<td>
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcContactPhone" />&nbsp;
			</td>
		</tr>
		<tr>
			<th>机构详细地址：</th>
			<td colspan="3">&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcAddress" />
			</td>
		</tr>
		<tr>
			<th>立功、受表彰时间：</th>
			<td>
				<input type="text" name="pmBscObserving.vcObservTime" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择立功、受表彰时间" class="add_input form_input" 
				readonly id="vcObservTime" onclick="displayCalendar('',document.getElementById('vcObservTime'),'yyyy-mm-dd', this)"
				 value="<@s.date name="pmBscObserving.vcObservTime" format="yyyy-MM-dd"/>" />
			</td>
			<th>立功、受表彰情况：</th>
			<td>
				<select class="search" name="pmBscObserving.pmBscObserType.pid" require="true" dataType="Require"  trim="true" msg="请选择立功、受表彰情况" >
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscobsertypelist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscObserving.pmBscObserType.pid==pid">selected="selected"</@s.if>>
		        		<@s.property value="vcName"/></option>
		        	</@s.iterator>
				</select>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td colspan="3">&nbsp;
				<textarea id="vcmemo" name="pmBscObserving.vcRemark"
				style="width: 400px;height: 50px;" maxLen="1000" 
				class="add_textarea" onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="pmBscObserving.vcRemark" /></textarea>
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
	<#include "../../common/footer.ftl"/>
</form>