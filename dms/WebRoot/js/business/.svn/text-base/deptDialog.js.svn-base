//获取checkbox选中值
var getChkKeys = function(arg1,obj){
	var deptkey = '';
	var objarr = new Array();
	$("input[name^='"+arg1+"_']:checked").each(function(){
		  if(chkRepeat(obj,$(this).val())==false){
		  	  return false;
		  }
          deptkey += delFormat($(this).val())+';';
    });
    return deptkey;
}
var getChkVals = function(arg1,arg2){
	var deptval = '';
	$("input[name^='"+arg1+"_']:checked").each(function(){
		  deptval +=$("#N"+$(this).attr('id')).val()+';';
    });
    return deptval;
}

var chkRepeat = function(arg1,arg2){
	var objarr = new Array();
	 if(arg1.indexOf(arg2)>-1){
	  	 objarr = arg1.split(';');
	  	 if(objarr.length>0){
	  	 	for(var i=0;i<objarr.length;i++){
	  	 		if(objarr[i]==arg2){
	  	 			alert('不能重复新增相同记录！');
	  	 			return false;
	  	 		}
	  	 	}
	  	}
	}
}

function delFormat(str){
  return str.replace(/,/g,"");
}