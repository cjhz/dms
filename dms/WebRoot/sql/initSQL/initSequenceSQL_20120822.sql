-----------------------------------------------------
-- Export file for user PSPM                       --
-- Created by Administrator on 2012-8-22, 13:44:11 --
-----------------------------------------------------

set define off
spool sequence.log

prompt
prompt Creating sequence SEQ_PM_BSC_AREA_INFO
prompt ======================================
prompt
create sequence SEQ_PM_BSC_AREA_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_DEPT_INFO
prompt ======================================
prompt
create sequence SEQ_PM_BSC_DEPT_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 320
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_DEPT_TYPE
prompt ======================================
prompt
create sequence SEQ_PM_BSC_DEPT_TYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 220
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_DICT
prompt =================================
prompt
create sequence SEQ_PM_BSC_DICT
minvalue 1
maxvalue 999999999999999999999999999
start with 260
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_LOGIN_INFO
prompt =======================================
prompt
create sequence SEQ_PM_BSC_LOGIN_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_OBSERVING
prompt ======================================
prompt
create sequence SEQ_PM_BSC_OBSERVING
minvalue 1
maxvalue 9999999999999999999999999
start with 240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_OBSERVING_STAFF
prompt ============================================
prompt
create sequence SEQ_PM_BSC_OBSERVING_STAFF
minvalue 1
maxvalue 99999999999999999999
start with 260
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_OBSER_TYPE
prompt =======================================
prompt
create sequence SEQ_PM_BSC_OBSER_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 180
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_STAFF_DEPT
prompt =======================================
prompt
create sequence SEQ_PM_BSC_STAFF_DEPT
minvalue 1
maxvalue 999999999999999999999999999
start with 320
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_STAFF_INFO
prompt =======================================
prompt
create sequence SEQ_PM_BSC_STAFF_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_CHANGE_INFO
prompt ========================================
prompt
create sequence SEQ_PM_MNG_CHANGE_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 180
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_MENU
prompt =================================
prompt
create sequence SEQ_PM_MNG_MENU
minvalue 1
maxvalue 999999999999999999999999999
start with 120
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_OPER
prompt =================================
prompt
create sequence SEQ_PM_MNG_OPER
minvalue 1
maxvalue 999999999999999999999999999
start with 100
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_ROLE_INFO
prompt ======================================
prompt
create sequence SEQ_PM_MNG_ROLE_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_ROLE_OPER
prompt ======================================
prompt
create sequence SEQ_PM_MNG_ROLE_OPER
minvalue 1
maxvalue 999999999999999999999999999
start with 150
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_MNG_SERVER_LOG
prompt =======================================
prompt
create sequence SEQ_PM_MNG_SERVER_LOG
minvalue 1
maxvalue 999999999999999999999999999
start with 100
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_JOB_CHANGE
prompt =======================================
prompt
create sequence SEQ_PM_BSC_JOB_CHANGE
minvalue 1
maxvalue 999999999999999999999999999
start with 120
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PM_BSC_RANK_CHANGE
prompt ========================================
prompt
create sequence SEQ_PM_BSC_RANK_CHANGE
minvalue 1
maxvalue 999999999999999999999999999
start with 140
increment by 1
cache 20;

spool off
exit;