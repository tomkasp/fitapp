INSERT INTO jhi_user
(id, login, password_hash, first_name, last_name, email, activated, lang_key, created_by, last_modified_by, created_date)
VALUES
    (1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System',
        'system@localhost', TRUE, 'en', 'system', 'system', CURRENT_TIMESTAMP()),
    (3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator',
        'admin@localhost', TRUE, 'en', 'system', 'system', CURRENT_TIMESTAMP());


INSERT INTO jhi_authority
(name)
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');


INSERT INTO jhi_user_authority
(user_id, authority_name)
VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_USER'),
    (3, 'ROLE_ADMIN'),
    (3, 'ROLE_USER');

INSERT INTO athlete
(id, name, user_id)
VALUES
    (1, 'Tomasz', 3);
