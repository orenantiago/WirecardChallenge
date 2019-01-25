CREATE TABLE clients (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO clients VALUES (1, "john", "doe");
INSERT INTO clients VALUES (2, "jane", "doe");