<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "../../common/header.ftl" />
		<link href="${contextPath}/theme/${theme}/css/ltree.checkbox.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${contextPath}/js/ltree.packed.js"></script>
		<script type="text/javascript">
			var param = 'search.vcRoleName=' + encodeURI('<@s.property value="search.vcRoleName" escape="false"/>')
        		+ '&pageNum=<@s.property value="pageNum" escape="false" />';
        	function doCancel() {
        		var url = '${contextPath}/permission/role/pageQuery.action?' + param;
        		document.location = url;
        	}
			function chkAll(){
				var a=this.parentNode.getElementsByTagName("input"),b=this.checked;
				for(var i=0,l=a.length; i<l;i++) {
					 a[i].checked=b;
				}
			}
			function chkOne() {
				var a=this,b=this.checked,p=lDOM.$("treeContainer"),pn=null;
				while(a.tagName!="DD" && a!=p) a=a.parentNode;
				pn=a.getElementsByTagName("input");
				if(b){
					for(var i=1,l=pn.length; i<l;i++) {
						if(!pn[i].checked){
							b=false;
							break;
						}
					}
				}
				pn[0].checked=b;
			}
			
			$(document).ready(function() {
				var lTree = new lTREE();
				lTree.config({path: "dl dd"});
				lTree.tagName({ folder: "DD", file: "DT"});
				lTree.className({folderClose: "folderClose", lastChild: "lastChild"});
				lTree.build("treeContainer");
				var arrCHK=lDOM.find("dl dd input",lDOM.$("treeContainer"),{type:"checkbox"});
				arrCHK.each(function(s){
					if(s.parentNode.tagName=="DT"){ 
						s.onclick=chkOne; 
					} else {
						s.onclick=chkAll;
					}
				});
				lTree.changAll(0);
			});
			
			function ok() {
				var roleId = <@s.property value="roleId" escape="false"/>;
				var array = [];
				$("input[level='5']:checked").each(function() {
					array.push($(this).val());
				});
				
				var operIds = array.join(',');
				var url = '${contextPath}/permission/role/persistRoleOperPair.action';
				$.post(url, {'roleId': roleId, 'operIds': operIds}, function(jsonObj) {
					if(jsonObj.timeout) {
						timeoutRedirect('${contextPath}');
					} else {
						showMsgPanel(jsonObj.flag, jsonObj.msg);
					}
				}, 'json');
			}
		</script>
	</head>
	<body>
		<div class="iner_title_name"><strong>系统管理>>角色管理>>权限分配</strong></div>
		<#include "../../common/msg_panel.ftl" />
		<div class="lTREEMenu lTREENormal" id="treeContainer" style="width:500px;border:1px solid #ccc;margin:3px;padding:3px;height:350px;overflow:auto;" >
			<@s.property value="jsonText" escape="false" />
		</div>
		
		<table cellpadding="0" cellspacing="0" class="form_table" style="margin-bottom:10px;">
			<tr>
				<td>
					<input  type="button" value="　保存　" onclick="ok()"/>
					<input  type="button" value="　返回　" onclick="doCancel()"/>
				</td>
			</tr>　
		</table>
	</body>
</html>
