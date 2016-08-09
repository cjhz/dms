function downXLS(tab,filename) {
    var tab = document.getElementById(tab);
    var frm = document.getElementById("exportfrm");
    frm.filename.value= filename;
    frm.body.value = tab.innerHTML;
    frm.submit();
 }