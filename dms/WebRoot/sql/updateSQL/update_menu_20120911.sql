--保证pid没错的情况下
update pm_mng_menu t set t.vc_menu_name = '部门立功表彰次数统计' where t.pid = 305;
update pm_mng_menu t set t.vc_menu_name = '人员立功表彰次数统计',t.vc_position = 4 where t.pid = 313;
update pm_mng_menu t set t.vc_position =3 where t.pid=314;
delete from pm_mng_menu t where t.pid=100;--首页
commit;
exit