    /*
     * Statement: 返回去掉前后空格的字符串
     * @param:      str
     * return     返回去掉前后空格的字符串
     * @Author:   YinTao
     */
    function trim(str)
    {
        if (str==null || str=='' || str==' ') {return '';}
        var Char='';
        do{
            Char=str.substring(0,1);
            if (Char==' ')
            {str=str.substring(1);}
            else
            {break;}
        }while(true)
        do{
            Char=str.substring(str.length-1,str.length);
            if (Char==' ')
            {str=str.substring(0,str.length-1);}
            else
            {break;}
        }while(true)
        return str;
    }

    /*
     * Statement: 判断文本框当前填写内容是否为数值 返回true 正确，返回false失败
     * @param:      strTemp         需要校验的字符串
     * return void
     * @Author:   YinTao
     */
    function IsNumber(strTemp){
        var iLeg=strTemp.length;            // 字符串长度
        var tChar='';                       // 每个字符
        var iQty=0;                         // 小数点个数“.”
        var iQty_=0;                        // 减号个数“-”
        var i;

        if(iLeg>0){
            for (i=1;i<=iLeg;i++){
                tChar=strTemp.substring(i-1,i);
                if(tChar=='.') iQty++;
                if(tChar=='-') iQty_++;

                // 有非数字字符
                if((tChar>'9' || tChar<'0') && tChar!='.' && tChar!='-') return false;
            }
            if(iQty>1) return false;        // 多个小数点

            // 不正常小数点（“.” 在开头）
            if(iQty==1 && strTemp.substring(0,1)=='.') return false;
            if(iQty_>=1)  return false;     // 多个负号“-”

            // 符号在数字中间，错误
            if(iQty_==1 && strTemp.substring(0,1)!="-") return false;
        }
        return true;
    }

    /*
     * Statement: 判断文本框当前填写内容是否为正数 返回true 正确，返回false失败
     * @param:      strTemp         需要校验的字符串
     * return void
     * @Author:   YinTao
     */
    function IsPlusNumber(strTemp){
        var iLeg=strTemp.length;            // 字符串长度
        var tChar='';                       // 每个字符
        var iQty=0;                         // 小数点个数“.”
        var i;
        if(iLeg>0){
            for (i=1;i<=iLeg;i++){
                tChar=strTemp.substring(i-1,i);
                if(tChar=='.') iQty++;

                // 有非数字字符
                if((tChar>'9' || tChar<'0') && tChar!='.') return false;
            }
            if(iQty>1) return false;        // 多个小数点

            // 不正常小数点（“.” 在开头）
            if(iQty==1 && strTemp.substring(0,1)=='.') return false;
        }
        return true;
    }

    /*
     * Statement: 判断文本框当前填写内容是否为整数 返回true 正确，返回false失败
     * @param:    strTemp         需要校验的字符串
     * return void
     * @Author:   YinTao
     */
    function IsInt(strTemp){
        var iLeg=strTemp.length;            // 字符串长度
        var tChar='';                       // 每个字符
        var i;
        if(iLeg>0){
            for (i=1;i<=iLeg;i++){
                tChar=strTemp.substring(i-1,i);

                // 有非数字字符
                if((tChar>'9' || tChar<'0')) return false;
            }
            return true;
        }else{
            return false;
        }
        
    }

    /*
     * Statement: 计算给定字符串的长度
     * @param:    strTemp         需要校验的字符串
     * return   int
     * @Author:   YinTao
     */
    function getLength(strTemp){
        var strT = trim(strTemp);
        return strT.replace(/[^\x00-\xff]/g, "**").length;
    }

    /*
     * Statement: 判断给定字符串是否包含全角字符
     * @param:    strTemp         需要校验的字符串
     * return     boolean         true=有汉字或全角字符;false=全部是半角字符
     * @Author:   YinTao
     */
    function checkChina(strTemp){
        if(getLength(strTemp)>trim(strTemp).length) return true;
        else false;
    }

    /*
     * 点击计算按钮后，计算当前备注内容的长度。
     *
     * return void
     * @Author:   YinTao
     */
    function calculate(val){
        var iCount = getLength(val);
        alert("字符长度为:"+iCount);
    }

    /*
     * 判断给定的字符串szVal的长度是否超出显示长度iLength；
     * 如果超出显示长度，则把显示长度范围内的最后的3个字符用"..."超链接表示，并在鼠标浮动时显示完整描述；
     * 如果"最后3个字符中"有汉字时，截取完整字符。
     *
     * @param szVal           String          完整字符描述
     * @param iLength         int             可以显示的长度
     * return String
     * @Author:   YinTao
     */
    function ShowDiv(szVal, iLength){
        var szShow = "";                     // 显示的字符串
        var szEnd = "";
        var szChar = "";
        var iCharLen = 0;
        var iShowLen = 0;
        if(szVal==null || trim(szVal)=="") return "";
        if(iLength<=3) return trim(szVal);

        // 校验字符长度是否符合显示长度
        if(getLength(szVal)>iLength){
            for(var i=0; i<trim(szVal).length; i++){
                szChar = trim(szVal).substring(i,i+1);
                iShowLen += getLength(szChar); 
                if(iShowLen>=(iLength-3)){
                    szShow = szVal.substring(0,i);
                    szEnd = szVal.substring(i,szVal.length);
                    break;
                }
            }

            // 如果超长，则截取页面显示部分,组合页面显示的信息结果字符串
            szShow = szShow+"<font color='blue' onmouseover=\"showDesc(this,'"+szEnd+"')\" onmouseout=\"HideDesc()\">...</font>";
        }else{
            szShow = trim(szVal);
        }
        return szShow;
    }

    /*
     * Statement: 校验必须输入整数的文本框
     * @param:      doc             文档对象
     * @param:      nam             文本框的名称
     * @param:      val             文本框值
     * return void
     * @Author:   YinTao
     */
    function txt_up(doc,nam,val){
        if(!IsInt(val)){
            eval(doc+"."+nam).value="";
            return false;
        }
        return true;
    }

    /*
     * Statement: 校验必须输入正数的文本框
     * @param:      doc             文档对象
     * @param:      nam             文本框的名称
     * @param:      val             文本框值
     * return void
     * @Author:   YinTao
     */
    function txt_num(doc,nam,val){
        if(!IsPlusNumber(val)){
            eval(doc+"."+nam).value="";
            return false;
        }
        return true;
    }

    /*
     * Statement: 校验必须输入正数的文本框
     * @param:      o            文本框控件对象
     * return void
     * @Author:   YinTao
     */
    function _txt_up(o){
        if(!IsPlusNumber(o.value)){
            o.value="";
            return false;
        }
        return true;
    }

    /*
     * Statement: 校验必须输入正数的文本框
     * @param:      o            文本框控件对象
     * return void
     * @Author:   YinTao
     */
    function _txt_num(o){
        if(!IsPlusNumber(o.value)){
            o.value="";
            return false;
        }
        return true;
    }

    /**
    * Script Name: openWin()
    * Function:    打开新窗口
    * @param       url     打开的链接地址
    * @param       id      窗口标志
    * @param       width   打开的窗口的宽度
    * @param       height  打开的窗口的高度
    * @param       left    打开的窗口的左边距
    * @param       top     打开的窗口的上边距
    * @return      void
    * Note         当left和top为空时，窗口显示在屏幕的中央
     * @Author:   YinTao
    */
    function openWin(url,id,width,height,left,top){
        var config;
        config = "toolbar=yes,menubar=yes";
        if(width!=null)
            config += ",width="+width;
        if(height!=null)
            config += ",height="+height;
        if(left!=null&&left!="")
            config += ",left="+left;
        if(top!=null&&top!="")
            config += ",top="+top;
        if(width!=null&&height!=null&&left==null&&top==null){
            config += ",left="+(window.screen.availWidth-width)/2;
            config += ",top="+(window.screen.availHeight-height)/2;
        }
            config += ",resizable=yes,status=yes, scrollbars=yes";
        window.open(url,"",config);
    }

    /**
    * Script Name: _openWin()
    * Function:    打开新窗口(没有滚动条，不可改变大小，没有状态条，没有工具栏)
    * @param       url     打开的链接地址
    * @param       id      窗口标志
    * @param       width   打开的窗口的宽度
    * @param       height  打开的窗口的高度
    * @param       left    打开的窗口的左边距
    * @param       top     打开的窗口的上边距
    * @return      void
    * Note         当left和top为空时，窗口显示在屏幕的中央
     * @Author:   YinTao
    */
    function _openWin(url,id,width,height,left,top){
        var config;
        config = "toolbar=yes,menubar=yes";
        if(width!=null)
            config += ",width="+width;
        if(height!=null)
            config += ",height="+height;
        if(left!=null&&left!="")
            config += ",left="+left;
        if(top!=null&&top!="")
            config += ",top="+top;
        if(width!=null&&height!=null&&left==null&&top==null){
            config += ",left="+(window.screen.availWidth-width)/2;
            config += ",top="+(window.screen.availHeight-height)/2;
        }
            config += ",resizable=no,status=no;scrollbars=no,toolbar=no,menubar=no,";
        window.open(url,"",config);
    }

    /* 
     * Statement: 比较指定2个日期的大小
     * @param:      date1       第一个日期（格式：YYYY-MM-DD）
     * @param:      date2       第二个日期（格式：YYYY-MM-DD）
     * return       date1>date2返回1,date1==date2返回0,date1<date2返回-1; -999=异常
     * @Author:   YinTao
     */
    function DateThen(date1,date2){
        if(trim(date1)=="" || trim(date1).length!=10) return -999;
        if(trim(date2)=="" || trim(date2).length!=10) return -999;
        if(trim(date1)==trim(date2)) return 0;
        var d1 = replaceAll(date1,"-","");;
        var d2 = replaceAll(date2,"-","");;
        if(parseInt(d1)>parseInt(d2))      return 1;
        else if(parseInt(d1)<parseInt(d2)) return -1;
        else                               return 0;
    }


    /*
     * Statement: 返回替换完毕的字符串
     * @param:      str         源字符串
     * @param:      dit         被替换的字符串
     * @param:      rec         新字符串（用字符串rec替换字符串dit）
     * return     返回去掉前后空格的字符串
     * @Author:   YinTao
     */
    function replaceAll(str,dit,rec){
        if(str==null || str=="" || trim(dit=="")) return str;
        while(str.indexOf(dit)!=-1){
            str = str.replace(dit,rec);
        }
        return str;
    }

    /*
     * Statement: 校验并转换回车换行字符
     * @param:    strTemp         需要校验的字符串
     * return   int
     * @Author:   YinTao
     */
    function covEnter(strTemp){
        var cur = "";
        var result = strTemp;
        if(strTemp!=null){
            result = "";
            for(var i=0; i<strTemp.length; i++){
                cur = strTemp.substring(i,i+1);
                if(cur=="\n"){
                    cur = "\\n";
                }
                result += cur;
            }
        }
        return result;
    }


    /***********************************************************
    第一个参数是待格式化的数值，第二个是保留小数位数
    注意：返回的是字符串类型
    第一个函数需要调用第二个函数，所以第二个不能去掉
    ***********************************************************/
    function formatKeep(value,num){ //四舍五入
        var a_str = breakStr(value,num);
        var a_int = parseFloat(a_str);
        if (value.toString().length>a_str.length)    {
            var b_str = value.toString().substring(a_str.length,a_str.length+1)
            var b_int = parseInt(b_str);
            if (b_int<5)        {
                return a_str
            }else{
                var bonus_str,bonus_int;
                if (num==0){
                    return (parseInt(a_str)+1).toString();
                }else{
                    a_str = a_str.substring(0,a_str.indexOf('.'))+a_str.substring(a_str.indexOf('.')+1,a_str.length);
                    a_int = parseInt(a_str)+1;
                    a_str = a_int.toString();
                    a_str = a_str.substring(0,a_str.length-num)+"."+a_str.substring(a_str.length-num,a_str.length);
                    return a_str;
                }
            }
        }
        return a_str
    }
    function breakStr(value,num){ //直接去尾
        var a,b,c,i
        a = value.toString();
        b = a.indexOf('.');
        c = a.length;
        if (num==0){
            if (b!=-1)
                a = a.substring(0,b);
        }else{
            if (b==-1){
                a = a + ".";
                for (i=1;i<=num;i++) a = a + "0";
            }else{
                a = a.substring(0,b+num+1);
                for (i=c;i<=b+num;i++) a = a + "0";
            }
        }
        return a
    }

    /*
     * Statement: 检查浮点数的整数位是否超过指定长度
     * @param:      str          浮点数的字符串
     * @param:      length       整数位的长度
     * return       如果超长或不是数字就返回false,否则返回true
     * @Author:   YinTao
     */
    function checkFloatLength(str, length)
    {
        if(!IsPlusNumber(str)) return false;
        var le = trim(str).indexOf(".");
        var strlen = trim(str).length;
        if(le == "-1")
        {        
            if(strlen > length)
                return false;
        }
        if(le > length)
            return false;
        return true;
    }
    
     /*
     * Statement: 校验不能输入中文的文本框
     * @param:      doc             文档对象
     * @param:      nam             文本框的名称
     * @param:      val             文本框值
     * return void
     * @Author:   YinTao
     */
    function txt_nochina(doc,nam,val){
        if(checkChina(val)){
            eval(doc+"."+nam).value="";
            return false;
        }
        return true;
    }

     /*
     * Statement: 即使判断并提示输入字符个数
     * @param:      obj             判断的控件
     * @param:      length          最大字符个数
     * @param:      remLen1,remLen2,remLen3,remLen4,remLen5         显示Font
     * return void
     * @Author:   YinTao
     */ 
   function checkMaxInput(sth,length){
	   var obj=sth;
	   if(zo_replace(obj.value)!=obj.value){
		   obj.value = zo_replace(obj.value);
		   }	
		while (fucCheckLength(obj)>length)
		{
			obj.value=obj.value.substring(0,obj.value.length-1);
		}	
       var n=fucCheckLength(obj);
       if(n>=length){
            remLen1.innerText='[最大可输入';
            remLen2.innerText=length;
            remLen3.innerText='字符，';
            remLen4.innerText='您已达到输入上限！';
            remLen5.innerText=']';
        }else{
            remLen1.innerText='[最大可输入';
            remLen2.innerText=length;
            remLen3.innerText='字符，还可输入';
            remLen4.innerText=length-n;
            remLen5.innerText='字符]';
        }
   }
    function checkMaxInputDic(obj,length){
 	   if(zo_replace(obj.value)!=obj.value){
 		   obj.value = zo_replace(obj.value);
 	   }
       var m=obj.value.length;
       var n=m;
       var j=0;
       for (var i=0;i<m;i++){
          if (obj.value.charCodeAt(i)<0||obj.value.charCodeAt(i)>161){
            n=n+1;
            if ( i <= length ){
               j=j+1;
            }
          }
       }
       if(n>length){
            obj.value=obj.value.substring(0,length-j);
            remLe1.innerText='[最大可输入';
            remLe2.innerText=length;
            remLe3.innerText='字符，';
            remLe4.innerText='您已达到输入上限！';
            remLe5.innerText=']';
        }else{
            remLe1.innerText='[最大可输入';
            remLe2.innerText=length;
            remLe3.innerText='字符，还可输入';
            remLe4.innerText=length-n;
            remLe5.innerText='字符]';
        }
   }
     /*
     * Statement: 清空显示Font（配合上面的checkMaxInput()使用）
     * @Author:   YinTao
     */ 
     function clsInput(obj){
//        var kan_keys="%|*|&|#|'|\"";
//		var kan_sp=kan_keys.split("|")
//		var pin=0;
//		var sp="";
//		obj.value =obj.value.replace(/%/g, "").replace(/\*/g, "").replace(/\&/g, "").replace(/#/g, "").replace(/\'/g, "").replace(/\"/g, ""); 
//		winodw.alert(obj.value+"111");
//		for (i=0;i<kan_sp.length;i++){
//			if((obj.value).indexOf(kan_sp[i])>=0){ 
//					pin=1;
//					sp=kan_sp[i];
//					break;
//			}
//		}
//		if(pin == 1){
//			window.alert("您输入了非法字符\""+sp+"\"，请重新输入");
//			obj.select();
//		}else{
//		}
    	obj.fireEvent('onKeyUp');
        remLen1.innerText=' ';
        remLen2.innerText=' ';
        remLen3.innerText=' ';
        remLen4.innerText=' ';
        remLen5.innerText=' ';
     }
      function clsInputDic(obj){
        var kan_keys="%|*|&|#|'|\"";
		var kan_sp=kan_keys.split("|")
		var pin=0;
		var sp="";
		for (i=0;i<kan_sp.length;i++){
			if((obj.value).indexOf(kan_sp[i])>=0){ 
					pin=1;
					sp=kan_sp[i];
					break;
			}
		}
		if(pin == 1){
			window.alert("您输入了非法字符\""+sp+"\"，请重新输入");
			obj.select();
		}else{
		}
        remLe1.innerText=' ';
        remLe2.innerText=' ';
        remLe3.innerText=' ';
        remLe4.innerText=' ';
        remLe5.innerText=' ';
     }
     

    /*
     * Statement: 判断是否符合正则表达式
     * @param:      reg             正则表达试
     * @param:      str             要判断的字符串
     * return:      boolean
     * @Author:   YinTao
     */ 
     function Check( reg, str ){
  if( reg.test( str ) ){
   return true;
  }
   return false;
 }
 
  /*
     * Statement: 判断Email是否符合法
     * @param:      obj             要判断的控件
     * return:      void
     * @Author:   YinTao
     */ 
 function checkEmail(obj){
  if(obj.value=="")return;
  var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
  if(""!=obj.value){
   if(Check( reg, obj.value )){
    clsInput();
   }else{
    alert("您输入的E-Mail地址有错误!\r\n格式如：abcd@126.com");
    obj.focus();
    obj.select();
   }
  }
  clsInput();
 } 
 
 function key(obj,len,msg){
    if(!IsInt(obj.value)){
    	alert(msg);
    	obj.value="";
    }
    checkMaxInput(obj,len);
}
    /*
     * Statement:   判断是否为电话号码
     * @param:      cellphone             正则表达试
     * @param:      str                   要判断的字符串
     * return:      boolean
     * @Author:     wxg
     */ 
