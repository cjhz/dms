//获取checkbox选中值
var getChkVal = function(arg1,arg2){
	var propval = '';
	var workval = '';
	$("input[name^='"+arg1+"_']:checked").each(function(){
          propval += $(this).val()+';';
    });
	$("input[name^='"+arg2+"_']:checked").each(function(){
          workval += $(this).val()+';';
    });
    $("#"+arg1).val(propval);
    $("#"+arg2).val(workval);
}
var getChkVal2 = function(arg1){
	var propval = '';
	//var workval = '';
	$("input[name^='"+arg1+"_']:checked").each(function(){
          propval += $(this).val()+';';
    });
	//$("input[name^='"+arg2+"_']:checked").each(function(){
    //      workval += $(this).val()+';';
    //});
    $("#"+arg1).val(propval);
    //$("#"+arg2).val(workval);
}

//用于人员属性调动、工作分工，取内容
var getChkHtm = function(arg1,arg2){
	var propval = '';
	if($("input[name^='"+arg1+"_']:checked").length<1){
		return false;
	}
	$("input[name^='"+arg1+"_']:checked").each(function(){
          propval += $(this).attr("propname")+';';
    });
    $("#"+arg2).val(propval);
}

//设置checkbox选中值
var setChkVal = function(arg1,arg2){
	var propval =$("#"+arg1).val();
	var workval =$("#"+arg2).val();
	var propArray = new Array();
	var workArray = new Array();
	if(propval.indexOf(';')>0){
		propArray = propval.split(';');
	}
	if(workval.indexOf(';')>0){
		workArray = workval.split(';');
	}
	for(var i=0;i<propArray.length;i++){
		if(propArray[i]!=''){
			$("input[name^='"+arg1+"_'][value="+propArray[i]+"]").attr('checked','true');
		}
	}
	for(var i=0;i<workArray.length;i++){
		if(workArray[i]!=''){
			$("input[name^='"+arg2+"_'][value="+workArray[i]+"]").attr('checked','true');
		}
	}
}
//设置checkbox选中值
var setChkVal2 = function(arg1){
	var propval =$("#"+arg1).val();
	//var workval =$("#"+arg2).val();
	var propArray = new Array();
	//var workArray = new Array();
	if(propval.indexOf(';')>0){
		propArray = propval.split(';');
	}
	//if(workval.indexOf(';')>0){
	//	workArray = workval.split(';');
	//}
	for(var i=0;i<propArray.length;i++){
		if(propArray[i]!=''){
			$("input[name^='"+arg1+"_'][value="+propArray[i]+"]").attr('checked','true');
		}
	}
	//for(var i=0;i<workArray.length;i++){
	//	if(workArray[i]!=''){
	//		$("input[name^='"+arg2+"_'][value="+workArray[i]+"]").attr('checked','true');
	//	}
	//}
}

	
