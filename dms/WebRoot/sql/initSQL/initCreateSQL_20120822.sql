prompt PL/SQL Developer import file
prompt Created on 2012年8月22日 星期三 by Administrator
set feedback off
set define off
prompt Creating PM_BSC_AREA_INFO...
create table PM_BSC_AREA_INFO
(
  pid                NUMBER(20) not null,
  vc_area_name       VARCHAR2(255),
  vc_delete_flag     NUMBER(2) default 0,
  vc_extend1         VARCHAR2(128),
  vc_extend2         VARCHAR2(256),
  vc_extend3         VARCHAR2(512),
  vc_extend4         VARCHAR2(1024),
  vc_level_code      VARCHAR2(20),
  vc_department_code VARCHAR2(20),
  vc_department_name VARCHAR2(100),
  vc_department_type NUMBER(2)
)
;
comment on table PM_BSC_AREA_INFO
  is '地区信息表';
comment on column PM_BSC_AREA_INFO.vc_area_name
  is '地区名称';
comment on column PM_BSC_AREA_INFO.vc_delete_flag
  is '删除标志';
comment on column PM_BSC_AREA_INFO.vc_level_code
  is '级别编码';
comment on column PM_BSC_AREA_INFO.vc_department_code
  is '机关编码';
comment on column PM_BSC_AREA_INFO.vc_department_name
  is '机关名称';
comment on column PM_BSC_AREA_INFO.vc_department_type
  is '机关类型';
alter table PM_BSC_AREA_INFO
  add constraint PK_PM_BSC_AREA_INFO primary key (PID);

prompt Creating PM_BSC_DEPT_TYPE...
create table PM_BSC_DEPT_TYPE
(
  pid            NUMBER(20) not null,
  vc_type_name   VARCHAR2(255),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024),
  vc_dept_prop   VARCHAR2(255)
)
;
comment on table PM_BSC_DEPT_TYPE
  is '机构性质表';
alter table PM_BSC_DEPT_TYPE
  add constraint PK_PM_BSC_DEPT_TYPE primary key (PID);

prompt Creating PM_BSC_DEPT_INFO...
create table PM_BSC_DEPT_INFO
(
  pid              NUMBER(20) not null,
  vc_area_id       NUMBER(20),
  vc_dept_no       VARCHAR2(50),
  vc_type_id       NUMBER(20),
  vc_dept_name     VARCHAR2(255),
  vc_rank          NUMBER(20),
  vc_render        VARCHAR2(200),
  vc_charge_name   VARCHAR2(30),
  vc_phone         VARCHAR2(20),
  vc_address       VARCHAR2(500),
  vc_remark        VARCHAR2(1000),
  vc_delete_flag   NUMBER(2) default 0,
  vc_extend1       VARCHAR2(128),
  vc_extend2       VARCHAR2(256),
  vc_extend3       VARCHAR2(512),
  vc_extend4       VARCHAR2(1024),
  vc_contact_name  VARCHAR2(30),
  vc_contact_phone VARCHAR2(20)
)
;
comment on table PM_BSC_DEPT_INFO
  is '机构信息表';
create index RELATIONSHIP_1_FK on PM_BSC_DEPT_INFO (VC_AREA_ID);
create index RELATIONSHIP_2_FK on PM_BSC_DEPT_INFO (VC_TYPE_ID);
alter table PM_BSC_DEPT_INFO
  add constraint PK_PM_BSC_DEPT_INFO primary key (PID);
alter table PM_BSC_DEPT_INFO
  add constraint FK_PM_BSC_D_RELATIONS_PM_BSC_A foreign key (VC_AREA_ID)
  references PM_BSC_AREA_INFO (PID);
alter table PM_BSC_DEPT_INFO
  add constraint FK_PM_BSC_D_RELATIONS_PM_BSC_D foreign key (VC_TYPE_ID)
  references PM_BSC_DEPT_TYPE (PID);

prompt Creating PM_BSC_DICT...
create table PM_BSC_DICT
(
  pid            NUMBER(20) not null,
  vc_code        NUMBER(20),
  vc_attrid      NUMBER(20),
  vc_name        VARCHAR2(500),
  vc_remark      VARCHAR2(500),
  vc_position    NUMBER(20),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024)
)
;
comment on table PM_BSC_DICT
  is '字典信息表';
alter table PM_BSC_DICT
  add constraint PK_PM_BSC_DICT primary key (PID);

