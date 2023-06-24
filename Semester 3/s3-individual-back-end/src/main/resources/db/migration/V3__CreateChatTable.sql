CREATE TABLE chat
(
    id         int          NOT NULL AUTO_INCREMENT,
    starter  varchar(36) NOT NULL,
    joiner   varchar(36),
    title    varchar(100)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (title)
);