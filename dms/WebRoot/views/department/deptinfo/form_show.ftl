<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
		<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.pid" value="<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.pid" escape="false"/>" />
		<input type="hidden" name="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" value="<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>" />
	
		<input type="hidden" name="pmBscDeptInfo.pid" id="roleId" value="<@s.property value="pmBscDeptInfo.pid" escape="false"/>" />
		<tr>
			<th width="20%">行政区域：</th>
			<td colspan="3">
				<!--<input type="hidden" name="pmBscDeptInfo.pmBscAreaInfo.pid" id="departmentid" value="" />
			  <select disabled="disabled" require="true" dataType="Require"  trim="true"  msg="请选择行政区域" class="serch" id="province" onchange="cascade(1);">
					<option value="" selected="selected">
						省
					</option>
				</select>
				<select disabled="disabled" class="serch" id="city" onchange="cascade(2);">
					<option value="" selected="selected">
						市
					</option>
				</select>
				<select disabled="disabled" class="serch" id="county">
					<option value="" selected="selected">
						区/县
					</option>
				</select>-->
				<@s.property value="pmBscDeptInfo.pmBscAreaInfo.vcAreaName" escape="false"/>
				&nbsp;
			</td>
		</tr>
		
		<tr>
			<th>机构性质：</th>
			<td colspan="3">
				<!--<select disabled="disabled" name="pmBscDeptInfo.pmBscDeptType.pid"  require="true" dataType="Require"  trim="true" msg="请选择机构性质" class="add_input form_select">
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscdepttypelist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscDeptInfo.pmBscDeptType.pid==pid">selected="selected"</@s.if>>
		        		<@s.property value="vcTypeName"/></option>
		        	</@s.iterator>
				</select>-->
				<@s.property value="pmBscDeptInfo.pmBscDeptType.vcTypeName" escape="false"/>
				&nbsp;
			</td>
		</tr>
		<tr>
			<th width="20%">机构名称：</th>
			<td width="40%">
				<@s.property value="pmBscDeptInfo.vcDeptName" />&nbsp;
			</td>
			<th width="10%">机构职级：</th>
			<td width="20%">
				<!--<select disabled="disabled" name="pmBscDeptInfo.pmBscDict.pid" dataType="Require" require="true"  trim="true"  msg="请选择机构职级" class="add_input form_select">
					<option value="">--请选择--</option>
		        	<@s.iterator value="pmbscdictlist">
		        		<option value="<@s.property value="pid"/>" 
		        		<@s.if test="pmBscDeptInfo.pmBscDict.pid==pid">selected="selected"</@s.if>>
		        		<@s.property value="vcName"/></option>
		        	</@s.iterator>
				</select>-->
				<@s.property value="pmBscDeptInfo.pmBscDict.vcName" escape="false"/>
				&nbsp;
			</td>
		</tr>
		<tr>
			<th>机构负责人：</th>
			<td>
				<@s.property value="pmBscDeptInfo.vcChargeName" />&nbsp;
			</td>
			<th>负责人电话：</th>
			<td>
				<@s.property value="pmBscDeptInfo.vcPhone" />&nbsp;
			</td>
		</tr>
		<tr>
			<th>机构联系人：</th>
			<td>
				<@s.property value="pmBscDeptInfo.vcContactName" />&nbsp;
			</td>
			<th>联系人电话：</th>
			<td>
				<@s.property value="pmBscDeptInfo.vcContactPhone" />&nbsp;
			</td>
		</tr>
		<tr>
			<th>机构详细地址：</th>
			<td colspan="3">
				<@s.property value="pmBscDeptInfo.vcAddress" />&nbsp;
			</td>
		</tr>
		 <tr>
		    <th>机构立功表彰情况：</th>
		    <td colspan="3">
		    	<#if (obserList?exists)&&(obserList?size>0)>
				<#list obserList as obser>
		    	${obser.vcObservTime?string('yyyy-MM-dd')}&nbsp;&nbsp;&nbsp;${obser.pmBscObserType.vcName!""}
		    	<#if obser_has_next><br/></#if>
		    	</#list>
		    	<#else>无
		    	</#if>&nbsp;
			</td>
		  </tr>
		<tr>
			<th>备注：</th>
			<td colspan="3"><@s.property value="pmBscDeptInfo.vcRemark" />&nbsp;</td>
		</tr>
		<#if (serverlogs?exists)&&(serverlogs?size>0)>
			<tr>
				<th>修改日志：</th>
				<td colspan="3">
					<#list serverlogs as log>
						${log.vcExtend1!""}&nbsp;&nbsp;${log.logMsg!""}<br>
					</#list>
				</td>
			</tr>
		</#if>
	</table>
</form>