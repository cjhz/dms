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
				var url = '${contextPath}/baseinfo/basedict/ajaxCheckDepTypeNameUnique.action';
				$.post(url, {'pmBscDict.vcName': vcName, 'dictype':${dictype}}, function(jsonObj) {
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
				document.location = '${contextPath}/baseinfo/basedict/searchDepType.action?dictype='+${dictype};
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>代码维护>>${titlename}新增</strong></div>
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/baseinfo/basedict/addDepType.action?dictype=${dictype}'" />
		<#include "form.ftl" />
	</body>
</html>		
