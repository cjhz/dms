<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl"/>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
        	function formSubmit() {
        		$("#roleNameMsg").html("")
        		var goldForm = document.getElementById('deptForm');
        		if(!Validator.Validate(goldForm, 3)) {
					return false;
				}
				var vcName = $.trim($('#vcName').val());
				var pId = $('#roleId').val();
				var url = '${contextPath}/baseinfo/obsertype/ajaxCheckDepTypeNameUnique.action';
				$.post(url, {'pmBscObserType.vcName': vcName, 'pmBscObserType.pid': pId, 'vctype':${vctype}}, function(jsonObj) {
					if(jsonObj.timeout) {
						timeoutRedirect('${contextPath}');
					} else if(!jsonObj.flag){
						$('#roleNameMsg').css('color', 'red');	
						$('#roleNameMsg').html(jsonObj.msg);
						return false;
					}else{
						goldForm.submit();
					}
				}, 'json');
        	}
        	
			function doCancel() {
				document.location = '${contextPath}/baseinfo/obsertype/searchDepType.action?vctype='+${vctype};
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>代码维护>><@s.if test="vctype == 1">机构立功表彰修改</@s.if>
	<@s.if test="vctype != 1">人员立功表彰修改</@s.if></strong></div>
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/baseinfo/obsertype/editDepType.action?vctype=${vctype}'" />
		<#include "form.ftl" />
	</body>
</html>		
