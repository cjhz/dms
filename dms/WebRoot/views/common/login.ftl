<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="${contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${contextPath}/js/jquery.capsLock.js"></script>
		<script type="text/javascript" src="${contextPath}/js/String.js"></script>
		<link href="${contextPath}/theme/${theme}/css/login.css" rel="stylesheet" type="text/css" />
		<title><@s.text name="pm.project.name" /></title>
		<script type="text/javascript">
			function formSubmit() {
				if(trim($('#vcLoginName').val()) == '') {
					$('#vcLoginName').focus();
					$('#msg').addClass('login_error_box');
					$('#msg').text('用户名不能为空');
					return;
				}
				if($('#vcLoginPwd').val() == '') {
					$('#vcLoginPwd').focus();
					$('#msg').addClass('login_error_box');
					$('#msg').text('密码不能为空');
					return;
				}
				if(trim($('#authCode').val()) == '') {
					$('#authCode').focus();
					$('#msg').addClass('login_error_box');
					$('#msg').text('验证码不能为空');
					return;
				}
			
				$('#msg').removeClass('login_error_box');
				$('#msg').addClass('login_info_box');
				$('#msg').text('正在登录...');
				
				$('form').ajaxSubmit({
					url: '${contextPath}/common/ajaxLogin.action',
					type: 'post',
					dataType: 'json',
					success: function(data) {
						$('#msg').text(data.msg);
						if(data.flag) {
							document.location.href = '${contextPath}/common/mainPage.action';
						} else {
							$('#msg').removeClass('login_info_box');			
							$('#msg').addClass('login_error_box');
							$('#authCodeImg').attr('src', '${contextPath}/servlet/AuthCodeServlet?r=' + new Date().getTime());
						}
					}
				});
			}
			$(document).ready(function() {
				if(top.location != self.location) {
					top.location = self.location;
				}
				$('#vcLoginName').focus();
				$('#ok').click(formSubmit);
				$('#cancel').click(function() {
					$('form')[0].reset();
				});
				$('#authCodeImg').click(function() {
					$('#authCodeImg').attr('src', '${contextPath}/servlet/AuthCodeServlet?r=' + new Date().getTime());
				});
				$('#loginName').keydown(function (evt){
					if(evt.keyCode == 13) {
						$('#passwordMd5').focus();
					}
				});
				$('#passwordMd5').keydown(function (evt){
					if(evt.keyCode == 13) {
						$('#authCode').focus();
					}
				});
				$('#authCode').keydown(function (evt){
					if(evt.keyCode == 13) {
						formSubmit();
					}
				});
				var options = {
					caps_lock_on: function() { 
						$("#caps_lock").text("大写锁定已打开");			
						$('#caps_lock').addClass('login_error_box');				
					},
					caps_lock_off: function() { 
						$("#caps_lock").text(""); 
						$('#caps_lock').removeClass('login_error_box');
					},
					debug: false
				};
				$("#vcLoginPwd").focus(function(){
					$("#vcLoginPwd").capslock(options);
				}).blur(function(){
					$("#caps_lock").text(""); 
					$('#caps_lock').removeClass('login_error_box');
				});
				
			});
		</script>
	</head>
	<body scroll="no">
		<form>
			<input type="text" style="display:none" value="此处的input删掉然后回车按钮就会触发提交" />
			<div class="top"><@s.text name="pm.project.name" /></div>
			<div class="login">
				<div class="login_form">
				     <ul>
						<li class="name"><input type="text" class="login_form_input" name="pmbsclogininfo.vcLoginName" id="vcLoginName"></li>
						<li class="password"><input type="password" class="login_form_input" name="pmbsclogininfo.vcLoginPwd" id="vcLoginPwd"></li>
						<li class="code"><input type="text" class="code_input" name="pmbsclogininfo.authCode" id="authCode"><img src="${contextPath}/servlet/AuthCodeServlet" id="authCodeImg" title="点击刷新" alt="验证码"></li>
						<li class="button"><input type="button" class="botton" value="　确定　" id="ok">　<input type="button" class="botton"  value="　重置　" id="cancel"></li>
					 </ul>
				</div>
			</div>
			<div class="bottom"><@s.text name="pm.project.name" /></div>
			<div id="msg"></div>
			<div id="caps_lock"></div>
		</form>
	</body>
</html>
