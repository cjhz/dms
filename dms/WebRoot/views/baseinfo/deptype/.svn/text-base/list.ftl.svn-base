<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title><@s.text name="pm.project.name" />>>代码维护>>机构性质</title>
		<script type="text/javascript">
			function editDeptType(pid,type){
				if(type == 'edit'){
					var url = '${contextPath}/baseinfo/deptype/toEditDepType.action?pid='+pid;
        			document.location = url;
				}
				if(type == 'del'){
					document.location = '${contextPath}/baseinfo/deptype/deleteDepType.action?pid='+pid;
				}
			}
			
			function addDeptType(){
				document.location = '${contextPath}/baseinfo/deptype/toAddDepType.action';
			}
			
		</script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>代码维护>>机构性质</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:addDeptType();" class="edit">新增</a>
		</div>
		<table width="100px" cellpadding="0" cellspacing="0" class="main_table" border="0">
			<tr>
				<th>序号</th>
				<th>机构性质</th>
				<th>机构属性</th>
				<th>操作</th>
			</tr>
			<@s.if test="pmbscdeptypelist == null || pmbscdeptypelist.size()<1">
				<tr align="center">
					<td colspan="4">
						暂无符合数据...
					</td>
				</tr>
			</@s.if>
			<@s.if test="pmbscdeptypelist != null && pmbscdeptypelist.size()>0">
				<#list pmbscdeptypelist as pmbscdeptype>
		            <tr>
		            	<td align="center">
		            		${pmbscdeptype_index+1}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptype.vcTypeName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmbscdeptype.vcDeptProp!""}&nbsp;
		            	</td>
		            	<td align="center">&nbsp;
		            		<a href="javascript:editDeptType(${pmbscdeptype.pid},'edit');" class="edit" >修改</a>
							<a href="javascript:void(0)" onclick="javascript:divDel(function(){editDeptType(${pmbscdeptype.pid},'del')});" class="delete" >删除</a>
		            	</td>
		            </tr>
		        </#list>
			</@s.if>
		</table>
	</body>
</html>
