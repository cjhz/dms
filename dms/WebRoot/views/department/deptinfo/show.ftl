<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl"/>
		<script type="text/javascript">
			var param = 'searchPmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="searchPmBscDeptInfo.vcDeptName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&searchPmBscDeptInfo.pmBscDeptType.pid=<@s.property value="searchPmBscDeptInfo.pmBscDeptType.pid" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.pmBscDict.pid=<@s.property value="searchPmBscDeptInfo.pmBscDict.pid" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.vcRender=<@s.property value="searchPmBscDeptInfo.vcRender" escape="false" default="0"/>'
			+'&searchPmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.pid" escape="false"/>'
			+'&searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=<@s.property value="searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false"/>';
			
			function doCancel() {
				document.location = '${contextPath}/department/deptinfo/searchDepType.action?'+param;
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>机构信息管理>>机构信息管理>>查看</strong></div>
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<#include "form_show.ftl" />
	</body>
</html>		