function checkPhone(str){
	var cellphone=/^((\d{3}-|\d{4}-)?(\d{8}|\d{7})?)$/;
	if(!cellphone.test(str)){
		return false;
	}else{
	    return true;
	}
}
    /*
     * Statement:   判断是否为手机号码
     * @param:      cellphone             正则表达试
     * @param:      str                   要判断的字符串
     * return:      boolean
     * @Author:     wxg
     */
function checkMobPhone(str){
    var cellphone=/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/;
	if(!cellphone.test(str)){
		return false;
	}else{
	    return true;
	}
}
    /*
     * Statement:   判断是否为身份证明码
     * @param:      cellphone             正则表达试
     * @param:      str                   要判断的字符串
     * return:      boolean
     * @Author:     wxg       Z123456(7)/
     */
function IdCardRegCheck(str){
   var reg = /^([0-9]{15}|[a-z,A-Z,0-9]{1}[0-9]{6}[(]{1}[a-z,A-Z,0-9]{1}[)]{1}|[0-9]{17}[0-9,x,X]{1})$/; 
   var flag = reg.test(str); 
   return flag; 
}

/*
     * Statement:   判断是否含有数字
     * @param:      str                   要判断的字符串
     * return:      boolean             true      没有数字
     * @Author:     wxg
     */
