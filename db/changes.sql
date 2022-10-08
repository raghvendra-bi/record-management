
--create schema for login activity
create table user_activity (id bigint not null auto_increment, address varchar(255), attempts integer, city varchar(255), country varchar(255), create_date datetime, ip_address varchar(255), latitude varchar(255), login_date datetime, login_status varchar(255), longitude varchar(255), region varchar(255), type varchar(255), user_id bigint, user_name varchar(255), primary key (id));

--added configuration
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'BASE_URL_IP_STACK', 'GM_AUTH', 'http://api.ipstack.com',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'IP_STACK_KEY', 'GM_AUTH', '1dc9953a9ee4c5a92eec1a8e36e795fe',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'GMP_BASE_URL', 'GM_AUTH', 'https://maps.googleapis.com',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'GMP_API', 'GM_AUTH', '/maps/api/geocode',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'GMP_KEY', 'GM_AUTH', 'AIzaSyBEPYV2hnfwD8MM7fSkQ91IFtYifIIZ-go',1,1);

