INSERT INTO authorities(id,name,creation_date,last_modified_date) VALUES (17,'VIEW_ENQUIRY_LIST',now(),now());
INSERT INTO authorities(id,name,creation_date,last_modified_date) VALUES (18,'VIEW_ENQUIRY',now(),now());
INSERT INTO authorities(id,name,creation_date,last_modified_date) VALUES (19,'DELETE_ENQUIRY',now(),now());

insert into roles_authorities (role_id,authority_id) values (13,17);
insert into roles_authorities (role_id,authority_id) values (14,17);
insert into roles_authorities (role_id,authority_id) values (15,17);

insert into roles_authorities (role_id,authority_id) values (3,18);
insert into roles_authorities (role_id,authority_id) values (13,18);
insert into roles_authorities (role_id,authority_id) values (14,18);
insert into roles_authorities (role_id,authority_id) values (15,18);

insert into roles_authorities (role_id,authority_id) values (13,19);
insert into roles_authorities (role_id,authority_id) values (14,19);
insert into roles_authorities (role_id,authority_id) values (15,19);