prompt Creating PM_MNG_ROLE_INFO...
create table PM_MNG_ROLE_INFO
(
  pid            NUMBER(20) not null,
  vc_role_name   VARCHAR2(255),
  vc_memo        VARCHAR2(1024),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024)
)
;
comment on table PM_MNG_ROLE_INFO
  is '角色信息表';
alter table PM_MNG_ROLE_INFO
  add constraint PK_PM_MNG_ROLE_INFO primary key (PID);

prompt Creating PM_BSC_LOGIN_INFO...
create table PM_BSC_LOGIN_INFO
(
  pid             NUMBER(20) not null,
  vc_login_name   VARCHAR2(255),
  vc_login_pwd    VARCHAR2(255),
  vc_name         VARCHAR2(50),
  vc_card_no      VARCHAR2(20),
  vc_role_id      NUMBER(20),
  vc_user_status  NUMBER(2),
  vc_login_status NUMBER(2),
  vc_rule_area    NUMBER(20),
  vc_rule_dept    VARCHAR2(50),
  dt_create       TIMESTAMP(6),
  dt_update       TIMESTAMP(6),
  vc_delete_flag  NUMBER(2) default 0,
  vc_extend1      VARCHAR2(128),
  vc_extend2      VARCHAR2(256),
  vc_extend3      VARCHAR2(512),
  vc_extend4      VARCHAR2(1024),
  vc_remark       VARCHAR2(2000)
)
;
comment on table PM_BSC_LOGIN_INFO
  is '人员登陆信息';
create index RELATIONSHIP_10_FK on PM_BSC_LOGIN_INFO (VC_RULE_AREA);
create index RELATIONSHIP_3_FK on PM_BSC_LOGIN_INFO (VC_ROLE_ID);
alter table PM_BSC_LOGIN_INFO
  add constraint PK_PM_BSC_LOGIN_INFO primary key (PID);
alter table PM_BSC_LOGIN_INFO
  add constraint FK_PM_BSC_L_RELATIONS_PM_BSC_A foreign key (VC_RULE_AREA)
  references PM_BSC_AREA_INFO (PID);
alter table PM_BSC_LOGIN_INFO
  add constraint FK_PM_BSC_L_RELATIONS_PM_MNG_R foreign key (VC_ROLE_ID)
  references PM_MNG_ROLE_INFO (PID);

prompt Creating PM_BSC_OBSER_TYPE...
create table PM_BSC_OBSER_TYPE
(
  pid            NUMBER(20) not null,
  vc_name        VARCHAR2(50),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024),
  vc_type        NUMBER(20)
)
;
comment on table PM_BSC_OBSER_TYPE
  is '立功表彰类别';
comment on column PM_BSC_OBSER_TYPE.vc_type
  is '区别人员与机关';
alter table PM_BSC_OBSER_TYPE
  add constraint PK_PM_BSC_OBSER_TYPE primary key (PID);

prompt Creating PM_BSC_STAFF_INFO...
create table PM_BSC_STAFF_INFO
(
  pid              NUMBER(20) not null,
  vc_real_name     VARCHAR2(255),
  vc_sex           NUMBER(2),
  vc_birth         TIMESTAMP(6),
  vc_political     NUMBER(20),
  vc_cultural      NUMBER(20),
  vc_mphone        VARCHAR2(20),
  vc_ophone        VARCHAR2(20),
  vc_fax           VARCHAR2(20),
  vc_alarm         VARCHAR2(50),
  vc_formation     NUMBER(20),
  vc_rank          NUMBER(20),
  vc_job           NUMBER(20),
  vc_card_no       VARCHAR2(20),
  vc_rank_date     TIMESTAMP(6),
  vc_job_date      TIMESTAMP(6),
  vc_drug_date     TIMESTAMP(6),
  vc_work_division VARCHAR2(200),
  vc_person_prop   VARCHAR2(20),
  vc_office_online VARCHAR2(20),
  vc_virtual_num   VARCHAR2(20),
  vc_transfer_node VARCHAR2(20),
  vc_formation_date TIMESTAMP(6),
  vc_work_date     TIMESTAMP(6),
  vc_remark        VARCHAR2(1000),
  vc_delete_flag   NUMBER(2) default 0,
  vc_extend1       VARCHAR2(128),
  vc_extend2       VARCHAR2(256),
  vc_extend3       VARCHAR2(512),
  vc_extend4       VARCHAR2(1024)
)
;
comment on table PM_BSC_STAFF_INFO
  is '人员信息表';
