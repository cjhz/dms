<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" contect="no-cache">
		<#include "../../common/header.ftl"/>
		<#include "../../common/delPage.ftl" />
		<title><@s.text name="pm.project.name" /></title>
		<script type="text/javascript">
			var param = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false"/>')
						+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&search.vcOphone='+'<@s.property value="search.vcOphone"  escape="false"/>'+'&search.isShowAll='+'<@s.property value="search.isShowAll"  escape="false"/>';
			function editDeptType(staffid,pid,type){
				if(type == 'edit'){
					document.location = '${contextPath}/basic/staffobserving/toEdit.action?pid='+pid+'&staffId='+staffid + '&' + param;
				}else if(type == 'del'){
					document.location = '${contextPath}/basic/staffobserving/destory.action?pid='+pid+'&staffId='+staffid+ '&' + param;
				}else if(type == 'view'){
					document.location = '${contextPath}/basic/staffobserving/show.action?pid='+pid+'&staffId='+staffid+'&viewType=1' + '&' + param;
				}
			}
			function add(staffId){
				document.location = '${contextPath}/basic/staffobserving/toAdd.action?staffId=' + staffId + '&' + param;
			}
			function doCancel() {
				document.location = '${contextPath}/basic/staffobserving/pageQuery.action?'+param;
			}
		</script>
	</head>
	<body>
	<div class="iner_title_name"><strong>人员信息管理>>人员立功表彰管理>>人员立功表彰维护</strong></div>
	<#include "../../common/msg_panel.ftl" />
	<div class="iner_title_sub_03">
		<a href="javascript:add('${staffId}');" class="add" >新增</a>
		<a href="javascript:doCancel();" class="return" >返回</a>
	</div>
		<table cellpadding="0" cellspacing="0" class="main_table">
			<tr>
				<th>序号</th>
				<th>所属机构</th>
				<th>姓名</th>
				<th>职务</th>
				<th>性别</th>
				<th>职级</th>
                <th>立功表彰情况</th> 
                <th>立功表彰时间</th>
                <th>联系电话</th>
                <th>操作</th>
			</tr>
				<@s.if test="pmBscObservingStafflist == null || pmBscObservingStafflist.size()<1">
				<tr align="center">
					<td colspan="10">
						暂无符合数据...
					</td>
				</tr>
			</@s.if>
			<@s.if test="pmBscObservingStafflist != null && pmBscObservingStafflist.size()>0">
				<#list pmBscObservingStafflist as pmBscObservingStaff>
		            <tr>
		            	<td align="center">
		            		${pmBscObservingStaff_index+1}&nbsp;
		            	</td>
		            	<td align="center">
		            		 <#list pmBscObservingStaff.pmBscStaffInfo.pmBscStaffDepts  as staffDept >
			                	${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
			                </#list>&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmBscObservingStaff.pmBscStaffInfo.vcRealName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmBscObservingStaff.pmBscStaffInfo.dictJobs.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		<@s.if test="${pmBscObservingStaff.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">
		            		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/></@s.if>
	               			<@s.if test="${pmBscObservingStaff.pmBscStaffInfo.vcSex!0}==@com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">
	               			<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/></@s.if>
		            	</td>
		            	<td align="center">
		            		${pmBscObservingStaff.pmBscStaffInfo.dictRanks.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmBscObservingStaff.pmBscObserType.vcName!""}&nbsp;
		            	</td>
		            	<td align="center">
		            		${pmBscObservingStaff.vcObservTime?string("yyyy-MM-dd")}&nbsp;
		            	</td>
		            	<td align="center">
		            		<@s.if test="${pmBscObservingStaff.pmBscStaffInfo.vcOphone}!=''">
		            			${pmBscObservingStaff.pmBscStaffInfo.vcOphone!""}
		            		</@s.if>
		            		<@s.else>${pmBscObservingStaff.pmBscStaffInfo.vcMphone!""}</@s.else>
		            		&nbsp;
		            	</td>
		            	<td align="center">
		            		&nbsp;
		            		<a href="javascript:editDeptType('${pmBscObservingStaff.pmBscStaffInfo.pid}','${pmBscObservingStaff.pid}','view');" class="see" >查看</a>
		            		<a href="javascript:editDeptType('${pmBscObservingStaff.pmBscStaffInfo.pid}','${pmBscObservingStaff.pid}','edit');" class="edit" >修改</a>
							<a href="javascript:void(0)" onclick="javascript:divDel(function(){editDeptType('${pmBscObservingStaff.pmBscStaffInfo.pid}','${pmBscObservingStaff.pid}','del')});" class="delete" >删除</a>
			            	
		            	</td>
		            </tr>
		         </#list>
			</@s.if>
		</table>
	</body>
</html>
