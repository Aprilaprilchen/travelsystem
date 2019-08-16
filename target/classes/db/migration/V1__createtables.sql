DROP TABLE IF EXISTS area CASCADE;
DROP TABLE IF EXISTS hotel CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP SEQUENCE IF EXISTS area_id_seq;
DROP SEQUENCE IF EXISTS hotel_id_seq;
DROP SEQUENCE IF EXISTS costumer_id_seq;
CREATE SEQUENCE area_id_seq START WITH 1;
CREATE SEQUENCE hotel_id_seq START WITH 1;
CREATE SEQUENCE customer_id_seq START WITH 1;

CREATE TABLE area(
 id      SERIAL NOT NULL,
 name     VARCHAR (30) UNIQUE NOT NULL,
 comsumption_level   INTEGER,
 location   VARCHAR(50),
 description   VARCHAR(150)
);
ALTER TABLE area ADD CONSTRAINT area_pk PRIMARY KEY(id);

CREATE TABLE hotel(
 id     SERIAL NOT NULL,
 name    VARCHAR(30),
 location   VARCHAR(150),
 price    NUMERIC(7, 2),
 comfort_level   INTEGER,
 area_id int
);
ALTER TABLE hotel ADD CONSTRAINT hotel_pk PRIMARY KEY(id);

CREATE TABLE customer (
 id     SERIAL NOT NULL,
 name    VARCHAR (50) UNIQUE NOT NULL,
 first_name  VARCHAR (50),
 last_name  VARCHAR (50),
 password   VARCHAR (50) NOT NULL,
 email    VARCHAR(355) UNIQUE NOT NULL,
 age    int NOT NULL,
 budget    NUMERIC(10, 2),
 gender    CHAR(1),
 area_id int
);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY(id);
ALTER TABLE customer
 ADD CONSTRAINT customer_area_fk FOREIGN KEY(area_id)
  REFERENCES area(id);
ALTER TABLE hotel
 ADD CONSTRAINT hotel_area_fk FOREIGN KEY(area_id)
  REFERENCES area(id);

--select * from area
--select * from hotel
--select * from customer