COMMENT ON COLUMN PM_BSC_STAFF_INFO.VC_EXTEND1 IS '现用于工作分工名称';
alter table PM_BSC_STAFF_INFO
  add constraint PK_PM_BSC_STAFF_INFO primary key (PID);

prompt Creating PM_BSC_OBSERVING...
create table PM_BSC_OBSERVING
(
  pid             NUMBER(20) not null,
  vc_observ_type  NUMBER(20),
  vc_ref_id       NUMBER(20),
  vc_observ_style NUMBER(20),
  vc_observ_time  TIMESTAMP(6),
  vc_remark       VARCHAR2(1000),
  vc_delete_flag  NUMBER(2) default 0,
  vc_extend1      VARCHAR2(128),
  vc_extend2      VARCHAR2(256),
  vc_extend3      VARCHAR2(512),
  vc_extend4      VARCHAR2(1024)
)
;
comment on table PM_BSC_OBSERVING
  is '机构立功表彰';
comment on column PM_BSC_OBSERVING.vc_observ_type
  is '（个人，还是机构）';
create index RELATIONSHIP_12_FK on PM_BSC_OBSERVING (VC_OBSERV_STYLE);
create index RELATIONSHIP_13_FK on PM_BSC_OBSERVING (VC_REF_ID);
alter table PM_BSC_OBSERVING
  add constraint PK_PM_BSC_OBSERVING primary key (PID);
alter table PM_BSC_OBSERVING
  add constraint FK_PM_BSC_O_RELATIONS_PM_BSC_D foreign key (VC_REF_ID)
  references PM_BSC_DEPT_INFO (PID)
  disable;
alter table PM_BSC_OBSERVING
  add constraint FK_PM_BSC_O_RELATIONS_PM_BSC_O foreign key (VC_OBSERV_STYLE)
  references PM_BSC_OBSER_TYPE (PID);
alter table PM_BSC_OBSERVING
  add constraint FK_PM_BSC_O_RELATIONS_PM_BSC_S foreign key (VC_REF_ID)
  references PM_BSC_STAFF_INFO (PID)
  disable;

prompt Creating PM_BSC_OBSERVING_STAFF...
create table PM_BSC_OBSERVING_STAFF
(
  pid             NUMBER(20) not null,
  vc_observ_type  NUMBER(20),
  vc_ref_id       NUMBER(20),
  vc_observ_style NUMBER(20),
  vc_observ_time  TIMESTAMP(6),
  vc_remark       VARCHAR2(1000),
  vc_delete_flag  NUMBER(2) default 0,
  vc_extend1      VARCHAR2(128),
  vc_extend2      VARCHAR2(256),
  vc_extend3      VARCHAR2(512),
  vc_extend4      VARCHAR2(1024)
)
;
comment on table PM_BSC_OBSERVING_STAFF
  is '个人立功表彰';
comment on column PM_BSC_OBSERVING_STAFF.vc_observ_type
  is '（个人ID）';
alter table PM_BSC_OBSERVING_STAFF
  add constraint PK_PM_BSC_OBSERVING_STAFF primary key (PID);

prompt Creating PM_BSC_STAFF_DEPT...
create table PM_BSC_STAFF_DEPT
(
  pid                NUMBER(20) not null,
  vc_staff_id        NUMBER(20),
  vc_dept_id         NUMBER(20),
  vc_delete_flag     NUMBER(2) default 0,
  vc_extend1         VARCHAR2(128),
  vc_extend2         VARCHAR2(256),
  vc_extend3         VARCHAR2(512),
  vc_extend4         VARCHAR2(1024),
  vc_department_code VARCHAR2(20)
)
;
comment on table PM_BSC_STAFF_DEPT
  is '人员对应的机构信息';
create index RELATIONSHIP_8_FK on PM_BSC_STAFF_DEPT (VC_STAFF_ID);
create index RELATIONSHIP_9_FK on PM_BSC_STAFF_DEPT (VC_DEPT_ID);
alter table PM_BSC_STAFF_DEPT
  add constraint PK_PM_BSC_STAFF_DEPT primary key (PID);
alter table PM_BSC_STAFF_DEPT
  add constraint FK_PM_BSC_S_RELATIONS_PM_BSC_D foreign key (VC_DEPT_ID)
  references PM_BSC_DEPT_INFO (PID);
