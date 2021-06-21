-- 使用springsecurity数据库
USE springsecurity;
-- 创建客户信息表
CREATE TABLE client(
   cid                  VARCHAR(50) not null,
   secret                 VARCHAR(68),
   scope               VARCHAR(32),
   grants  VARCHAR(50) ,
   url                     VARCHAR(200),
   CONSTRAINT pk_mid PRIMARY KEY (cid)
) engine='innodb';
INSERT INTO client(cid, secret,scope, grants, url) VALUES ('client_muyan','{bcrypt}$2a$10$yrmieUQje2lL10p4jT3dx.oS5e4FtqfP7QKoh9RrZ4JQEfeLdcsaG','webapp', 'authorization_code', 'https://www.yootk.com') ;
INSERT INTO client(cid, secret,scope, grants, url) VALUES ('client_yootk','{bcrypt}$2a$10$yrmieUQje2lL10p4jT3dx.oS5e4FtqfP7QKoh9RrZ4JQEfeLdcsaG','webapp', 'authorization_code', 'https://www.yootk.com');

INSERT INTO client(cid, secret,scope, grants, url) VALUES ('client_happy',
    '{bcrypt}$2a$10$yrmieUQje2lL10p4jT3dx.oS5e4FtqfP7QKoh9RrZ4JQEfeLdcsaG','webapp', 'authorization_code', 'http://oauth-client:8888/login');
