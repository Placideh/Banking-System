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
SELECT * FROM account WHERE account_number='111-222-333-12' AND password='123456';