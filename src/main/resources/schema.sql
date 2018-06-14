create table user
(
	id int auto_increment
		primary key,
	userName varchar(100) null comment '用户名(登录名)',
	password varchar(100) null comment '密码',
	comments varchar(100) null comment '备注',
	createTime datetime null comment '创建时间',
	phone    varchar(100) null comment '手机号'
)
;

INSERT INTO user (id, userName, password, comments, createTime,phone) VALUES (1, 'dun', '123456', 'xxxxx', '2018-05-16 07:11:14',null);