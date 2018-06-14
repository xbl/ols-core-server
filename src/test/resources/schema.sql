create table user
(
	id int auto_increment primary key,
	user_name varchar(25) not null,
	password varchar(25) not null,
	phone    varchar(15) null,
	create_time datetime not null
);