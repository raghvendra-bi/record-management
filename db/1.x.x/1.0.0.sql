-- remove foreign keys from users
ALTER TABLE users DROP FOREIGN KEY fk_user_t_clinicId_clinic_t_id;
ALTER TABLE users DROP FOREIGN KEY fk_user_t_staffId_staff_t_id;
-- set defalut values
ALTER TABLE users MODIFY COLUMN enabled bit(1) DEFAULT 1 NOT NULL;
ALTER TABLE users MODIFY COLUMN is_deleted bit(1) DEFAULT 0 NOT NULL;
-- delete column
ALTER TABLE users DROP COLUMN clinic_id;
ALTER TABLE users DROP COLUMN staff_id;
ALTER TABLE users DROP COLUMN gomedii_id;
ALTER TABLE users DROP COLUMN is_owner;
ALTER TABLE users DROP COLUMN phone;
ALTER TABLE users DROP COLUMN country_iata_code;
ALTER TABLE users DROP COLUMN is_terms_policy_agreed;

ALTER TABLE users_aud DROP COLUMN is_terms_policy_agreed;
ALTER TABLE users_aud DROP COLUMN phone;
ALTER TABLE users_aud DROP COLUMN country_iata_code;
ALTER TABLE users_aud DROP COLUMN clinic_id;
ALTER TABLE users_aud DROP COLUMN staff_id;
ALTER TABLE users_aud DROP COLUMN gomedii_id;
ALTER TABLE users_aud DROP COLUMN is_owner;

-- create oauth_access_token table
CREATE TABLE oauth_access_token (
  `guid` varchar(256) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `expiration_time` varchar(255) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT now() ,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guid`)
);
-- update oauth_client_details
UPDATE oauth_client_details SET `scope`='DOCTOR,ADMIN,SITE_ADMIN,SUPPORT_STAFF' WHERE client_id='gmc-client' and resource_ids='api';

-- update website/app authorities
UPDATE oauth_client_details SET authorities='DOCTOR,ADMIN,SITE_ADMIN,SUPPORT_STAFF' WHERE client_id='gmc-client' and resource_ids='api';

-- update one more grant_type for service token
UPDATE oauth_client_details SET authorized_grant_types='password,authorization_code,refresh_token,implicit,client_credentials' WHERE client_id='gmc-client' and resource_ids='api';

-- =====================================================================
-- ====================================================================================================
-- created authorities for SITE_ADMIN role
-- ====================================================================================================

INSERT INTO authorities (id, name) values (6, 'TEMPLATE_READ'), (7, 'TEMPLATE_CREATE'), (8,'TEMPLATE_UPDATE'), (9,'TEMPLATE_DELETE');
INSERT INTO roles_authorities (role_id,authority_id) values (1,6), (1,7),(1,8),(1,9);

alter table users add column last_modified_date datetime;
alter table users_aud add column last_modified_date datetime;

ALTER TABLE users MODIFY COLUMN is_deleted tinyint(1) DEFAULT 0 NOT NULL;
ALTER TABLE users_aud MODIFY COLUMN is_deleted tinyint(1) DEFAULT 0 NOT NULL;

-- ================== configuration changes =================================

CREATE TABLE `configuration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `gmc_key` varchar(255) NOT NULL,
  `value` longtext,
  `created_by_id` bigint(20) DEFAULT NULL,
  `last_modified_by_id` bigint(20) DEFAULT NULL,
  `service_key` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serviceKey_gmcKey_unique` (`gmc_key`,`service_key`),
  KEY `FKm2d187oy6jchl140t1c7bv2xx` (`created_by_id`),
  KEY `FK3ujtdd5tm5watr7fhv1mpx8qf` (`last_modified_by_id`),
  CONSTRAINT `FK3ujtdd5tm5watr7fhv1mpx8qf` FOREIGN KEY (`last_modified_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKm2d187oy6jchl140t1c7bv2xx` FOREIGN KEY (`created_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `configuration_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `gmc_key` varchar(255) DEFAULT NULL,
  `value` longtext,
  `created_by_id` bigint(20) DEFAULT NULL,
  `last_modified_by_id` bigint(20) DEFAULT NULL,
  `service_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKcrnf8x5ligvryouje42b2moaq` (`rev`),
  CONSTRAINT `FKcrnf8x5ligvryouje42b2moaq` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- add auth base url
insert into configuration (creation_date, last_modified_date, gmc_key, value, created_by_id, last_modified_by_id, service_key) values ('2019-10-18 20:43:42', '2019-10-18 20:43:42', 'AUTH_BASE_URL', 'http://auth-api:8080', 1, 1, 'GM_AUTH');
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, value, created_by_id, last_modified_by_id, service_key) VALUES(now(), now(), 'GMC_INTERNAL_URL', 'http://clinic-api:8080', 1, 1, 'GM_AUTH');
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, value, created_by_id, last_modified_by_id, service_key) VALUES ('2018-11-27 18:15:28.000', '2018-11-27 18:15:28.000', 'DEV_ACCESS_KEY', 'fsdf87dfh243sdpo34sdffj397', 2, 2, 'GM_AUTH');
