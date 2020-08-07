-- CREATE DATABASE Bank;
-- USE Bank;
-- CREATE TABLE account (
-- 	account_number VARCHAR(100),
-- 	first_name VARCHAR(100),
-- 	last_name VARCHAR(100),
-- 	email VARCHAR(100),
-- 	PRIMARY KEY(account_number)

-- );
-- DESCRIBE account;
-- INSERT INTO account(account_number,first_name,last_name,email) VALUES('111-222-333-12','user','user','user@user.com');
-- SELECT * FROM account;
-- ALTER TABLE account ADD COLUMN password VARCHAR(255);
-- SELECT * FROM account;
-- UPDATE account SET password="123456" WHERE account_number="111-222-333-12";
-- SELECT * FROM account;
-- SELECT * FROM account WHERE account_number='111-222-333-12' AND password='123456';
-- SELECT account_number FROM account WHERE account_number='111-222-333-12';
-- SELECT account_number FROM account;
-- ALTER TABLE account ADD COLUMN balance INT(10);
-- SELECT * FROM account;
-- ALTER TABLE account DROP COLUMN balance;
-- SELECT * FROM account;
-- ALTER TABLE account ADD COLUMN balance INT(10) NOT NULL;
-- SELECT * FROM account;


-- UPDATE account SET account_number='111-222-333-12' WHERE account_number='112-222-333-12';
-- so this query above I never know that it is going to work but  I did and I am excited 
-- SELECT * FROM account;
-- CREATE DATABASE Admin;
-- USE admin;
-- CREATE TABLE admin (
-- 	name VARCHAR(100) NOT NULL,
-- 	password VARCHAR(255) NOT NULL,
-- 	PRIMARY KEY(name)


-- );
-- INSERT INTO admin (name,password)VALUES('Admin','123');
SELECT * FROM admin;	