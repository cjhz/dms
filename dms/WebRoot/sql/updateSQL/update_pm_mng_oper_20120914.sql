update pm_mng_oper t set t.vc_oper_action=t.pid || '01';
commit;
exit
