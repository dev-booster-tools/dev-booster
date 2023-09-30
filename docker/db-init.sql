CREATE TABLE IF NOT EXISTS users
(
    id       serial PRIMARY KEY,
    email    varchar(128) UNIQUE  NOT NULL,
    password varchar(100)         NOT NULL,
    role     varchar(32)          NOT NULL,
    enabled  boolean DEFAULT true NOT NULL
);

INSERT INTO users(id, email, password, role, enabled)
VALUES (1, 'admin@mail.ru', '$2a$12$WKygJaZy5TwAWlv2WjaEz.OL.JITAE/LdDRr5DUnl4fUYJl.xAHmi', 'ROLE_ADMIN', true),
       (2, 'user@mail.ru', '$2a$12$WKygJaZy5TwAWlv2WjaEz.OL.JITAE/LdDRr5DUnl4fUYJl.xAHmi', 'ROLE_USER', true)