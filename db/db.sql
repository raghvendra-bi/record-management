
create table authorities (id bigint not null auto_increment, creation_date datetime, last_modified_date datetime, name varchar(255), created_by_id bigint, last_modified_by_id bigint, primary key (id)) engine=InnoDB ;

create table authorities_aud (id bigint not null, rev integer not null, revtype tinyint, creation_date datetime, last_modified_date datetime, name varchar(255), created_by_id bigint, last_modified_by_id bigint, primary key (id, rev)) engine=InnoDB ;

create table oauth_access_token (guid varchar(255) not null, client_id varchar(255), created_on datetime, expiration_time varchar(255), user_id bigint, primary key (guid)) engine=InnoDB ;

create table revinfo (rev integer not null auto_increment, revtstmp bigint, primary key (rev)) engine=InnoDB ;

create table roles (id bigint not null auto_increment, name varchar(15), primary key (id)) engine=InnoDB ;

create table roles_aud (id bigint not null, rev integer not null, revtype tinyint, name varchar(255), primary key (id, rev)) engine=InnoDB ;

create table roles_authorities (role_id bigint not null, authority_id bigint not null) engine=InnoDB ;

create table roles_authorities_aud (rev integer not null, role_id bigint not null, authority_id bigint not null, revtype tinyint, primary key (rev, role_id, authority_id)) engine=InnoDB ;

create table user_activity (id bigint not null auto_increment, address varchar(255), attempts integer, city varchar(255), country varchar(255), create_date datetime, ip_address varchar(255), latitude varchar(255), longitude varchar(255), region varchar(255), status varchar(255), type varchar(255), user_id bigint, user_name varchar(255), primary key (id)) engine=InnoDB ;

create table user_passwords (id bigint not null, password varchar(255), primary key (id)) engine=InnoDB ;

create table users (id bigint not null auto_increment, account_expired bit not null, account_locked bit not null, attempts bigint, client_id varchar(255), created_on_date datetime, credentials_expired bit not null, domain varchar(255), email varchar(255), enabled bit not null, facebook_id varchar(255), facebook_image varchar(255), first_name varchar(255), is_auto_generated bit, is_deleted bigint not null, is_email_verfied bit, is_phone_verfied bit, last_modified_date datetime, last_name varchar(255), login_name varchar(255), password varchar(255), phone varchar(255), phone_code varchar(255), profile_image_id varchar(255), primary key (id)) engine=InnoDB ;

create table users_aud (id bigint not null, rev integer not null, revtype tinyint, account_expired bit, account_locked bit, attempts bigint, client_id varchar(255), created_on_date datetime, credentials_expired bit, domain varchar(255), email varchar(255), enabled bit, facebook_id varchar(255), facebook_image varchar(255), first_name varchar(255), is_auto_generated bit, is_deleted bigint, is_email_verfied bit, is_phone_verfied bit, last_modified_date datetime, last_name varchar(255), login_name varchar(255), password varchar(255), phone varchar(255), phone_code varchar(255), profile_image_id varchar(255), primary key (id, rev)) engine=InnoDB ;

create table users_roles (user_id bigint not null, role_id bigint not null) engine=InnoDB ;

create table users_roles_aud (rev integer not null, user_id bigint not null, role_id bigint not null, revtype tinyint, primary key (rev, user_id, role_id)) engine=InnoDB ;

alter table users drop index user_phone_code_phone_clientId_isDeleted_unique ;

alter table users add constraint user_phone_code_phone_clientId_isDeleted_unique unique (phone_code, phone, client_id, is_deleted) ;

alter table users drop index user_email_clientId_isDeleted_unique ;

alter table users add constraint user_email_clientId_isDeleted_unique unique (email, client_id, is_deleted) ;

alter table users drop index user_loginName_client_id_is_deleted ;

alter table users add constraint user_loginName_client_id_is_deleted unique (login_name, client_id, is_deleted) ;

alter table users drop index user_facebookId_clientId_unique ;

alter table users add constraint user_facebookId_clientId_unique unique (facebook_id, client_id) ;

alter table authorities add constraint FKd8i6us1vdbywtpup9hecnoo85 foreign key (created_by_id) references users (id) ;

alter table authorities add constraint FKljxr0grddqedx3rap5rtqw6bh foreign key (last_modified_by_id) references users (id) ;

alter table authorities_aud add constraint FKewrx98i3dwo3cgtjehkqh348f foreign key (rev) references revinfo (rev) ;

alter table roles_aud add constraint FKt0mnl3rej2p0h9gxnbalf2kdd foreign key (rev) references revinfo (rev) ;

alter table roles_authorities add constraint FKt69njxcgfcto5wcrd9ocmb35h foreign key (authority_id) references authorities (id) ;

alter table roles_authorities add constraint FKq3iqpff34tgtkvnn545a648cb foreign key (role_id) references roles (id) ;

alter table roles_authorities_aud add constraint FK3e3cm3mdi8enyv01co2w57c7n foreign key (rev) references revinfo (rev) ;

alter table users_aud add constraint FKc4vk4tui2la36415jpgm9leoq foreign key (rev) references revinfo (rev) ;

alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id) ;

alter table users_roles add constraint fk_role_user foreign key (user_id) references users (id) ;

alter table users_roles_aud add constraint FKktxqr55ntd0j2i228uj8sq6j9 foreign key (rev) references revinfo (rev) ;


