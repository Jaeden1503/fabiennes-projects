CREATE TABLE guest
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    license_plate varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);