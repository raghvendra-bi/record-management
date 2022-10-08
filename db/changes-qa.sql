-- As on 29/11/2021
INSERT INTO authorities (`id`, `name`) VALUES ('28', 'GM_CONTENT_CRUD');
insert into roles_authorities(role_id,authority_id) values(2,28);

-- As on 01/12/2021
alter table users add column created_on_date datetime;
alter table users_aud add column created_on_date datetime;
update users  set created_on_date=last_modified_date where id>=1;
update gm_auth_pp.users u join gm_order_pp.login l on l.LoginId=u.id 
set u.created_on_date=l.CreatedOn where l.LoginId<26000;

-- As on 28/01/2022
INSERT INTO authorities (`id`, `name`) VALUES ('60', 'CREATE_CM_TREATMENT_CATEGORY');
insert into roles_authorities(role_id,authority_id) values(2,60);
INSERT INTO authorities (`id`, `name`) VALUES ('61', 'UPDATE_CM_TREATMENT_CATEGORY');
insert into roles_authorities(role_id,authority_id) values(2,61);
INSERT INTO authorities (`id`, `name`) VALUES ('62', 'DELETE_CM_TREATMENT_CATEGORY');
insert into roles_authorities(role_id,authority_id) values(2,62);

-- as on 02/03/2022
insert into roles(id,name) values(16,'GM_CASE_MANAGER');

-- as on 18/07/2022
insert into roles(id,name) values(17,'CONTENT_MANAGER');
insert into roles_authorities(role_id, authority_id) values(17,21);

insert into roles(id,name) values(18,'EXECUTIVE');
insert into roles_authorities(role_id, authority_id) values(18, 17) ;
insert into roles_authorities(role_id, authority_id) values(18, 18) ;
insert into roles_authorities(role_id, authority_id) values(18, 20) ;
insert into roles_authorities(role_id, authority_id) values(18, 22) ;
insert into roles_authorities(role_id, authority_id) values(18, 24) ;
insert into roles_authorities(role_id, authority_id) values(18, 25) ;
insert into roles_authorities(role_id, authority_id) values(18, 26) ;
insert into roles_authorities(role_id, authority_id) values(18, 52) ;
insert into roles_authorities(role_id, authority_id) values(18, 56) ;
insert into roles_authorities(role_id, authority_id) values(18, 57) ;
insert into roles_authorities(role_id, authority_id) values(18, 58) ;
insert into roles_authorities(role_id, authority_id) values(18, 59) ;
insert into roles_authorities(role_id, authority_id) values(18, 60) ;
update oauth_client_details set scope="USER,ADMIN,CONTENT_MANAGER,EXECUTIVE" where client_id="gm-client";

insert into roles_authorities(role_id, authority_id) values(18, 23) ;

-- as on 02/08/2022
insert into roles(id,name) values(19,'ENQUIRY_MANAGER');

insert into roles_authorities(role_id, authority_id) values(19, 17) ;
insert into roles_authorities(role_id, authority_id) values(19, 18) ;
insert into roles_authorities(role_id, authority_id) values(19, 20) ;
insert into roles_authorities(role_id, authority_id) values(19, 22) ;
insert into roles_authorities(role_id, authority_id) values(19, 56) ;
insert into roles_authorities(role_id, authority_id) values(19, 57) ;
insert into roles_authorities(role_id, authority_id) values(19, 58) ;
insert into roles_authorities(role_id, authority_id) values(19, 59) ;
insert into roles_authorities(role_id, authority_id) values(19, 60) ;
update oauth_client_details set scope="USER,ADMIN,CONTENT_MANAGER,EXECUTIVE,ENQUIRY_MANAGER" where client_id="gm-client";

insert into roles_authorities(role_id, authority_id) values(19, 23) ;

