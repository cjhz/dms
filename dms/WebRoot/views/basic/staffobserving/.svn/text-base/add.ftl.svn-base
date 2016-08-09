<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl"/>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false"/>')
						+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&search.vcOphone='+'<@s.property value="search.vcOphone"  escape="false"/>'+'&search.isShowAll='+'<@s.property value="search.isShowAll"  escape="false"/>';
        	function formSubmit() {
        		var goldForm = document.getElementById('staffForm');
        		if(!Validator.Validate(goldForm, 3)) {
					return false;
				}
				
				goldForm.submit();
        	}
        	
			function doCancel() {
				document.location = '${contextPath}/basic/staffobserving/pageQueryObserving.action?staffId='+'${staffId}' + '&' +param;
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员立功表彰管理>>人员立功表彰维护>>新增</strong></div>
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staffobserving/add.action'" />
		<#include "form.ftl" />
	</body>
</html>		
