alter table users add column attempts bigint;
alter table users_aud add column attempts bigint;
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'OAUTH_LOGIN_DELAY_MS', 'GM_AUTH', '5000',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'OAUTH_LOGIN_LAST_ATTEMPTS_COUNT', 'GM_AUTH', '5',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'GOOGLE_CAPTCHA_VARIFY_URL', 'GM_AUTH', 'https://www.google.com/recaptcha/api/siteverify',1,1);
INSERT INTO configuration (creation_date, last_modified_date, gmc_key, service_key, value, created_by_id, last_modified_by_id) VALUES (now(), now(), 'GOOGLE_CAPTCHA_SECRET', 'GM_AUTH', '6LcgvMYUAAAAAHPeIzWkllzYhRqwVmLfgnaHAXS8',1,1);
update users set attempts = 0 where attempts is null;
