create table members(
    id long not null primary key,
	userId varchar_ignorecase(50) not null,
	userPassword varchar_ignorecase(200) not null,
);

-- create table authorities (
-- 	username varchar_ignorecase(50) not null,
-- 	authority varchar_ignorecase(50) not null,
-- 	constraint fk_authorities_users foreign key(username) references users(username)
-- );