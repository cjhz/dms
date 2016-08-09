<@s.if test="page != null && page.resultSet != null && !page.resultSet.isEmpty() && page.pageCount > 1">
	<div class="iner_title_page">
		<@s.if test="page.firstPageNum != null">
		
			<!--<a href="<@s.property value="#pageUrl" escape="false"/><@s.property value="formatDouble(page.firstPageNum)" />">首页</a>-->
			<a href="javascript:void(0);" onclick="formSubmit('<@s.property value="formatDouble(page.firstPageNum)" />');return false;">首页</a>
		</@s.if>
		<@s.else>
			首页
		</@s.else>
				
		<@s.if test="page.upPageNum != null">
			<!--<a href="<@s.property value="#pageUrl" escape="false"/><@s.property value="formatDouble(page.upPageNum)" /> ">上一页</a>-->
			<a href="javascript:void(0);" onclick="formSubmit('<@s.property value="formatDouble(page.upPageNum)" />');return false;">上一页</a>
		</@s.if>
		<@s.else>
			上一页
		</@s.else>
		(第<@s.property value="formatDouble(page.currentNum)" />页/共<@s.property value="formatDouble(page.pageCount)" />页 共<@s.property value="formatDouble(page.recordCount)" />条)
		<@s.if test="page.downPageNum != null">
			<!--<a href="<@s.property value="#pageUrl" escape="false"/><@s.property value="formatDouble(page.downPageNum)" />">下一页</a>-->	
			<a href="javascript:void(0);" onclick="formSubmit('<@s.property value="formatDouble(page.downPageNum)" />');return false;">下一页</a>
		</@s.if>
		<@s.else>
			下一页
		</@s.else>
				
		<@s.if test="page.endPageNum != null"> 
			<!--<a href="<@s.property value="#pageUrl" escape="false"/><@s.property value="formatDouble(page.endPageNum)" /> ">末页</a>-->
			<a href="javascript:void(0);" onclick="formSubmit('<@s.property value="formatDouble(page.endPageNum)" />');return false;">末页</a>
		</@s.if>
		<@s.else>
			末页
		</@s.else>
		跳转到<input type="text" size="2" class="page form_input" id="pageNumText" onkeypress="_pressEnter(event)" onkeyup="this.value=this.value.replace(/[\D]/g,'');"/>
	</div>
	<script type="text/javascript">
		function _jump() {
			var pageNumText = document.getElementById('pageNumText');
			if($.trim(pageNumText.value) == '' || isNaN(pageNumText.value)) {
				pageNumText.value = '';
			} else {
				var iNum = parseInt(pageNumText.value);
				if(iNum == <@s.property value="formatDouble(page.currentNum)" />) {
					return;	
				}
				
				if(iNum < 1) {
					iNum = 1;
				} else if(iNum > <@s.property value="formatDouble(page.pageCount)" />) {
					iNum = <@s.property value="formatDouble(page.pageCount)" />;
				}
				//var url = '<@s.property value="#pageUrl" escape="false"/>' + iNum;
				//document.location = url;	
				formSubmit(iNum);
			}
		}	
		
		function _pressEnter(evt) {
			var e = evt || window.event;
			var code = e.charCode || e.keyCode;
			if(code == 13) {  
  				_jump();
  			}
		}
	</script>
</@s.if>

