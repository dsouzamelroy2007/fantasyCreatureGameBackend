--INSERT INTO creatures (name) VALUES ('Creature 1');
--INSERT INTO creatures (name) VALUES ('Creature 2');
INSERT INTO creatures (name,type,state) values ('Creature 1','Zombie','ALIVE');
INSERT INTO creatures (name,type,state) values ('Creature 3','Wizard','ALIVE');
INSERT INTO creatures (name,type,state) values ('Creature 3','Troll','ALIVE');
INSERT INTO creatures (name,type,state) values ('Creature 4','Zombie','ALIVE');

--Creature_attributes
INSERT INTO attributes (creature_id,name,attribute_value) values (1,'strength',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (1,'armor',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (1,'intellect',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (1,'speed',4);

INSERT INTO attributes (creature_id,name,attribute_value) values (2,'strength',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (2,'armor',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (2,'intellect',7);
INSERT INTO attributes (creature_id,name,attribute_value) values (2,'speed',4);


 ----- Creature_action
INSERT INTO creature_action (creature_id,action_name,type) values (1,'weapon','hammer');
INSERT INTO creature_action (creature_id,action_name,type) values (2,'weapon','sword');