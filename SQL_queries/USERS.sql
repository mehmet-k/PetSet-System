CREATE SEQUENCE IF NOT EXISTS user_id_seq;

CREATE TABLE IF NOT EXISTS USERS(
	id INT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
	userName varchar(255) UNIQUE NOTNULL,
	realName varchar(255),
	surname varchar(255),
	adress varchar(255)
);

INSERT INTO USERS(userName,realName,surname) VALUES ('meh	metk','mehmet','kececi');

SELECT *
FROM USERS;