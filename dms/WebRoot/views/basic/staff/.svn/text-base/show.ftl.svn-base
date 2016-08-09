<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
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
        	function modify(staffId) {
        		document.location = '${contextPath}/basic/staff/edit.action?' + param_ + '&staffId=' + staffId;
        	}
        	function query() {
        		document.location = '${contextPath}/basic/staff/pageQuery.action?' +param_;
        	}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员基础信息维护>>查看</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:modify(<@s.property value="staff.pid" escape="false"/>)" class="edit">编辑</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staff/create.action'" />
		
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			<tr>
			    <th colspan="4" style="text-align:center;"><strong>禁毒人员信息</strong> </th>
			  </tr>
			  <tr>
			    <th>人员属性：</th>
			    <td>
			    <#list newdictprops as dict >
			    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
			    </#list>&nbsp;
			    </td>
			    <th>所属机构：</th>
			    <td>&nbsp;
			    	<#list deptList as dept>
			      		${dept.pmBscDeptInfo.vcDeptName}<#if dept_has_next>;</#if>
			       </#list>
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
				  <th>参加禁毒工作时间</th>
				  <td><@s.date name="staff.vcDrugDate" format="yyyy-MM-dd"/>&nbsp;</td>
				  <td>&nbsp;</td>
				  <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>编制：</th>
			    <td colspan="3">
				  <#if formationChangelist?exists&&(formationChangelist?size>0)>
					  <#list formationChangelist as rankchange>
					  	  <#if rankchange_index+1==1>
						  	  <span style="width:140px;float:left">
						  	  <#if rankchange.vcNowDate?exists>${rankchange.vcNowDate?string('yyyy-MM-dd')}--</#if>
						  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}</#if>
						  	  </span>
						  	  <span style="float:left">${rankchange.dictNowRanks.vcName}</span><br/>
					  	  </#if>
					      <span style="width:140px;float:left">
					  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}--</#if>
					  	  <#if rankchange.vcEndDate?exists>${rankchange.vcEndDate?string('yyyy-MM-dd')}<#else>至今</#if>
					  	  </span>
					  	  <span style="float:left">${rankchange.dictChgRanks.vcName}</span><#if rankchange_has_next><br/></#if>
					  </#list>
					<#else>
					<@s.date name="staff.vcFormationDate" format="yyyy-MM-dd"/>-- 至今&nbsp;&nbsp;<@s.property value="staff.dictFormations.vcName" escape="false"/>
				  </#if>&nbsp;
			    </td>
			  </tr>
			  <tr>
			    <th>职级：</th>
			    <td colspan="3">
				  <#if rankChangelist?exists&&(rankChangelist?size>0)>
					  <#list rankChangelist as rankchange>
					  	  <#if rankchange_index+1==1>
						  	  <span style="width:140px;float:left">
						  	  <#if rankchange.vcNowDate?exists>${rankchange.vcNowDate?string('yyyy-MM-dd')}--</#if>
						  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}</#if>
						  	  </span>
						  	  <span style="float:left">
							  	  <#if rankchange.dictNowRanks?exists>${rankchange.dictNowRanks.vcName}</#if>
						  	  </span><br/>
					  	  </#if>
					  	  <span style="width:140px;float:left">
					  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}--</#if>
					  	  <#if rankchange.vcEndDate?exists>${rankchange.vcEndDate?string('yyyy-MM-dd')}<#else>至今</#if>
					  	  </span>
					  	  <span style="float:left">${rankchange.dictChgRanks.vcName}</span><#if rankchange_has_next><br/></#if>
					  </#list>
				    <#else>
					<@s.date name="staff.vcRankDate" format="yyyy-MM-dd"/>-- 至今&nbsp;&nbsp;<@s.property value="staff.dictRanks.vcName" escape="false"/>
				  </#if>&nbsp;
			    </td>
			  </tr>
			  <tr>
			    <th>职务：</th>
			    <td colspan="3">
				  <#if jobChangelist?exists&&(jobChangelist?size>0)>
					  <#list jobChangelist as rankchange>
					      <#if rankchange_index+1==1>
						  	  <span style="width:140px;float:left">
						  	  <#if rankchange.vcNowDate?exists>${rankchange.vcNowDate?string('yyyy-MM-dd')}--</#if>
						  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}</#if>
						  	  </span>
						  	  <span style="float:left">${rankchange.dictNowRanks.vcName}</span><br/>
					  	  </#if>
					      <span style="width:140px;float:left">
					  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}--</#if>
					  	  <#if rankchange.vcEndDate?exists>${rankchange.vcEndDate?string('yyyy-MM-dd')}<#else>至今</#if>
					  	  </span>
						  <span style="float:left">${rankchange.dictChgRanks.vcName}</span><#if rankchange_has_next><br/></#if>
					  </#list>
				   <#else>
					<@s.date name="staff.vcJobDate" format="yyyy-MM-dd"/>-- 至今&nbsp;&nbsp;<@s.property value="staff.dictJobs.vcName" escape="false"/>
				  </#if>&nbsp;
			    </td>
			  </tr>
			  <tr>
			    <th>工作分工：</th>
			    <td colspan="3">
				  <#if workChangelist?exists&&(workChangelist?size>0)>
					  <#list workChangelist as rankchange>
					      <#if rankchange_index+1==1>
						  	  <span style="width:140px;float:left">
						  	  <#if rankchange.vcNowDate?exists>${rankchange.vcNowDate?string('yyyy-MM-dd')}--</#if>
						  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}</#if>
						  	  </span>
						  	  <span style="float:left">${rankchange.vcExtend1!""}</span><br/>
					  	  </#if>
					      <span style="width:140px;float:left">
					  	  <#if rankchange.vcChgDate?exists>${rankchange.vcChgDate?string('yyyy-MM-dd')}--</#if>
					  	  <#if rankchange.vcEndDate?exists>${rankchange.vcEndDate?string('yyyy-MM-dd')}<#else>至今</#if>
					  	  </span>
					  	  <span style="float:left">${rankchange.vcExtend2!""}</span><#if rankchange_has_next><br/></#if>
					  </#list>
				   <#else>
					<@s.date name="staff.vcWorkDate" format="yyyy-MM-dd"/>-- 至今&nbsp;&nbsp;<@s.property value="staff.vcExtend1" escape="false"/>
				  </#if>&nbsp;
			    </td>
			  </tr>
			 
			  <tr>
			    <th>工作调动情况：</th>
			    <td colspan="3">
			    	<#if (changeList?exists)&&(changeList?size>0)>
			    	<#list changeList as chginfo>
			    		${chginfo.vcChangeDate?string('yyyy-MM-dd')}&nbsp;&nbsp;&nbsp;
			    		${chginfo.vcNowDeptName!""}-->${chginfo.vcChgDeptName!""}&nbsp;&nbsp;&nbsp;&nbsp;
			    		${chginfo.vcBpersonPropName!""}-->${chginfo.vcEpersonPropName!""}
			    	<#if chginfo_has_next><br/></#if>
			    	</#list>
			    	<#else>无
			    	</#if>
			    	&nbsp;
				</td>
			  </tr>
			  <tr>
			    <th>立功、表彰情况：</th>
			    <td colspan="3">
			    	<#if (obserStaffList?exists)&&(obserStaffList?size>0)>
					<#list obserStaffList as obser>
			    	${obser.vcObservTime?string('yyyy-MM-dd')}&nbsp;&nbsp;&nbsp;${obser.pmBscObserType.vcName!""}
			    	<#if obser_has_next><br/></#if>
			    	</#list>
			    	<#else>无
			    	</#if>
			    	&nbsp;
				</td>
			  </tr>
			  <tr>
			    <th>备注：</th>
			    <td colspan="3"><@s.property value="staff.vcRemark" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th colspan="4" style="text-align:center;">
			      <input type="button" onclick="query()" value="  返回  " />
			    </th>
			 </tr>
		</table>
	</body>
</html>
