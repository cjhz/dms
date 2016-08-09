<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" contect="no-cache">
<title><@s.text name="pm.project.name" /></title>
<link href="${contextPath}/theme/${theme}/css/inner.css" type="text/css" rel="stylesheet" />
<link href="${contextPath}/theme/${theme}/css/reset.css" type="text/css" rel="stylesheet" />
<link href="${contextPath}/theme/${theme}/css/dialog.css" type="text/css" rel="stylesheet" />
<link href="${contextPath}/theme/${theme}/css/dhtmlgoodies_calendar.css" type="text/css" rel="stylesheet" />
<link href="${contextPath}/js/dhtmlxColorPicker/dhtmlxcolorpicker.css" type="text/css" rel="stylesheet" />
		
<script type="text/javascript" src="${contextPath}/js/scriptvalidator.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${contextPath}/js/trimpath-template-1.0.38.js"></script>
<script type="text/javascript" src="${contextPath}/js/common.js"></script>
<script type="text/javascript" src="${contextPath}/js/color_picker.js"></script>
<script type="text/javascript" src="${contextPath}/js/dialog.js"></script>
<script type="text/javascript" src="${contextPath}/js/dhtmlxColorPicker/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${contextPath}/js/dhtmlgoodies_calendar.js"></script>
<script type="text/javascript" src="${contextPath}/js/dhtmlxColorPicker/dhtmlxcolorpicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/lhgdialog/lhgdialog.min.js?skin=iblack"></script>
<script type="text/javascript" src="${contextPath}/js/department.js"></script>
<script type="text/javascript" src="${contextPath}/js/export.js"></script>

<script type="text/javascript" >
	$(document).ready(function() {
		if(document.getElementById('search_btn')) {
			$('#search_btn').click(conditionSwitch);
			if(<@s.property value="showAdvancedQueryPanel" />) {
				_clickCount = 1;
				showAdvancedPanel();
			}	
		}
	});
</script>
<input type="hidden" id="areaDepartmentUrl" value="${contextPath}/basic/area/findByLevel.action"/>