DROP DATABASE IF EXISTS yootk ;
CREATE DATABASE yootk CHARACTER SET UTF8 ;
USE yootk ;
CREATE TABLE emp(
   eid			VARCHAR(50),
   ename			VARCHAR(50) ,
   sal			DOUBLE ,
   did			BIGINT ,
   flag			VARCHAR(50),
   CONSTRAINT pk_eid PRIMARY KEY(eid)
) engine=innodb ;
-- 增加雇员测试数据
INSERT INTO emp(eid, ename, sal, did, flag) VALUES
	 	 ('yootk-teacher-a', '沐言科技讲师-A', 5000.00, 1, database());
INSERT INTO emp(eid, ename, sal, did, flag) VALUES
	 	 ('yootk-teacher-b', '沐言科技讲师-B', 5000.00, 1, database());
INSERT INTO emp(eid, ename, sal, did, flag) VALUES
	 	 ('yootk-leader-c', '沐言科技领导', 6000.00, 2, database());
INSERT INTO emp(eid, ename, sal, did, flag) VALUES
	 	 ('yootk-developer-d', '沐言科技工程师-A', 9000.00, 2, database());
INSERT INTO emp(eid, ename, sal, did, flag) VALUES
	 	 ('yootk-developer-e', '沐言科技工程师-B', 9800.00, 2, database());