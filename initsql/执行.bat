@echo off

sqlplus system/cjh@ORCL_192.168.2.99 @initUserSQL.sql
sqlplus DMS/DMS@ORCL_192.168.2.99 @initCreateSQL_20120822.sql
sqlplus DMS/DMS@ORCL_192.168.2.99 @initSequenceSQL_20120822.sql
sqlplus DMS/DMS@ORCL_192.168.2.99 @initInsertSQL_20120822.sql
echo. & pause;