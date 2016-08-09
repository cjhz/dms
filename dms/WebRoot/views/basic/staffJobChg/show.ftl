<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript">
        	var param = 'staffsearch.vcRealName=' + encodeURI('<@s.property value="staffsearch.vcRealName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&staffsearch.vcWorkDivision=<@s.property value="staffsearch.vcWorkDivision" escape="false" default=""/>'
			+'&staffsearch.dictRanks.pid=<@s.property value="staffsearch.dictRanks.pid" escape="false" default="0"/>'
			+'&staffsearch.vcCardNo=<@s.property value="staffsearch.vcCardNo" escape="false" default="0"/>'
			+'&staffsearch.dictCulturals.pid=<@s.property value="staffsearch.dictCulturals.pid" escape="false" default="0"/>'
			+'&staffsearch.vcSex=<@s.property value="staffsearch.vcSex" escape="false" default="0"/>'
			+'&staffsearch.bbirth=<@s.property value="staffsearch.bbirth" escape="false" default=""/>'
			+'&staffsearch.ebirth=<@s.property value="staffsearch.ebirth" escape="false" default=""/>'
			+'&staffsearch.vcOphone=<@s.property value="staffsearch.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&staff.pid=<@s.property value="staff.pid" escape="false"/>';
        
        	function modify(staffId) {
        		document.location = '${contextPath}/basic/staff/edit.action?' + param + '&staffId=' + staffId;
        	}
        	function query() {
        		document.location = '${contextPath}/basic/staffJobChg/pageQueryJobChg.action?' + param;
        	}

        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动>>工作分工调整>>个人工作分工调整查看</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staffJobChg/create.action'" />
		<form id="staffChangeForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
		<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
		<!--<input type="hidden" name="staffRank.pmBscStaffInfo.pid"  value="<@s.property value="staffsearch.pid" escape="false"/>"  />-->
		<input type="hidden" name="staff.pid"  value="<@s.property value="staff.pid" escape="false"/>"  />
		<input type="hidden" name="staffJob.pid"  value="<@s.property value="chgid" escape="false"/>"  />
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			 <tr>
			     <th colspan="4" style="text-align:center;"><strong>禁毒人员工作分工调整</strong> </th>
			  </tr>
			  <tr>
			  	<th>现所在机构：</th>
			  	<td colspan="3">
			  		<#list staff.pmBscStaffDepts as staffDept>
			  			${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
			  		</#list>
			  	&nbsp;</td>
			  </tr>
			  <tr>
			    <th width="25%">姓名：</th>
			    <td width="25%"><@s.property value="staff.vcRealName" escape="false"/>&nbsp;</td>
			    <th width="25%">警号：</th>
			    <td width="25%"><@s.property value="staff.vcAlarm" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>身份证号码：</th>
			    <td><@s.property value="staff.vcCardNo.substring(0,staff.vcCardNo.length()-4)+'XXXX'" escape="false"/>&nbsp;</td>
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
			    <th>职务：</th>
			    <td><@s.property value="staff.dictJobs.vcName" escape="false"/>&nbsp;</td>
			    <th>人员属性：</th>
			    <td>
				    <#list newdictprops as dict >
				    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
				    </#list>&nbsp;
			    </td>
			  </tr>
			  <tr>
			    <th>调动前工作分工：</th>
			    <td>
			    <@s.property value="staffJob.vcExtend1" escape="false"/>&nbsp;
			    </td>
			    <th>任前工作分工时间：</th>
			    <td>
			    <@s.date name="staffJob.vcNowDate" format="yyyy-MM-dd"/>&nbsp;
			    </td>
			  </tr>
			  <tr>
			  
			  	<th>调动后工作分工：</th>
			    <td>
			    	<@s.property value="staffJob.vcExtend2" escape="false"/>&nbsp;
			     </td>
		     
			    <th>调动时间：</th>
			    <td><@s.date name="staffJob.vcChgDate" format="yyyy-MM-dd"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>备注：</th>
			    <td colspan="3">
					<@s.property value="staffJob.vcRemark" escape="false" />&nbsp;
				</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td colspan="2">
				    <input type="button" onclick="query()" value="  返回  " />
			    </td>
			    <td>&nbsp;</td>
			  </tr>
		</table>
		</form>
	</body>
</html>
<#include "../../common/footer.ftl" />