DROP DATABASE IF EXISTS yootk ;
CREATE DATABASE yootk CHARACTER SET UTF8 ;
USE yootk ;
CREATE TABLE member (
   mid          		VARCHAR(50),
   name         		VARCHAR(10) ,
   age          		INT ,
   salary       		DOUBLE,
   birthday     		DATE,
   content      		TEXT,
   isdel        		INT DEFAULT 0,
   CONSTRAINT pk_mid PRIMARY KEY(mid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO member(mid, name, age, salary, birthday, content) VALUES
	 ('muyan', '沐言科技', 18, 5999.99 , '2006-09-19', 'www.yootk.com') ;
INSERT INTO member(mid, name, age, salary, birthday, content) VALUES
	 ('yootk', '沐言优拓', 38, 8787.66, '1999-08-13', 'www.yootk.com') ;
INSERT INTO member(mid, name, age, salary, birthday, content) VALUES
	 ('edu', '李兴华编程训练营', 22, 6723.12, '2004-08-13', 'edu.yootk.com') ;
