<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title>机构立功表彰信息维护</title>
		<script type="text/javascript">
			var param = 'searchpmBscDeptInfo.vcDeptName=' + encodeURI('<@s.property value="searchpmBscDeptInfo.vcDeptName" escape="false"/>')
			+'&searchpmBscDeptInfo.pmBscAreaInfo.pid=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.pid" escape="false" default="0"/>'
			+'&searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=<@s.property value="searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode" escape="false" default="0"/>';
			$(document).ready(function() {
				
			});
			
			function editDeptType(pid,type,canceltype){
				if(type == 'edit'){
					var url = '${contextPath}/department/deptobserving/toEditDepType.action?pid='+pid+'&'+param;
        			document.location = url;
				}else if(type == 'del'){
					document.location = '${contextPath}/department/deptobserving/deleteDepType.action?pid='+pid+'&depid='+${pid}+'&'+param;
				}else if(type == 'view'){
					document.location = '${contextPath}/department/deptobserving/toViewDeptObserving.action?pid='+pid+'&canceltype='+canceltype+'&'+param;
				}
				
			}
			function add(pid){
				document.location = '${contextPath}/department/deptobserving/toAddDepType.action?pid='+pid +'&'+param;
			}
			function doCancel() {
				document.location = '${contextPath}/department/deptobserving/searchDepType.action?' +'&'+param;;
			}
		</script>
	</head>
	<body>
	<div class="iner_title_name"><strong>机构信息管理>>机构立功表彰>>机构立功表彰维护</strong></div>
	<#include "../../common/msg_panel.ftl" />
	<div class="iner_title_sub_03">
			<a href="javascript:add(${pid});" class="add" >新增</a>
			<a href="javascript:doCancel();" class="return" >返回</a>
		</div>
		<table width="100px" cellpadding="0" cellspacing="0" class="main_table" border="0">
			<tr>
				<th>序号</th>
				<th>地区</th>
				<th>机构名称</th>
				<th width="20%">机构性质</th>
				<th>机构职级</th>
				<th>立功表彰情况</th>
				<th>立功表彰时间</th>
				<th>联系电话</th>
				<th>操作</th>
			</tr>
			<@s.if test="pmbscobservinglist == null || pmbscobservinglist.size()<1">
				<tr align="center">
					<td colspan="10">
						暂无符合数据...
					</td>
				</tr>
			</@s.if>
			<@s.if test="pmbscobservinglist != null && pmbscobservinglist.size()>0">
				<#list pmbscobservinglist as pmbscobserving>
		            <tr>
		            	<td align="center">
		            		${pmbscobserving_index+1}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscAreaInfo.vcAreaName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.vcDeptName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscDeptType.vcTypeName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.pmBscDict.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscObserType.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.vcObservTime?string("yyyy-MM-dd")}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscobserving.pmBscDeptInfo.vcContactPhone!""}&nbsp;
		            	</td>
		            	<td align="center">
			            	<a href="javascript:editDeptType(${pmbscobserving.pid},'view','1');" class="see" >查看</a>
							<a href="javascript:editDeptType(${pmbscobserving.pid},'edit','');" class="edit" >修改</a>
							<a href="javascript:void(0)" onclick="javascript:divDel(function(){editDeptType(${pmbscobserving.pid},'del','')});" class="delete" >删除</a>
		            	</td>
		            </tr>
		        </#list>
			</@s.if>
		</table>
	</body>
</html>
