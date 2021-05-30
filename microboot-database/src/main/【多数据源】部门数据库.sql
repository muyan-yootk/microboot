DROP DATABASE IF EXISTS muyan ;
CREATE DATABASE muyan CHARACTER SET UTF8 ;
USE muyan ;
CREATE TABLE dept(
   did			BIGINT    AUTO_INCREMENT,
   dname  		VARCHAR(50),
   loc			VARCHAR(50),
   flag			VARCHAR(50),
   CONSTRAINT pk_did PRIMARY KEY(did)
) engine=innodb;
-- 增加测试数据
INSERT INTO dept(dname, loc, flag) VALUES ('教学部', '北京', database());
INSERT INTO dept(dname, loc, flag) VALUES ('财务部', '上海', database());
INSERT INTO dept(dname, loc, flag) VALUES ('技术部', '洛阳', database());
