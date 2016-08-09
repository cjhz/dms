prompt Importing table pm_mng_oper...
set feedback off
set define off
insert into pm_mng_oper t
  (t.pid, t.vc_oper_name, t.vc_oper_action, t.vc_menu_id, t.vc_delete_flag)
values
  (102, '新增机构信息', 10102, 102, 0);

insert into pm_mng_oper t
  (t.pid, t.vc_oper_name, t.vc_oper_action, t.vc_menu_id, t.vc_delete_flag)
values
  (103, '编辑机构信息', 10103, 102, 0);

insert into pm_mng_oper t
  (t.pid, t.vc_oper_name, t.vc_oper_action, t.vc_menu_id, t.vc_delete_flag)
values
  (118, '新增人员基础信息', 11702, 203, 0);

insert into pm_mng_oper t
  (t.pid, t.vc_oper_name, t.vc_oper_action, t.vc_menu_id, t.vc_delete_flag)
values
  (119, '编辑人员基础信息', 11703, 203, 0);
  
prompt Done.
