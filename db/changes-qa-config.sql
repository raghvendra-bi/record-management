-- 29/07/2022

INSERT INTO configuration (`creation_date`, `last_modified_date`, `gmc_key`, `value`, `created_by_id`, `last_modified_by_id`, `is_nullable`, `service_key`)
 VALUES (now(), now(), 'GET_USER_ID_BY_TOKEN_URL', '/api/tokens/{token}/userId', '1', '1', false, 'GM_AUTH');
