<form id="deptForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
	<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
	<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
	
		<tr>
			<th width="20%">行政区域：</th>
			<td width="25%">&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.pmBscAreaInfo.vcAreaName" escape="false"/>
			</td>
			<th width="20%">机构名称：</th>
			<td width="25%">&nbsp;
				<@s.property value="pmBscObserving.pmBscDeptInfo.vcDeptName" escape="false"/>
			</td>
		</tr>
		<tr>
			<th>机构性质：</th>&nbsp;
			<td>
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
			<td>&nbsp;
				<@s.date name="pmBscObserving.vcObservTime" format="yyyy-MM-dd"/>
			</td>
			<th>立功、受表彰情况：</th>
			<td>&nbsp;
				<@s.property value="pmBscObserving.pmBscObserType.vcName" />
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td colspan="3">&nbsp;
				<@s.property value="pmBscObserving.vcRemark" />
			</td>
		</tr>
	</table>
</form>