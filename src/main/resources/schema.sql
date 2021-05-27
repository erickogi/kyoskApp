DROP TABLE IF EXISTS config;


CREATE TABLE config
(
    id  BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(256),
    metadata TEXT
);
