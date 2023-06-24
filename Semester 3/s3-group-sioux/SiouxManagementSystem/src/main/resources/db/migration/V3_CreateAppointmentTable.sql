CREATE TABLE appointment
(
    id   int     NOT NULL AUTO_INCREMENT,
    start_time time NOT NULL,
    end_time time NOT NULL
    date date NOT NULL,
    guest_id int NOT NULL ,
    employee_id int NOT NULL ,
    PRIMARY KEY (id),
    UNIQUE (guest_id, employee_id)
    FOREIGN KEY (guest_id) REFERENCES guest (id)
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);