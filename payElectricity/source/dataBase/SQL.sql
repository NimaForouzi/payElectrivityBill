SHOW TABLES;


-- name and familyName = username
-- password = counterName
CREATE TABLE customer(
  username varchar(20),
  pass varchar(20),
  adress varchar(255),
  email varchar(50),
  phoneNumber int,
  nationalCode int,
  Various varchar(20),
  previusWattUsage int,
  nowWattUsage int,
  debt int
);

INSERT INTO customer VALUES('ali sameti', '224455', 'baharan St. golchin alley', 'salisameti@gmail.com', 0931242334, 382692422,'domestic', 5 , 4 , 245777);
INSERT INTO customer VALUES('ahmad sobhani', '552233', '3.19 st. lale alley', 'ahmadsobhani@gmail.com', 09324324, 38232422, 'business', 1 , 2 , 223);
INSERT INTO customer VALUES('samad sadeghi', '441122', 'shapor st. beydaghi alley','samadsadeghi@gmail.com', 09344324, 3342534,'domestic', 0 , 0 , 0);
INSERT INTO customer VALUES('saman shademan', '341123', 'ferdosi st. nargess alley','samanshademan@gmail.com', 093224324, 3534534,'business', 0 , 0 , 0);
INSERT INTO customer VALUES('homan wami', '442233', 'sadr st. barezan alley','homanwami@gmail.com', 096424223, 2412453,'domestic', 3 , 2 , 0);
INSERT INTO customer VALUES('barzan kashefi', '112452', 'namdaran st. trize alley','barzankashefi@gmail.com', 0942254223, 423543532, 'business', 0 , 0 , 244);
INSERT INTO customer VALUES('karim qasemi', '53452', 'lale st. vadi alley','karimqasemi@gmail.com', 0974358034, 23533235,'business', 4 , 23 ,234);


CREATE TABLE thechnicalAssist(
  username varchar(20),
  pass varchar(20),
  adress varchar(255),
  email varchar(50),
  phoneNumber int,
  nationalCode int
);

INSERT INTO thechnicalAssist VALUES('kamran golabi', '23344','asadi st. saerpanah alley','kamrangolabi@gmail.com', 096342235, 42424);


CREATE TABLE financialManager(
  username varchar(20),
  pass varchar(20),
  payments int,
  adress varchar(255),
  email varchar(50),
  phoneNumber int,
  nationalCode int
);
INSERT INTO financialManager VALUES('shahin sarband', '112233',0,'kamali st. damghani alley','shahinsarband@gmail.com', 097634535, 424242);

SELECT * FROM customer ;
SELECT * FROM thechnicalAssist ;
SELECT * FROM financialManager ;

/*
DROP TABLE factor;
DROP TABLE financialManager;
DROP TABLE thechnicalAssist;
DROP TABLE customer;
*/
/*
DELETE FROM customer;
DELETE FROM factor;
DELETE FROM financialManager;
DELETE FROM thechnicalAssist;
*/
/*
ALTER TABLE electricity.customer DROP role;
DESCRIBE electricity.customer;

ALTER TABLE electricity.thechnicalAssist DROP COLUMN role;

INSERT INTO electricity.customer VALUES('ali asadi', '224455', 'ali' , 'asadi',324562545, 'gorgan, ahmadi street','091342245','ahmadi@gmail.com');
INSERT INTO electricity.thechnicalAssist VALUES('reza samadi', '446677', 'reza', 'samadi' , 834512445, 'abadan, varmaghan street', '091343253', 'rez@gmail.com'); 
INSERT INTO electricity.financialManager VALUES('sina saedi', '552244', 'sina', 'saedi' , 433345, 'semnan, ahmadian street', '095533563', 'sisaed@gmail.com');
DESCRIBE electricity.thechnicalAssist;

DELETE FROM electricity.thechnicalAssist
WHERE username = 'sina saedi';

DELETE FROM customer; 
DELETE FROM financialManager;
DELETE FROM thechnicalAssist;

ALTER TABLE electricity.customer ADD COLUMN paingCost int;
ALTER TABLE electricity.customer ADD COLUMN various varchar(20);
ALTER TABLE electricity.customer DROP COLUMN varous;
ALTER TABLE electricity.customer ADD COLUMN meterCounterNum int;
ALTER TABLE electricity.customer ADD COLUMN previousMonthWatt int;
 ALTER TABLE electricity.customer ADD COLUMN thisMonthWatt int;
                             DESCRIBE customer;       
ALTER TABLE electricity.customer DROP national_code;
ALTER TABLE electricity.customer DROP email;
ALTER TABLE electricity.customer DROP telephone;
ALTER TABLE electricity.customer DROP first_name;
ALTER TABLE electricity.customer DROP last_name;
ALTER TABLE electricity.customer DROP paingCost;
ALTER TABLE electricity.thechnicalAssist DROP first_name;
ALTER TABLE electricity.thechnicalAssist DROP last_name;
ALTER TABLE electricity.thechnicalAssist DROP email;
ALTER TABLE electricity.thechnicalAssist DROP adress;
ALTER TABLE electricity.thechnicalAssist DROP telephone;
ALTER TABLE electricity.thechnicalAssist DROP national_code;
ALTER TABLE electricity.financialManager DROP first_name;
ALTER TABLE electricity.financialManager DROP last_name;
ALTER TABLE electricity.financialManager DROP email;
ALTER TABLE electricity.financialManager DROP telephone;
ALTER TABLE electricity.financialManager DROP adress;
ALTER TABLE electricity.financialManager DROP national_code;
INSERT INTO customer VALUES('ali asadi', '352451245', 'khiaban bahran', 245234, 'domestic', 5, 4);

*/

