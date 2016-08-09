<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<script type="text/javascript" src="${contextPath}/js/business/staff.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRealName=' + encodeURI('<@s.property value="search.vcRealName" escape="false"/>')
				+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&search.vcOphone=<@s.property value="search.vcOphone" />&pageNum=<@s.property value="pageNum" escape="false" />';
        	
        	function modify(staffId) {
        		document.location = '${contextPath}/basic/staff/edit.action?' + param + '&staffId=' + staffId;
        	}
        	function query() {
        		document.location = '${contextPath}/basic/staffChange/pageQuery.action?' + param;
        	}
        	
        	$(function(){
				$('#selBtn,#deptNames').dialog({ 
					lock: true,
					left:'50%',
					top:'50%',
					width:'500px',
					height:'300px',
					title:'机关选择',
					content:'url:${contextPath}/basic/staff/dialog.action?allparam=1'
				});
				
				$('#canBtn').click(function(){
					$('#deptIds').val('');
					$('#deptNames').val('');
				});
			});
			
			function formSubmit() {
				$("#roleNameMsg").html("");
				getChkVal('personProp');
				trimB();
				var retn = getChkHtm('personProp','personPropName');
				if(retn!=false&&compareif()!=false){
					var staffChangeForm = document.getElementById('staffChangeForm');
					if(!Validator.Validate(staffChangeForm, 3)) {
						return false;
					}
					
					var personProp = $("#personProp").val();
					var staffid = $("#staffid").val();
					var deptIds = $("#deptIds").val();
					var url = '${contextPath}/basic/staffChange/ajaxCompareChangeUnique.action';
					$.post(url, {'staffchange.vcEpersonProp': personProp, 'staffchange.pmBscStaffInfo.pid': staffid, 'staffchange.vcChgDept':deptIds}, function(jsonObj) {
						if(jsonObj.timeout) {
							timeoutRedirect('${contextPath}');
						} else if(!jsonObj.flag){
							$('#roleNameMsg').css('color', 'red');	
							$('#roleNameMsg').html(jsonObj.msg);
							return false;
						}else{
							staffChangeForm.submit();
						}
					}, 'json');
					
				}else if(retn==false){
					alert("请选择人员属性调动！");
				}
			}
			
			function formSubmit2() {
					var staffChangeForm = document.getElementById('staffChangeForm');
					staffChangeForm.submit();
			}
			
			function compareif(){
				var bprop = intercept($.trim($("#vcBpersonProp").val()));
				var eprop = intercept($.trim($("#personProp").val()));
				var bdept = intercept($.trim($("#vcNowDept").val()));
				var edept = intercept($.trim($("#deptIds").val()));
				if(bprop==eprop&&bdept==edept){
					alert("没有任何调动，请修改！");
					return false;
				}
			}
			function intercept(arg){
				var len = arg.split("").length-1;
				var last = arg.lastIndexOf(";")
				if(last==len){
					return arg.substring(0,last);
				}else{
					return arg;
				}
				
			}
			function trimB(){
				$("#vcBpersonProp").val($.trim($("#vcBpersonProp").val()));
				$("#vcBpersonPropName").val($.trim($("#vcBpersonPropName").val()));
				$("#vcNowDept").val($.trim($("#vcNowDept").val()));
				$("#vcNowDeptName").val($.trim($("#vcNowDeptName").val()));
			}
			function radioCheckedChange(type){
				if(type == 0){
					$("#staffbutton").val(" 保存 ");
					$("#staffvcTransferNode").attr("style","display:none;");
					$("#staffbutton").attr('onclick', '').unbind('click').click(
						function updateStaffDeleteFlag(flagvalue){
							formSubmit();
						}
					);
					$("#stafftype").attr("style","display:inline;");
					$("#staffdept").attr("style","display:inline;");
					$("#stafftime").attr("style","display:inline;");
					$("#staffremark").attr("style","display:inline;");
				}else{
					if(type == 2){
						$("#staffbutton").val(" 调离 ");
						$("#staffvcTransferNode").removeAttr("style");
						$("#staffbutton").attr('onclick', '').unbind('click').click(
								function updateStaffDeleteFlag(flagvalue){
									formSubmit2();
								}
						);
						$("#staffChangeForm").removeAttr("action").attr("action","${contextPath}/basic/staffChange/updateStaffDelFlag.action");
					}else{
						$("#staffbutton").val(" 离退休 ");
						$("#staffvcTransferNode").attr("style","display:none;");
						$("#staffbutton").attr('onclick', '').unbind('click').click(
								function updateStaffDeleteFlag(flagvalue){
									updateFlag(type);
								}
						);
					}
					$("#stafftype").attr("style","display:none;");
					$("#staffdept").attr("style","display:none;");
					$("#stafftime").attr("style","display:none;");
					$("#staffremark").attr("style","display:none;");
				}

			}
			function updateFlag(flagvalue){
				var vcTransferNode = $("#vcTransferNode").val();
				document.location = '${contextPath}/basic/staffChange/updateStaffDelFlag.action?staff.pid=${staff.pid}&staff.vcDeleteFlag='+flagvalue+'&staff.vcTransferNode='+encodeURI(vcTransferNode)+'&dialogAreaDisplay='+${dialogAreaDisplay}+'&search.vcOphone=<@s.property value="search.vcOphone" />&pageNum=<@s.property value="pageNum" escape="false" />';
			}
        </script>
	</head>
	<body>
		<div class="iner_title_name"><strong>人员信息管理>>人员调动管理>>人员调动>>调动</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="iner_title_sub_03">
			<a href="javascript:query();" class="return" >返回</a>
		</div>
		<@s.set name="formUrl" value="'/basic/staffChange/create.action'" />
		<form id="staffChangeForm" method="post" action="${contextPath}<@s.property value="#formUrl" escape="false"/>">
		<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
		<input type="hidden" id="staffid" name="staffchange.pmBscStaffInfo.pid"  value="<@s.property value="staff.pid" escape="false"/>"  />
		<input type="hidden" name="dialogAreaDisplay"  value="<@s.property value="dialogAreaDisplay" escape="false"/>"  />
		<input type="hidden" name="search.vcOphone" id="departmentid" value="<@s.property value="search.vcOphone" />" />
		<input type="hidden" name="staff.pid" value="<@s.property value="staff.pid" />" />
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			 <tr>
			     <th colspan="4" style="text-align:center;"><strong>禁毒人员调动</strong> </th>
			  </tr>
			   <tr>
			    <th width="25%">调动类型：</th>
			    <td width="75%" colspan="3" >
			    	<input name="staff.vcDeleteFlag" type="radio" 
			    	<@s.if test="staff.vcDeleteFlag == @com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_KEY_1">checked="checked"</@s.if>
			    	 value="0" onclick="radioCheckedChange(this.value);" />
			    		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_VAL_1"/>
			    	<input name="staff.vcDeleteFlag" type="radio" 
			    	<@s.if test="staff.vcDeleteFlag == @com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_KEY_2">checked="checked"</@s.if>
			    	value="2" onclick="radioCheckedChange(this.value);" />
			    		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_VAL_2"/>
			    	<input name="staff.vcDeleteFlag" type="radio" 
			    	<@s.if test="staff.vcDeleteFlag == @com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_KEY_3">checked="checked"</@s.if>
			    	value="3" onclick="radioCheckedChange(this.value);" />
			    		<@s.property value="@com.sunraysoft.hr.constant.BizConstant@STAFF_CHANGE_TYPE_VAL_3"/>
			    </td>
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
			    <th>职级：</th>
			    <td><@s.property value="staff.dictRanks.vcName" escape="false"/>&nbsp;</td>
			  </tr>
			  <tr>
			    <th>人员属性：</th>
			    <td colspan="3">
				    <#list newdictprops as dict >
				    	${dict.vcName!""}<#if dict_has_next>,</#if>&nbsp;
				    </#list>
				    <#assign x>
				    	<#list newdictprops as dict >${dict.pid}<#if dict_has_next>;</#if></#list>
				    </#assign>
				    <#assign xx>
				    	<#list newdictprops as dict >${dict.vcName!""}<#if dict_has_next>;</#if></#list>
				    </#assign>
				    <input type="hidden" name="staffchange.vcBpersonProp" id="vcBpersonProp" value="${x}"  />
				    <input type="hidden" name="staffchange.vcBpersonPropName" id="vcBpersonPropName" value="${xx}"  />
			    </td>
			  </tr>
			  <tr id="stafftype">
			    <th>人员属性调动为：</th>
			    <td colspan="3">
					<#list dictprops as dict>
						<input type="checkbox" name="personProp_${dict_index+1}" id="personProp_${dict_index+1}" value="${dict.pid}" propname="${dict.vcName}"/>${dict.vcName}&nbsp;
			        </#list>
			        <input type="hidden" name="staffchange.vcEpersonProp" id="personProp" value=""  />
			        <input type="hidden" name="staffchange.vcEpersonPropName" id="personPropName" value=""  />
				</td>
			  </tr>
			  <tr>
			    <th>现所在机构：</th>
			    <td colspan="3">
			    	<#list staff.pmBscStaffDepts  as staffDept >
	                	${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if>
	                </#list>&nbsp;
	                <#assign y>
				    	<#list staff.pmBscStaffDepts  as staffDept >${staffDept.pmBscDeptInfo.pid}<#if staffDept_has_next>;</#if></#list>
	                </#assign>
	                <#assign yy>
				    	<#list staff.pmBscStaffDepts  as staffDept >${staffDept.pmBscDeptInfo.vcDeptName}<#if staffDept_has_next>;</#if></#list>
	                </#assign>
	                <input type="hidden" name="staffchange.vcNowDept" id="vcNowDept" value="${y}"  />
	                <input type="hidden" name="staffchange.vcNowDeptName" id="vcNowDeptName" value="${yy}"  />
				</td>
			  </tr>
			  <tr id="staffdept">
			    <th>所在机构调动为：</th>
			    <td colspan="3">
				    <input  type="hidden" name="staffchange.vcChgDept" id="deptIds" value=""  require="true" dataType="Require"  trim="true" msg="请选择调动所在机构"/>
				    <input class="add_input2 form_input" type="text" name="staffchange.vcChgDeptName" id="deptNames" value="" readonly="true"/>
				    <input type="button" name="selBtn" id="selBtn" value="  选择  " />
		      		<input type="button" name="canBtn" id="canBtn" value="  清除  " />
	      		</td>
			  </tr>
			  <tr id="stafftime">
			    <th>调动时间：</th>
			    <td colspan="2"><input class="form_input" type="text" style="width:200px;margin-left:3px" name="staffchange.vcChangeDate" id="vcChangeDate" value="" dataType="Date" require="true" dataType="Require"  trim="true" msg="请选择调动时间" onclick="displayCalendar('',document.getElementById('vcChangeDate'),'yyyy-mm-dd', this)" readonly="true"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr id="staffremark">
			    <th>备注：</th>
			    <td colspan="3">
					<textarea name="staffchange.vcRemark" id="vcRemark" cols="80" rows="5" require="false" trim="true" class="add_textarea2" width="75%"  onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="staffchange.vcRemark" escape="false" /></textarea></td>
				</td>
			  </tr>
			  <tr id="staffvcTransferNode" style="display:none;">
			    <th>调离备注：</th>
			    <td colspan="3">
					<textarea name="staff.vcTransferNode" id="vcTransferNode" cols="80" rows="5" require="false" trim="true" class="add_textarea2" width="75%"  onKeyUp="testTextareaLength(this,1000)" onBlur="testLeaveTextareaLength(this,1000)"><@s.property value="staffchange.vcTransferNode" escape="false" /></textarea></td>
				</td>
			  </tr>
				<tr>
					<th>&nbsp;</th>
					<td colspan="3" >
							<font id="remLen1">&nbsp;</font>
							<font color=red id="remLen2">&nbsp;</font>
							<font id="remLen3">&nbsp;</font>
							<font color=red id="remLen4">&nbsp;</font>
							<font id="remLen5">&nbsp;</font>&nbsp;
							<span id="roleNameMsg"></span>
					</td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>
				    <input id="staffbutton" type="button" onclick="formSubmit()" value=" 保存  " />
				</td>
			    <td>
				    <input type="button" onclick="query()" value=" 返回  " />
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <br>
		</table>
		</form>
	</body>
</html>
<#include "../../common/footer.ftl" />