alter table PM_BSC_STAFF_DEPT
  add constraint FK_PM_BSC_S_RELATIONS_PM_BSC_S foreign key (VC_STAFF_ID)
  references PM_BSC_STAFF_INFO (PID);

prompt Creating PM_MNG_CHANGE_INFO...
create table PM_MNG_CHANGE_INFO
(
  pid                  NUMBER(20) not null,
  vc_staff_id          NUMBER(20),
  vc_now_dept          VARCHAR2(100),
  vc_chg_dept          VARCHAR2(100),
  vc_bperson_prop      VARCHAR2(100),
  vc_eperson_prop      VARCHAR2(100),
  vc_change_date       TIMESTAMP(6),
  vc_delete_flag       NUMBER(2) default 0,
  vc_extend1           VARCHAR2(128),
  vc_extend2           VARCHAR2(256),
  vc_extend3           VARCHAR2(512),
  vc_extend4           VARCHAR2(1024),
  vc_remark            VARCHAR2(2000),
  vc_now_dept_name     VARCHAR2(100),
  vc_chg_dept_name     VARCHAR2(100),
  vc_bperson_prop_name VARCHAR2(100),
  vc_eperson_prop_name VARCHAR2(100)
)
;
create index RELATIONSHIP_11_FK on PM_MNG_CHANGE_INFO (VC_STAFF_ID);
alter table PM_MNG_CHANGE_INFO
  add constraint PK_PM_MNG_CHANGE_INFO primary key (PID);
alter table PM_MNG_CHANGE_INFO
  add constraint FK_PM_MNG_C_RELATIONS_PM_BSC_S foreign key (VC_STAFF_ID)
  references PM_BSC_STAFF_INFO (PID);

prompt Creating PM_MNG_MENU...
create table PM_MNG_MENU
(
  pid            NUMBER(20) not null,
  vc_menu_name   VARCHAR2(255),
  vc_client_type NUMBER(2),
  vc_parent_id   NUMBER(20),
  vc_menu_url    VARCHAR2(255),
  vc_position    NUMBER(20),
  vc_meno        VARCHAR2(1024),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024)
)
;
comment on table PM_MNG_MENU
  is '菜单信息表';
alter table PM_MNG_MENU
  add constraint PK_PM_MNG_MENU primary key (PID);

prompt Creating PM_MNG_OPER...
create table PM_MNG_OPER
(
  pid            NUMBER(20) not null,
  vc_oper_name   VARCHAR2(255),
  vc_oper_action VARCHAR2(255),
  vc_menu_id     NUMBER(20),
  vc_memo        VARCHAR2(1024),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024)
)
;
comment on table PM_MNG_OPER
  is '操作信息表';
create index RELATIONSHIP_6_FK on PM_MNG_OPER (VC_MENU_ID);
alter table PM_MNG_OPER
  add constraint PK_PM_MNG_OPER primary key (PID);
alter table PM_MNG_OPER
  add constraint FK_PM_MNG_O_RELATIONS_PM_MNG_M foreign key (VC_MENU_ID)
  references PM_MNG_MENU (PID);

prompt Creating PM_MNG_ROLE_OPER...
create table PM_MNG_ROLE_OPER
(
  pid            NUMBER(20) not null,
  vc_role_id     NUMBER(20),
  vc_oper_id     NUMBER(20),
  vc_delete_flag NUMBER(2) default 0,
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024)
)
;
comment on table PM_MNG_ROLE_OPER
  is '角色对应操作表';
create index RELATIONSHIP_4_FK on PM_MNG_ROLE_OPER (VC_OPER_ID);
create index RELATIONSHIP_5_FK on PM_MNG_ROLE_OPER (VC_ROLE_ID);
alter table PM_MNG_ROLE_OPER
  add constraint PK_PM_MNG_ROLE_OPER primary key (PID);
alter table PM_MNG_ROLE_OPER
  add constraint FK_PM_MNG_R_RELATIONS_PM_MNG_O foreign key (VC_OPER_ID)
  references PM_MNG_OPER (PID);
alter table PM_MNG_ROLE_OPER
  add constraint FK_PM_MNG_R_RELATIONS_PM_MNG_R foreign key (VC_ROLE_ID)
  references PM_MNG_ROLE_INFO (PID);

