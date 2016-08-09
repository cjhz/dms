-- 数据表空间
CREATE TABLESPACE DMS DATAFILE 
  'D:\oracle\oradata\ORCL\DMS.DBF' SIZE 30M AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED;
CREATE USER "DMS"  PROFILE "DEFAULT" 
    IDENTIFIED BY "DMS" DEFAULT TABLESPACE "DMS" 
    QUOTA UNLIMITED 
    ON "DMS" 
    ACCOUNT UNLOCK;
GRANT CREATE ANY SYNONYM TO "DMS";
GRANT CREATE ANY INDEX TO "DMS";
GRANT CREATE PROCEDURE TO "DMS";
GRANT CREATE SEQUENCE TO "DMS";
GRANT CREATE TABLE TO "DMS";
GRANT CREATE TRIGGER TO "DMS";
GRANT CREATE VIEW TO "DMS";
GRANT SELECT ANY SEQUENCE TO "DMS";
GRANT SELECT ANY TABLE TO "DMS";
--高级复制必须添加
GRANT SELECT ANY DICTIONARY TO "DMS";
GRANT "CONNECT" TO "DMS";
GRANT "DBA" TO "DMS";
/
exit;
