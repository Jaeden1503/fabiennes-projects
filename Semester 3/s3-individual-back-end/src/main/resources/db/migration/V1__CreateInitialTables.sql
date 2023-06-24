CREATE TABLE users
(
    id         int          NOT NULL AUTO_INCREMENT,
    username   varchar(36)  NOT NULL,
    email      varchar(254) NOT NULL,
    password   varchar(100)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE searchpost
(
    id            int            NOT NULL AUTO_INCREMENT,
    title         varchar(254)   NOT NULL,
    description   varchar(15000) NOT NULL,
    searchingband bit            NOT NULL,
    date          date           NOT NULL,
    instrument    varchar(100)   NOT NULL,
    province      varchar(64)    NOT NULL,
    user_id       int            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE comment
(
    id            int            NOT NULL AUTO_INCREMENT,
    description   varchar(15000) NOT NULL,
    date          date           NOT NULL,
    user_id       int            NOT NULL,
    searchpost_id int            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (searchpost_id) REFERENCES searchpost(id)
);

CREATE TABLE user_role
(
    id        int         NOT NULL AUTO_INCREMENT,
    user_id   int         NOT NULL,
    role_name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES users (id)
);