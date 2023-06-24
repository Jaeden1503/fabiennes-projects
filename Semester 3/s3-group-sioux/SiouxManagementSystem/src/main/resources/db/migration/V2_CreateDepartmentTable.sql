CREATE TABLE department
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

