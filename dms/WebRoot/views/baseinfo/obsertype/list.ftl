<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title><@s.text name="pm.project.name" />>>代码维护>>立功表彰</title>
		<script type="text/javascript">
			function editDeptType(pid,type){
				if(type == 'edit'){
					document.location = '${contextPath}/baseinfo/obsertype/toEditDepType.action?pid='+pid+'&vctype='+${vctype};
				}
				if(type == 'del'){
					document.location = '${contextPath}/baseinfo/obsertype/deleteDepType.action?pid='+pid+'&vctype='+${vctype};
				}
			}
			function addDeptType(){
				document.location = '${contextPath}/baseinfo/obsertype/toAddDepType.action?vctype='+${vctype};
			}
		</script>
	</head>
	<body>
	<div class="iner_title_name">
	<strong>系统管理>>代码维护>><@s.if test="vctype == 1">机构立功表彰</@s.if>
	<@s.if test="vctype != 1">人员立功表彰</@s.if></strong></div>
	<#include "../../common/msg_panel.ftl" />
	<div class="iner_title_sub_03">
			<a href="javascript:addDeptType();" class="edit">新增</a>
		</div>
			<table cellpadding="0" cellspacing="0" class="main_table" border="0">
				<tr>
					<th>序号</th>
					<th>${titlename}</th>
					<th>操作</th>
				</tr>
				<@s.if test="pmbscobsertypelist == null || pmbscobsertypelist.size()<1">
					<tr align="center">
						<td colspan="3">
							暂无符合数据...
						</td>
					</tr>
				</@s.if>
				<@s.if test="pmbscobsertypelist != null && pmbscobsertypelist.size()>0">
					<#list pmbscobsertypelist as pmbscobsertype> 
			            <tr>
			            	<td align="center">
			            		${pmbscobsertype_index+1}&nbsp;
			            	</td>
			            	<td align="center">
			            		${pmbscobsertype.vcName!""}&nbsp;
			            	</td>
			            	<td align="center">&nbsp;
			            		<a href="javascript:editDeptType(${pmbscobsertype.pid},'edit');" class="edit" >修改</a>
								<a href="javascript:void(0)" onclick="javascript:divDel(function(){editDeptType(${pmbscobsertype.pid},'del')});" class="delete" >删除</a>
			            	</td>
			            </tr>
			         </#list>
				</@s.if>
			</table>
	</body>
</html>
