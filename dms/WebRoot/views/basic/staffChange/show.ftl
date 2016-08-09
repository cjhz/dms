<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript">
        	var param = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false"/>') + '&pageNum=<@s.property value="pageNum" escape="false" />';
        	function query() {
        		document.location = '${contextPath}/basic/staffChange/chgstaff_page_query.action?' + param;
        	}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员调动管理>>人员调动查看>>查看</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staff/create.action'" />
		
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;table-layout:fixed;word-wrap:break-word;word-break:break-all">
			<tr>
			    <th colspan="4" style="text-align:center;"><strong>禁毒人员信息</strong> </th>
			  </tr>
			  <tr>
			    <th>人员属性：</th>
			    <td colspan="3">
			    <#list newdictprops as dict >
			    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
			    </#list>
			    </td>
			  </tr>
			  <tr>
			    <th>所属机构：</th>
			    <td colspan="3">
			    	<#list deptList as dept>
			      		${dept.pmBscDeptInfo.vcDeptName}<#if dept_has_next>;</#if>
			       </#list>&nbsp;
				</td>
			  </tr>
			  <tr>
			    <th>姓名：</th>
			    <td><@s.property value="staff.vcRealName" escape="false"/>&nbsp;</td>
			    <th>身份证号码：</th>
			    <td><@s.property value="staff.vcCardNo.substring(0,staff.vcCardNo.length()-4)+'XXXX'" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>出生日期：</th>
			    <td><@s.date name="staff.vcBirth" format="yyyy-MM-dd"/>&nbsp;</td>
			    <th>性别：</th>
			    <td>
			    <@s.if test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_1">
			    	<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_1"/>
			    </@s.if>
			    <@s.elseif test="staff.vcSex == @com.sunraysoft.hr.constant.BizConstant@SEX_KEY_2">
			    	<@s.property value="@com.sunraysoft.hr.constant.BizConstant@SEX_TXT_2"/>
			    </@s.elseif>
			    <@s.else>&nbsp;</@s.else>
			    </td>
			  </tr>
			  <tr>
			    <th>警号：</th>
			    <td><@s.property value="staff.vcAlarm" escape="false"/>&nbsp;</td>
			    <th>文化程度：</th>
			    <td><@s.property value="staff.dictCulturals.vcName" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>政治面貌：</th>
			    <td><@s.property value="staff.dictPoliticals.vcName" escape="false"/>&nbsp;</td>
			    <th>手机号码：</th>
			    <td><@s.property value="staff.vcMphone" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>办公外线：</th>
			    <td><@s.property value="staff.vcOphone" escape="false"/>&nbsp;</td>
			    <th>传真：</th>
			    <td><@s.property value="staff.vcFax" escape="false"/>&nbsp;</td>
			  </tr>
			  
			  <tr>
			    <th>工作调动情况</th>
			    <#if (changeList?size<1)>
			    <td colspan="3">
			    	无&nbsp;
				</td>
				<#else>
				<th colspan="3">&nbsp;</th>
				</#if>
			  </tr>
				<#list changeList as chginfo>
					<tr>
						<th>调动时间：</th>
						<td>${chginfo.vcChangeDate?string("yyyy-MM-dd")}&nbsp;</td>
						<th>调动情况：</th>
						<td>
						<#if chginfo.vcNowDeptName?exists&&chginfo.vcChgDeptName?exists>
							${chginfo.vcNowDeptName!""}-->${chginfo.vcChgDeptName!""}<br/>
						</#if>
						<#if chginfo.vcBpersonPropName?exists&&chginfo.vcEpersonPropName?exists>
							${chginfo.vcBpersonPropName!""}-->${chginfo.vcEpersonPropName!""}
						</#if>&nbsp;
						</td>
					</tr>
					<tr>
						<th>备注：</th>
						<td colspan="3">${chginfo.vcRemark!""}&nbsp;</td>
					</tr>
		    	</#list>
			  <tr>
			    <th colspan="4" style="text-align:center;">
			      <input type="button" onclick="query()" value="  返回  " />
			    </th>
			 </tr>
		</table>
	</body>
</html>
