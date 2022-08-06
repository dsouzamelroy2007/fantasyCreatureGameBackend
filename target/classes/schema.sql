DROP TABLE IF EXISTS creatures;


CREATE TABLE creatures (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   type VARCHAR(20) NOT NULL,
   state VARCHAR(20) NOT NULL,
   leadership_value INT
);

DROP TABLE IF EXISTS attributes;

CREATE TABLE attributes (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   creature_id int not null,
   name VARCHAR(20) NOT NULL,
   ATTRIBUTE_VALUE int not null ,
   foreign key (creature_id) references creatures(id)

);

DROP TABLE IF EXISTS creature_action;

CREATE TABLE creature_action (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   creature_id int not null,
   action_name VARCHAR(20) NOT NULL,
   type VARCHAR(20) NOT NULL,
   foreign key (creature_id) references creatures(id)

);
