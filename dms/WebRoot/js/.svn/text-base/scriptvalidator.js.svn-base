Validator = {
	Require : /.+/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?1[0-9]\d{9}$/,
	PhoneOrMobile:/(^((\(\d{2,3}\))|(\d{3}\-))?1[0-9]\d{9}$)|(^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$)/,
	Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IdCard : "this.IsIdCard(value)",
	Currency : /^\d+(\.\d+)?$/,
	
	Money : /^\d+(\.\d{1,2})?$/,
	Number : /^\d+$/,
	Name :  /^[^\~\^\<\>\\\&\《\》]+$/,
	Name1 :  /^[^\~\^\<\>\\\$\《\》]+$/,
	Name2 : /^[0-9a-zA-Z\u4e00-\u9fa5_\-\(\)\[\]\{\}]+$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,20}$/,
	Integer : /^[-\+]?\d+$/,
	Double : /^[-\+]?\d+(\.\d+)?$/,
	PositiveDouble : /^\d+(\.\d+)?$/,
	NumberOrLetterOrZh : /^[0-9a-zA-Z\u4e00-\u9fa5]+$/,
	NumberOrLetter: /^[0-9a-zA-Z\-_]+$/,
	English : /^[A-Za-z]+$/,
	Chinese :  /^[\u0391-\uFFE5]+$/,
	Username : /^[a-z]\w{3,}$/i,
	CemUsername : /^[A-Za-z0-9_]+$/, //删除了.
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",
	Filter : "this.DoFilter(value, getAttribute('accept'))",
	Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",
	LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
	Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
	Range : "getAttribute('min') < ((value==null || value=='') ? 0 : parseFloat(value)) && ((value==null || value=='') ? 0 : parseFloat(value)) <= getAttribute('max')",
	StrictRange : "this.DoStrictRange(value, getAttribute('min'), getAttribute('max'), getAttribute('op1'), getAttribute('op2'), getAttribute('rel'))",
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Between : "this.compare(value, 'GreaterThanEqual', getAttribute('min')) && this.compare(value, 'LessThanEqual', getAttribute('max'))",
	Custom : "this.Exec(value, getAttribute('regexp'))",
	Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	ErrorItem : [document.forms[0]],
	ErrorMessage : ["以下原因导致提交失败：\t\t\t\t"],
	Validate : function(theForm, mode){
		var obj = theForm || event.srcElement;
		var count = obj.elements.length;
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		for(var i=0;i<count;i++){
			with(obj.elements[i]){
				var _dataType = getAttribute("dataType");

				if (typeof(_dataType) == 'object') continue;

				this.ClearState(obj.elements[i]);
				var _dataTypes = _dataType.split('|') ;
				var msgs = getAttribute("msg").split('|');
				for (var io = 0; io < _dataTypes.length; io++) {
					if(typeof(_dataTypes[io]) == "object" || typeof(this[_dataTypes[io]]) == "undefined")  continue;

					if(getAttribute("isTinyMCE") == "true") {
					   	if(tinyMCE) {
							tinyMCE.getInstanceById(name).select();
							value = tinyMCE.getContent(name);
						}
					}
					//trim
					if(getAttribute("trim") == "true") {
						value = value.replace(/^\s+/, '').replace(/\s+$/, '');
					}

					if(getAttribute("require") == "false" && value == "") continue;
					var hasError = false;
					switch(_dataTypes[io]){
						case "IdCard" :
						case "Date" :
						case "Repeat" :
						case "Range" :
						case "Compare" :
						case "Between" :
						case "Custom" :
						case "Group" :
						case "Limit" :
						case "LimitB" :
						case "SafeString" :
						case "Filter" :
						case "StrictRange" : 
							if(!eval(this[_dataTypes[io]]))	{
								if (io < msgs.length) {
								    this.AddError(i, msgs[io]);
								} else {
								    this.AddError(i, msgs[0]);
								}
								hasError = true;
							}
							break;
						default :
							if(!this[_dataTypes[io]].test(value)){
								if (io < msgs.length) {
									this.AddError(i, msgs[io]);
								} else {
								    this.AddError(i, msgs[0]);
								}
								hasError = true;
							}
							break;
					}
					if (hasError) {
						break;
					}	
				}
			}
		}
		if(this.ErrorMessage.length > 1){
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch(mode){
			case 2 :
				for(var i=1;i<errCount;i++)
					this.ErrorItem[i].style.color = "red";
			case 1 :
				alert(this.ErrorMessage.join("\n"));
				this.ErrorItem[1].focus();
				break;
			case 3 :
				for(var i=1;i<errCount;i++){
				try{
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.className = "ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = "<br>&nbsp;&nbsp;&nbsp;"+this.ErrorMessage[i].replace(/\d+:/,"");
					}
					catch(e){alert(e.description);}
				}
				if(this.ErrorItem[1].type=='hidden'){
					break;
				}
				this.ErrorItem[1].focus();
				break;
			default :
				alert(this.ErrorMessage.join("\n"));
				break;
			}
			return false;
		}
		return true;
	},
	limit : function(len,min, max){
		min = min || 0;
		max = max || Number.MAX_VALUE;
		return min <= len && len <= max;
	},
	LenB : function(str){
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	ClearState : function(elem){
		with(elem){
			if(style.color == "red")
				style.color = "";
			while(true) {
			   var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
			   if(lastNode.id == "__ErrorMessagePanel") {
				parentNode.removeChild(lastNode);
			   } else {
				break;
			   }
			}
		}
	},
	AddError : function(index, str){
		if (this.ErrorItem[this.ErrorItem.length - 1] != this.ErrorItem[0].elements[index] ||
		   this.ErrorMessage[this.ErrorMessage.length - 1] != (this.ErrorMessage.length - 1) +  ":" + str ) {
			this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
			this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
		}
	},
	Exec : function(op, reg){
		return new RegExp(reg,"g").test(op);
	},
	compare : function(op1,operator,op2){
		if (op1 == "") return true;
		
		if (op2.substring(0,1) == "$") {
			op2 = eval(op2);
		}
		switch (operator) {
			case "NotEqual":
				return (op1 != op2);
			case "GreaterThan":
				return (op1 > op2);
			case "GreaterThanEqual":
				return (op1 >= op2);
			case "LessThan":
				return (op1 < op2);
			case "LessThanEqual":
				return (op1 <= op2);
			default:
				return (op1 == op2);
		}
	},
	MustChecked : function(name, min, max){
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for(var i=groups.length-1;i>=0;i--)
			if(groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	DoFilter : function(input, filter){
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
	},
	IsIdCard : function(number){
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if(re == null) return false;
		if(re[1] >= area.length || area[re[1]] == "") return false;
		if(re[2].length == 12){
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else{
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if(!this.IsDate(date, "ymd")) return false;
		var sum = 0;
		for(var i = 0;i<=16;i++){
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai +=  verify.charAt(sum%11);
		return (number.length ==15 || number.length == 18 && number == Ai);
	},
	IsDate : function(op, formatString){
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch(formatString){
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if(m == null ) return false;
				day = m[6];
				month = m[5]*1;
				year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if(m == null ) return false;
				day = m[1];
				month = m[3]*1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if(!parseInt(month)) return false;
		month = month==0 ?12:month;
		var date = new Date(year, month-1, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	},
	DoStrictRange: function(val, min, max, op1, op2, rel) {
		var f1 = false, f2 = false, f = true;
		val = ((val==null || val=='') ? 0 : parseFloat(val));
		
		if(op1 == 'le') {
			f1 = (val <= min); 
		} else if(op1 == 'lt') {
			f1 = (val < min);
		} else if(op1 == 'ge') {
			f1 = (val >= min);
		} else if(op1 == 'gt') {
			f1 = (val > min);
		}
		
		if(op2 == 'le') {
			f2 = (val <= max); 
		} else if(op2 == 'lt') {
			f2 = (val < max);
		} else if(op2 == 'ge') {
			f2 = (val >= max);
		} else if(op2 == 'gt') {
			f2 = (val > max);
		}
		
		if(op1) { f = f1; }
		if(op2) {
			if(!rel || rel == '&&') {
				f = f && f2;
			} else if(rel == '||') {
				f = f || f2;
			}
		}
		return f;
	}
 }