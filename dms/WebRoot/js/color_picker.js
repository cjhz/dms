
var ColorHex = new Array("00", "33", "66", "99", "CC", "FF");
var SpColorHex = new Array("FF0000", "00FF00", "0000FF", "FFFF00", "00FFFF", "FF00FF");
var colorPicker;

function ColorPicker(okHandler, cancelHandler) {
	this.okHandler = okHandler;
	this.cancelHandler = cancelHandler;
}

ColorPicker.prototype.show = function() {

	var colorPanel = document.createElement("div");
    colorPanel.setAttribute('id', 'colorPanel');
    colorPanel.style.top = "30%";
    colorPanel.style.left = "40%";
    colorPanel.style.zIndex = "999";
    colorPanel.style.position = "absolute";
    document.body.appendChild(colorPanel);
    
    var colorTable = "";
	for (i = 0; i < 2; i++) {
		for (j = 0; j < 6; j++) {
			colorTable = colorTable + "<tr height=15>";
			colorTable = colorTable + "<td width=15 style=\"background-color:#000000\">";
			if (i == 0) {
				colorTable = colorTable + "<td width=15 style=\"cursor:pointer;background-color:#" + ColorHex[j] + ColorHex[j] + ColorHex[j] + "\" onclick=\"colorPicker.okHandler(this.style.backgroundColor)\">";
			} else {
				colorTable = colorTable + "<td width=15 style=\"cursor:pointer;background-color:#" + SpColorHex[j] + "\" onclick=\"colorPicker.okHandler(this.style.backgroundColor)\">";
			}
			colorTable = colorTable + "<td width=15 style=\"background-color:#000000\">";
			for (k = 0; k < 3; k++) {
				for (l = 0; l < 6; l++) {
					colorTable = colorTable + "<td width=15 style=\"cursor:pointer;background-color:#" + ColorHex[k + i * 3] + ColorHex[l] + ColorHex[j] + "\" onclick=\"colorPicker.okHandler(this.style.backgroundColor)\">";
				}
			}
		}
	}
	colorTable = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px #000000 solid;border-bottom:none;border-collapse: collapse;width:337px;\" bordercolor=\"000000\">" + "<tr height=20><td colspan=21 bgcolor=#ffffff style=\"font:12px tahoma;padding-left:2px;\">" + "<span style=\"float:left;color:#999999;\">\u989c\u8272\u9009\u62e9\u5668</span><span style=\"float:right;padding-right:3px;cursor:pointer;\" onclick=\"colorPicker.cancelHandler()\">\xd7\u5173\u95ed</span>" + "</td></table>" + "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse\" bordercolor=\"000000\" style=\"cursor:pointer;\">" + colorTable + "</table>";
	document.getElementById("colorPanel").innerHTML = colorTable;
	//var current_x = document.getElementById("inputcolor").offsetLeft;
	//var current_y = document.getElementById("inputcolor").offsetTop;
	//document.getElementById("colorPanel").style.left = current_x + "px";
	//document.getElementById("colorPanel").style.top = current_y + "px";	
}

ColorPicker.prototype.hide = function() {
	document.body.removeChild(document.getElementById("colorPanel"));
}

ColorPicker.prototype.parseColor = function(colorText) {
	if(colorText.indexOf('rgb') == 0 || colorText.indexOf('RGB') == 0) { //'rgb(0, 255, 153)
		var s = colorText.match(/[0-9]+/g);
		var t = '';
		if(parseInt(s[0]).toString(16).length == 1) {
			t = t + '0' + parseInt(s[0]).toString(16);
		} else {
			t += parseInt(s[0]).toString(16);
		}
		if(parseInt(s[1]).toString(16).length == 1) {
			t = t + '0' + parseInt(s[1]).toString(16);
		} else {
			t += parseInt(s[1]).toString(16);
		}
		if(parseInt(s[2]).toString(16).length == 1) {
			t = t + '0' + parseInt(s[2]).toString(16);
		} else {
			t += parseInt(s[2]).toString(16);
		}
		
		return t;
	} else if(colorText.indexOf('#') == 0) {
		colorText = colorText.replace(/#/g, '');
		return colorText;
	}
}
