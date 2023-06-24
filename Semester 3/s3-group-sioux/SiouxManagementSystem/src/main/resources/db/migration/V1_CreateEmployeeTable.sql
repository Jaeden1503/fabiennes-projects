CREATE TABLE employee
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    email_address varchar(100) NOT NULL,
    department_id int NOT NULL ,
    PRIMARY KEY (id),
    UNIQUE (name, email_address, department_id)
    FOREIGN KEY (department_id) REFERENCES department (id)
);