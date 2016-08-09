<div id="innerMenuContainer">
<@s.property value="innerMenu" escape="false"/>
</div>
<div class="error_prompt" id="message_hint" <@s.if test="msg == null || msg.length() == 0">style="display:none;"</@s.if>>
	<a href="javascript:closeMsgPanel()">关闭</a>
	<@s.if test="success">
		<span id="inner_msg">
			<@s.property value="msg" escape="false" />
		</span>	
	</@s.if>
	<@s.else>
		<span id="inner_msg">
			<@s.property value="msg" escape="false" />
		</span>
	</@s.else>
</div>
<@s.if test="showWarning()">
	<div style="color:red;"><strong>此处操作非常重要，将影响系统正常运行，请勿操作！！！</strong></div>
</@s.if>	