prompt Creating PM_MNG_SERVER_LOG...
create table PM_MNG_SERVER_LOG
(
  pid              NUMBER(20) not null,
  vc_delete_flag   NUMBER(2) default 0,
  log_type         NUMBER(2),
  oper_type        NUMBER(2),
  oper_id          NUMBER(20),
  oper_ip          VARCHAR2(255),
  oper_server_uid  VARCHAR2(255),
  be_operated_type NUMBER(2),
  be_operated_id   NUMBER(20),
  log_msg          VARCHAR2(2048),
  created_on       TIMESTAMP(6),
  vc_data_pid      NUMBER(20),
  vc_extend1       VARCHAR2(128),
  vc_extend2       VARCHAR2(256),
  vc_extend3       VARCHAR2(512),
  vc_extend4       VARCHAR2(1024)
)
;
comment on table PM_MNG_SERVER_LOG
  is '日志信息表';
comment on column PM_MNG_SERVER_LOG.pid
  is 'PK';
comment on column PM_MNG_SERVER_LOG.log_type
  is '日志类型(1登陆日志，2交易日志，3资金日志，4设置日志，5监控日志)';
comment on column PM_MNG_SERVER_LOG.oper_type
  is '操作人类型(用户类型)';
comment on column PM_MNG_SERVER_LOG.oper_id
  is '操作人ID(TRADER_INFO_ID/ STAFF_INFO_ID)';
comment on column PM_MNG_SERVER_LOG.oper_ip
  is '操作人IP';
comment on column PM_MNG_SERVER_LOG.oper_server_uid
  is '操作服务器UID（发起该笔日志的服务器）';
comment on column PM_MNG_SERVER_LOG.be_operated_type
  is '被操作人类型用户类型)';
comment on column PM_MNG_SERVER_LOG.be_operated_id
  is '被操作人ID';
comment on column PM_MNG_SERVER_LOG.log_msg
  is '描述';
comment on column PM_MNG_SERVER_LOG.created_on
  is '时间戳';
comment on column PM_MNG_SERVER_LOG.vc_extend1
  is '扩展字段1';
comment on column PM_MNG_SERVER_LOG.vc_extend2
  is '扩展字段2';
comment on column PM_MNG_SERVER_LOG.vc_extend3
  is '扩展字段3';
comment on column PM_MNG_SERVER_LOG.vc_extend4
  is '扩展字段4';
alter table PM_MNG_SERVER_LOG
  add constraint PK_PM_MNG_SERVER_LOG primary key (PID);

prompt Creating table PM_BSC_JOB_CHANGE...
create table PM_BSC_JOB_CHANGE
(
  pid            NUMBER(20) not null,
  vc_staff_id    NUMBER(20),
  vc_now_job     VARCHAR2(100),
  vc_chg_job     VARCHAR2(100),
  vc_now_date    TIMESTAMP(6),
  vc_chg_date    TIMESTAMP(6),
  vc_remark      VARCHAR2(1000),
  vc_delete_flag NUMBER(2),
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024),
  vc_ifedit      NUMBER(2) default 0,
  vc_create_date TIMESTAMP(6) default systimestamp,
  vc_end_date    TIMESTAMP(6)
)
;
alter table PM_BSC_JOB_CHANGE
  add constraint PK_PM_BSC_JOB_CHANGE primary key (PID);

prompt Creating table PM_BSC_RANK_CHANGE...
create table PM_BSC_RANK_CHANGE
(
  pid            NUMBER(20) not null,
  vc_staff_id    NUMBER(20),
  vc_now_rank    NUMBER(20),
  vc_chg_rank    NUMBER(20),
  vc_now_date    TIMESTAMP(6),
  vc_chg_date    TIMESTAMP(6),
  vc_remark      VARCHAR2(1000),
  vc_delete_flag NUMBER(2),
  vc_extend1     VARCHAR2(128),
  vc_extend2     VARCHAR2(256),
  vc_extend3     VARCHAR2(512),
  vc_extend4     VARCHAR2(1024),
  vc_ifedit      NUMBER(2) default 0,
  vc_create_date TIMESTAMP(6) default systimestamp,
  vc_type        NUMBER(2),
  vc_end_date    TIMESTAMP(6)
)
;
alter table PM_BSC_RANK_CHANGE
  add constraint PK_PM_BSC_RANK_CHANGE primary key (PID);
  
set feedback on
set define on
prompt Done.
exit;