function checkHavaNum(str){
    var kan_keys="0|1|2|3|4|5|6|7|8|9";
	var kan_sp=kan_keys.split("|")
	var pin=0;
	var sp="";
	for (i=0;i<kan_sp.length;i++){
		if((str).indexOf(kan_sp[i])>=0){
				pin=1;
				sp=kan_sp[i];
				break;
		}
	}
	if(pin == 1){
		return false;
	}else{
	    return true;
	}
}
/*
     * Statement:   验证数字字母下划线组成的字符串
     * @param:      cellphone             正则表达试
     * @param:      str                   要判断的字符串
     * return:      boolean
     * @Author:     wxg
     */
function isCharNum(str){
   var reg = /^([a-zA-Z]|\d|_|-)*$/; 
   var flag = reg.test(str); 
   return flag; 
}
/*
     * Statement:   验证数字和带有小数点的数字。
     * @param:      obj                           验证的对象
     * return:      boolean
     * @Author:     wxg
     */
function down(obj,str){
	var sValue = obj.value;
	if(sValue.indexOf(".")>0){
	    if(sValue.substring(0,sValue.indexOf(".")).length>8||sValue.substring(sValue.indexOf(".")+1,sValue.length).length>3){
	        alert("整数部分请不要超过8位，小数部分不要超过4位，请重新填写!");
	        obj.value="";
	    }
	}else{
	    if(sValue.length>7){
	        alert("整数部分请不要超过8位，小数部分不要超过4位，请重新填写!");
	        obj.value="";
	    }
	}
	//alert(event.keyCode);
	if(event.keyCode == 13){//denotes "Tab" key
		event.keyCode = 9;
	}else if(event.ctrlKey && (event.keyCode == 67 || event.keyCode == 86 || event.keyCode == 88)){
		event.returnValue=false;
		return
	}//denotes the "Ctrl + C" and "Ctrl + X" and "Ctrl + V" behaviors
	else{
			if (event.shiftKey)
			{
				event.returnValue=false;
				return;
			}
			if(( 
					(event.keyCode == 8) || //"backspace" of the left keyboard
					(event.keyCode == 9) ||	//"tab" of the left keyboard
					(event.keyCode == 46) || //del" of the right keyboard
					(event.keyCode >=35 && event.keyCode <=40) || // direction keys of the right key board 
					(event.keyCode>=48 && event.keyCode <=57) || //0-9 of the left keyboard		
					(event.keyCode >= 96 && event.keyCode <=105 //0-9 of the right keyboard
			  )) == false){
						//alert("Sorry, You can't input characters except 1~9 and '-' !");
					
						event.returnValue = false;
				}
			if(( 
					(event.keyCode == 8) || //"backspace" of the left keyboard
					(event.keyCode == 9) ||	//"tab" of the left keyboard
					(event.keyCode == 46) || //del" of the right keyboard
					(event.keyCode == 110) || //"." of the left keyboard
					(event.keyCode == 190) || //"." of the right keyboard
					(event.keyCode >=35 && event.keyCode <=40) || // direction keys of the right key board 
					(event.keyCode>=48 && event.keyCode <=57) || //0-9 of the left keyboard		
					(event.keyCode >= 96 && event.keyCode <=105 //0-9 of the right keyboard
				  )) == false){
				        alert("只能输入正数!");
						//alert("I'm Sorry, You can't input characters except 1~9 and '-' !");
						
						event.returnValue = false;
						obj.value="";
				}
	if(event.keyCode == 110 || event.keyCode ==190){
		if (str=="S")
		{
			event.returnValue = false;
		}
		if (str=="I")
		{
			event.returnValue = false;
		}
		if (sValue!="")
		{
			if (str=="Y" || str=="F")
			{
				if (sValue.indexOf(".")==-1 )
					{
						event.returnValue = false;
						setV(obj,sValue);
					}else{
						event.returnValue = false;
					}
			}
			if (str=="M")
			{
				if (sValue.indexOf(".")==-1 )
					{
						event.returnValue = false;
						setV(obj,sValue);
						
					}else{
						event.returnValue = false;
					}
			}
			
			event.returnValue = false;
		}else{
			event.returnValue = false;
		}
	}
if (event.keyCode == 189)
	{
	    event.returnValue = false;
		
	}
	if (event.returnValue==false)
		{
			return false;
	}
  }
}
function setVal(obj,str){
	obj.value = "-"+str;
}
function setV(obj,str){
	if (str.length==1)
	{
		if (str=="-")
		{
			obj.value="-0.";
		}else{
			obj.value=str+".";
		}
	}else{
		obj.value=str+".";
	}
}

	function inputCheck(obj){
		var kan_keys="%|*|&|#|'|\"";
		var kan_sp=kan_keys.split("|")
		var pin=0;
		var sp="";
		for (i=0;i<kan_sp.length;i++){
			if((obj.value).indexOf(kan_sp[i])>=0){ 
					pin=1;
					sp=kan_sp[i];
					break;
			}
		}
		if(pin == 1){
			window.alert("您输入了非法字符\""+sp+"\"，请重新输入");
			obj.select();
			return false;
		}else{
		}
        remLen1.innerText=' ';
        remLen2.innerText=' ';
        remLen3.innerText=' ';
        remLen4.innerText=' ';
        remLen5.innerText=' ';
        return true;
	}
	function checkAmount(_obj){
		var _value = _obj.value;
		var _alt = _obj.alt;
		var _name = _obj.name;
		var _floatLength = parseInt(4);
		if(inputCheck(_obj)){
			if(!IsNumber(_value) && !IsPlusNumber(_value)){
				_obj.value = "";
			}
			var _tempValue = _value.substring(0,_value.indexOf("."));
			if(_tempValue == "" || _tempValue == "undefined"){
				_obj.value = "";
			}else{
				var _temp = formatKeep(_value,_floatLength);
				_obj.value = _temp;
			}
		}
	}
	function checkAgeNumber(_obj){
		_obj.value = _obj.value.replace(/[^\d]/g,""); 
		if(_obj.value.length>3){
			_obj.value=_obj.value.substring(0,3);	
		}	
	}	
	function checkAccount(_obj,obj_){
		_obj.value = _obj.value.replace(/[^A-Z^a-z^0-9]/g,"");	
		checkMaxInput(_obj,obj_);
	}
	function fucCheckLength(obj) {
		var i=obj.value;
		var str=i.replace(/[^A-Z^a-z^0-9]/g,"11");
//		sum=0;
//		for(i=0;i<obj.value.length;i++) {
//		if ((obj.value.charCodeAt(i)>=48) && (obj.value.charCodeAt(i)<=122)) {
//		sum=sum+1;
//		}else {
//		sum=sum+2;
//		}
//		}
		return str.length;
	}
	function testLeaveTextareaLength(obj,sum){
		testTextareaLength(obj,sum);
        remLen1.innerText=' ';
        remLen2.innerText=' ';
        remLen3.innerText=' ';
        remLen4.innerText=' ';
        remLen5.innerText=' ';
	}
	function testTextareaLength(sth,sum){
		var obj=sth;
	   if(zo_replace(obj.value)!=obj.value){
		   obj.value = zo_replace(obj.value);
		   }	
		while (fucCheckLength(obj)>sum)
		{
			obj.value=obj.value.substring(0,obj.value.length-1);
		}	
		var n = fucCheckLength(obj);
       if(n==sum){
            remLen1.innerText='[最大可输入';
            remLen2.innerText=sum;
            remLen3.innerText='字符，';
            remLen4.innerText='您已达到输入上限！';
            remLen5.innerText=']';
        }else{
            remLen1.innerText='[最大可输入';
            remLen2.innerText=sum;
            remLen3.innerText='字符，还可输入';
            remLen4.innerText=sum-n;
            remLen5.innerText='字符]';
        }
	}
	
	function zo_replace(text) {
	    var str = text && text.replace(/[\u3000\uff01-\uff5f]/g, function($0) {
	        return $0 == "\u3000" ? " " : String.fromCharCode($0.charCodeAt(0) - 0xfee0);
	    });
	    str =str.replace(/%/g, "").replace(/\*/g, "").replace(/\&/g, "").replace(/#/g, "").replace(/\'/g, "").replace(/\"/g, ""); 
	    str =str.replace(/\r\n/gi, "");
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	}
