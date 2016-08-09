<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">	
	<head>
		<#include "header.ftl" />
	</head>
	<body>
		<#include "msg_panel.ftl">
		<div class="iner_title_name"><strong>欢迎使用<@s.text name="pm.project.name" /></strong></div>
		<div class="iner_title_sub_03">
			<strong>导航信息</strong>
		</div>
		<table cellpadding="0" cellspacing="0" align="left" id="tb1" style="margin-left:10%;">
		  <tr>
		    <td>
		    <table cellpadding="0" cellspacing="0" id="tb2" >
		      <tr>
		        <td class="pic1">
		        <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_1")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_1")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic2"></td>
		      </tr>
		      <tr>
		        <td class="pic1">
		        <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_2")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_2")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic2"></td>
		      </tr>
		      <tr>
		        <td class="pic1">
		        <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_3")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_3")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic2"></td>
		      </tr>
		      <tr>
		        <td class="pic1">
		        <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_4")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_4")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic2"></td>
		      </tr>
		      <tr>
		        <td class="pic1">
		        <a href="${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_5")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_5")}</a>
		        </td>
		      </tr>
		    </table>
		    </td>
		    <td width="312"><img src="${contextPath}/theme/${theme}/images/mid_line.jpg" alt="" /></td>
		    <td>
		    <table border="0" cellpadding="0" cellspacing="0" id="tb3" style="margin:0 0 80px 0;padding:0;">
		      <tr>
		        <td style="height:130px;" class="pic1">
		          <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_6")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_6")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		          <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_7")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_7")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		           <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_8")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_8")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		           <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_9")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_9")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		           <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_10")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_10")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		           <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_11")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_11")}</a>
		        </td>
		      </tr>
		      <tr>
		        <td class="pic1">
		           <a href="${contextPath}${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_URL_12")}">${stack.findValue("@com.sunraysoft.hr.constant.NaviConstant@NAVI_VAL_12")}</a>
		        </td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
	</body>
</html>
