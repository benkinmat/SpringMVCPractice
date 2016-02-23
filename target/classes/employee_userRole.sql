-- Spring MVC with Hibernate

CREATE TABLE EMPLOYEE(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    joining_date DATE NOT NULL,
    salary DOUBLE NOT NULL,
    ssn VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE USER_ROLE(
	id INT NOT NULL AUTO_INCREMENT,
    role VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE EMP_USER_ROLE(
	emp_id INT NOT NULL,
    user_role_id INT NOT NULL,
    PRIMARY KEY (emp_id, user_role_id),
    CONSTRAINT fk_emp FOREIGN KEY (emp_id) REFERENCES EMPLOYEE (id),
    CONSTRAINT fk_user_role FOREIGN KEY (user_role_id) REFERENCES USER_ROLE (id)
);

SELECT * FROM springmvc.employee;
SELECT * FROM springmvc.user_role;
SELECT * FROM springmvc.emp_user_role;

INSERT INTO user_role VALUES (1, 'ADMIN');
INSERT INTO user_role VALUES (2, 'USER');
INSERT INTO user_role VALUES (3, 'DBA');

DROP TABLE springmvc.employee;
DROP TABLE springmvc.user_role;
DROP TABLE springmvc.emp_user_role;

SHOW ENGINE INNODB STATUS;
