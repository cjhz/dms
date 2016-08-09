<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl"/>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'searchpmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="searchpmBscDeptInfo.vcDeptName" escape="false"/>')
			+'&searchpmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.pid" escape="false" default="0"/>'
			+'&searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false" default="0"/>';
        	function formSubmit() {
        		var goldForm = document.getElementById('deptForm');
        		if(!Validator.Validate(goldForm, 3)) {
					return false;
				}
				
				goldForm.submit();
        	}
        	
			function doCancel() {
				//document.location = '${contextPath}/department/deptobserving/searchDepType.action';
				document.location = '${contextPath}/department/deptobserving/searchDept_observing.action?pid='+${pid}+'&'+param;
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>机构信息管理>>机构立功表彰>>机构立功表彰维护>>新增</strong></div>
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/department/deptobserving/addDepType.action?pid=${pid}'" />
		<#include "form.ftl" />
	</body>
</html>		
