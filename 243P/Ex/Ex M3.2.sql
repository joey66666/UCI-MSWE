-- Question 1
-- CREATE INDEX vendor_zip_code_index ON vendors(vendor_zip_code);


-- Question 2
-- USE ex;
-- 
-- DROP TABLE IF EXISTS members;
-- DROP TABLE IF EXISTS members_committees;
-- DROP TABLE IF EXISTS committees;
-- 
-- CREATE TABLE members(
-- 	member_id		INT		PRIMARY KEY		AUTO_INCREMENT,
-- 	first_name	VARCHAR(50)		NOT NULL,
-- 	last_name		VARCHAR(50) 	NOT NULL,
-- 	address 		VARCHAR(200) 	NOT NULL,
-- 	city				VARCHAR(50)		NOT NULL,
-- 	state				VARCHAR(50)		NOT NULL,
-- 	phone 			VARCHAR(50) 	NOT NULL
-- );
-- 
-- CREATE TABLE committees(
-- 	committee_id 		INT PRIMARY KEY 	AUTO_INCREMENT,
-- 	committee_name 	VARCHAR(50)		NOT NULL
-- );
-- 
-- CREATE TABLE members_committees(
-- 	member_id 		INT NOT NULL,
-- 	committee_id	INT NOT NULL,
-- 	CONSTRAINT members_committees_fk_members
-- 		FOREIGN KEY (member_id)
-- 		REFERENCES members(member_id),
-- 	CONSTRAINT members_committees_fk_committees
-- 		FOREIGN KEY (committee_id)
-- 		REFERENCES committees(committee_id)
-- );


-- Question 3
-- INSERT INTO members VALUES
-- (DEFAULT, 'Trump', 'Donald', 'White House', 'Washington DC', 'DC', '1234520952'),
-- (DEFAULT, 'Joe', 'Biden', 'Biden\'s Home', 'Syracuse', 'New York', '1234500152');
-- 
-- INSERT INTO committees VALUES
-- (DEFAULT, 'House'),
-- (DEFAULT, 'Senate');
-- 
-- INSERT INTO members_committees VALUES
-- (1,2),
-- (2,1),
-- (2,2);
-- 
-- SELECT committees.committee_name, members.last_name, members.first_name
-- FROM committees 
-- 	JOIN members_committees ON committees.committee_id = members_committees.committee_id
-- 	JOIN members ON members.member_id = members_committees.member_id
-- ORDER BY committees.committee_name, members.last_name, members.first_name;


-- Question 4
-- ALTER TABLE members ADD anuual_dues DECIMAL(5,2) DEFAULT 52.50;
-- ALTER TABLE members ADD payment_date DATE;


-- Question 5
-- ALTER TABLE committees MODIFY committee_name VARCHAR(50) NOT NULL UNIQUE;
-- INSERT INTO committees VALUES (DEFAULT, 'Senate');
