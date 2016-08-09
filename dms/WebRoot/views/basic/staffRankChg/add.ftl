<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'staffsearch.vcRealName=' + encodeURI('<@s.property value="staffsearch.vcRealName" escape="false" default=""/>')
			+ '&pageNum=<@s.property value="pageNum" escape="false" />'
			+'&staffsearch.vcWorkDivision=<@s.property value="staffsearch.vcWorkDivision" escape="false" default=""/>'
			+'&staffsearch.dictRanks.pid=<@s.property value="staffsearch.dictRanks.pid" escape="false" default="0"/>'
			+'&staffsearch.vcCardNo=<@s.property value="staffsearch.vcCardNo" escape="false" default="0"/>'
			+'&staffsearch.dictCulturals.pid=<@s.property value="staffsearch.dictCulturals.pid" escape="false" default="0"/>'
			+'&staffsearch.vcSex=<@s.property value="staffsearch.vcSex" escape="false" default="0"/>'
			+'&staffsearch.isShowAll=<@s.property value="staffsearch.isShowAll" escape="false" default="0"/>'
			+'&staffsearch.bbirth=<@s.property value="staffsearch.bbirth" escape="false" default=""/>'
			+'&staffsearch.ebirth=<@s.property value="staffsearch.ebirth" escape="false" default=""/>'
			+'&staffsearch.vcOphone=<@s.property value="staffsearch.vcOphone" escape="false" default=""/>'
			+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&staff.pid=<@s.property value="staff.pid" escape="false"/>';
        
        	function query(type) {
        		document.location = '${contextPath}/basic/staffRankChg/pageQueryRankChg.action?' + param+'&search.vcType='+type;
        	}
        	
			function formSubmit() {
				if($("#nowRank").val()==$("#chgRank").val()){
					alert("调动后${typeName}不能与原来相同！");
					return;
				}else{
					var staffChangeForm = document.getElementById('staffChangeForm');
					if(!Validator.Validate(staffChangeForm, 3)) {
						return false;
					}
					staffChangeForm.submit();
				}
			}

        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员信息管理>>人员属性变动>>${typeName}变动>>个人${typeName}变动</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query(${search.vcType});" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staffRankChg/create.action'" />
		<form id="staffChangeForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
		<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
		<!--<input type="hidden" name="staffRank.pmBscStaffInfo.pid"  value="<@s.property value="staffsearch.pid" escape="false"/>"  />-->
		<input type="hidden" name="staff.pid"  value="<@s.property value="staff.pid" escape="false"/>"  />
		<input type="hidden" name="staffRank.pid"  value="<@s.property value="chgid" escape="false"/>"  />
		<input type="hidden" name="staffRank.vcType"  value="<@s.property value="search.vcType" escape="false"/>"  />
		<input type="hidden" name="search.vcType"  value="<@s.property value="search.vcType" escape="false"/>"  />
		<input type="hidden" name="staffsearch.vcOphone" value="<@s.property value="staffsearch.vcOphone" escape="false"/>" />
		<input type="hidden" id="areadisplay" name="dialogAreaDisplay" value="${dialogAreaDisplay}" />
		<input type="hidden" name="pageNum" value="<@s.property value="pageNum" escape="false"/>" />
		<div>
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			 <tr>
			     <th colspan="4" style="text-align:center;"><strong>禁毒人员${typeName}调动</strong> </th>
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
			  <#if isedit>
			    <th>调动前${typeName}：</th>
			    <td>
			    <input type="hidden" name="staffRank.dictNowRanks.pid" id="nowRank" value="<@s.property value="staffRank.dictNowRanks.pid" escape="false"/>" />
			    <@s.property value="staffRank.dictNowRanks.vcName" escape="false"/>&nbsp;
			    </td>
			    <th>任前${typeName}时间：</th>
			    <td>
			    <input type="hidden" name="staffRank.vcNowDate"  value="<@s.date name="staffRank.vcNowDate" format="yyyy-MM-dd"/>" />
			    <@s.date name="staffRank.vcNowDate" format="yyyy-MM-dd"/>&nbsp;
			    </td>
			    <#else>
			    	<#if search.vcType==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_1")>
					    <th>现${typeName}：</th>
					    <td>
					    <input type="hidden" name="staffRank.dictNowRanks.pid" id="nowRank" value="<@s.property value="staff.dictRanks.pid" escape="false"/>" />
					    <@s.property value="staff.dictRanks.vcName" escape="false"/>&nbsp;
					    </td>
					    <th>现任${typeName}时间：</th>
					    <td>
					    <input type="hidden" name="staffRank.vcNowDate"  value="<@s.date name="staff.vcRankDate" format="yyyy-MM-dd"/>" />
					    <@s.date name="staff.vcRankDate" format="yyyy-MM-dd"/>&nbsp;
					    </td>
					<#elseif search.vcType==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_2")>
					    <th>现${typeName}：</th>
					    <td>
					    <input type="hidden" name="staffRank.dictNowRanks.pid" id="nowRank" value="<@s.property value="staff.dictJobs.pid" escape="false"/>" />
					    <@s.property value="staff.dictJobs.vcName" escape="false"/>&nbsp;
					    </td>
					    <th>现任${typeName}时间：</th>
					    <td>
					    <input type="hidden" name="staffRank.vcNowDate"  value="<@s.date name="staff.vcJobDate" format="yyyy-MM-dd"/>" />
					    <@s.date name="staff.vcJobDate" format="yyyy-MM-dd"/>&nbsp;
					    </td>
					<#elseif search.vcType==stack.findValue("@com.sunraysoft.hr.constant.BizConstant@STAFF_ATTR_TYPE_KEY_3")>
					    <th>现${typeName}：</th>
					    <td>
					    <input type="hidden" name="staffRank.dictNowRanks.pid" id="nowRank" value="<@s.property value="staff.dictFormations.pid" escape="false"/>" />
					    <@s.property value="staff.dictFormations.vcName" escape="false"/>&nbsp;
					    </td>
					    <th>现任${typeName}时间：</th>
					    <td>
					    <input type="hidden" name="staffRank.vcNowDate"  value="<@s.date name="staff.vcFormationDate" format="yyyy-MM-dd"/>" />
					    <@s.date name="staff.vcFormationDate" format="yyyy-MM-dd"/>&nbsp;
					    </td>
					
			    	</#if>
			    </#if>
			  
			  </tr>
			  <tr>
			  
			  	<th>调动后${typeName}：</th>
			  	<#--<#if isedit>
				    <td>
				    	<@s.property value="staffRank.dictChgRanks.vcName" escape="false"/>&nbsp;
				     </td>
			  		<#else>-->
				    <td>
				    	<select class="add_input form_select" name="staffRank.dictChgRanks.pid" id="chgRank">
				         <#list dictRanks as dict>
					        <option value=${dict.pid} <@s.if test="${dict.pid}==staffRank.dictChgRanks.pid">selected</@s.if>>${dict.vcName}</option>
					     </#list>
				       </select>&nbsp;
				     </td>
			     <#--</#if>-->
			     
			    <th>调动时间：</th>
			    <td><input class="form_input" type="text" style="width:200px;margin-left:3px" name="staffRank.vcChgDate" id="vcChgDate" value="<@s.date name="staffRank.vcChgDate" format="yyyy-MM-dd"/>" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择调动时间" onclick="displayCalendar('',document.getElementById('vcChgDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
			  </tr>
			  <tr>
			    <th>备注：</th>
			    <td colspan="3">
					<textarea name="staffRank.vcRemark" id="vcRemark" cols="80" rows="5" require="false" trim="true" class="add_textarea2" width="75%" onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="staffRank.vcRemark" escape="false" /></textarea></td>
				</td>
			  </tr>
				<tr>
					<th>&nbsp;</th>
					<td colspan="5" >
							<font id="remLen1">&nbsp;</font>
							<font color=red id="remLen2">&nbsp;</font>
							<font id="remLen3">&nbsp;</font>
							<font color=red id="remLen4">&nbsp;</font>
							<font id="remLen5">&nbsp;</font>&nbsp;
					</td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>
				    <input type="button" onclick="formSubmit()" value="  保存  " />
				</td>
			    <td>
				    <input type="button" onclick="query(${search.vcType})" value="  返回  " />
			    </td>
			    <td>&nbsp;</td>
			  </tr>
		</table>
		</div>
		</form>
		
	</body>
</html>
<#include "../../common/footer.ftl" />