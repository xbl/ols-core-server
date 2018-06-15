create table t_user
(
	id varchar(32) primary key,
	name varchar(25) not null,
	password varchar(25) not null,
	phone_number varchar(15) null
);