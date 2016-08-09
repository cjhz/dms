<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param_ = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false" default=""/>')
			+'&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&search.vcWorkDivision=<@s.property value="search.vcWorkDivision" escape="false" default=""/>'
			+'&search.dictRanks.pid=<@s.property value="search.dictRanks.pid" escape="false" default="0"/>'
			+'&search.vcCardNo=<@s.property value="search.vcCardNo" escape="false" default="0"/>'
			+'&search.dictCulturals.pid=<@s.property value="search.dictCulturals.pid" escape="false" default="0"/>'
			+'&search.vcSex=<@s.property value="search.vcSex" escape="false" default="0"/>'
			+'&search.isShowAll=<@s.property value="search.isShowAll" escape="false" default="0"/>'
			+'&search.bbirth=<@s.property value="search.bbirth" escape="false" default=""/>'
			+'&search.ebirth=<@s.property value="search.ebirth" escape="false" default=""/>'
			+'&search.vcOphone=<@s.property value="search.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay};
			$(function(){
				setChkVal('personProp','workDivision');
				$('#selBtn').dialog({ 
					lock: true,
					left:'50%',
					top:'50%',
					width:'500px',
					height:'300px',
					title:'机关选择',
					content:'url:${contextPath}/basic/staff/dialog.action?allparam=0'
				});
				
				$('#canBtn').click(function(){
					$('#deptIds').val('');
					$('#deptNames').val('');
				});
			});
        	function doCancel() {
        		var url = '${contextPath}/basic/staff/pageQuery.action?'+ param_;
        		document.location = url;
        	}
        	
        	function formSubmit() {
        		getChkVal('personProp','workDivision');
				var staffForm = document.getElementById('staffForm');
				if(!Validator.Validate(staffForm, 3)) {
					return false;
				}
				if(!($('#vcDrugDate').val()>$('#vcBirth').val())){
					alert("出生日期必须小于参加禁毒工作时间");
					return;
				}
				staffForm.submit();
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员基础信息维护>修改</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:doCancel();" class="return">返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staff/update.action'" />
		<@s.set name="ifedit" value="true" />
		<#include "form.ftl" />
	</body>
</html>
