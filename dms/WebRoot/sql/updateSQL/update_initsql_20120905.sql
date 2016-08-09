delete pm_mng_role_oper where vc_oper_id in(205,209,213,217);
delete pm_mng_oper where pid in(205,209,213,217);
commit;
exit;