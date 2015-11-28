
CREATE TABLE departments (
  department_id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(15)  NOT NULL,
  created datetime  NOT NULL,
  created_by varchar(255)  NOT NULL,
  modified datetime  NOT NULL,
  modified_by varchar(255)  NOT NULL,
  UNIQUE KEY department_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE territories (
  territory_id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(64)  NOT NULL,
  created datetime  NOT NULL,
  created_by varchar(255)  NOT NULL,
  modified datetime  NOT NULL,
  modified_by varchar(255)  NOT NULL,
  UNIQUE KEY territory_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE employees (
  employee_id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name varchar(64)  NOT NULL,
  last_name varchar(64)  NOT NULL,
  title varchar(64)  NOT NULL,
  date_of_birth datetime  NOT NULL,
  date_of_hire datetime  NOT NULL,
  address varchar(128)  NOT NULL,
  city varchar(64)  NOT NULL,
  state varchar(2)  NOT NULL,
  postal_code varchar(10)  NOT NULL,
  home_phone varchar(10)  NOT NULL,
  supervisor_id bigint(20)  NULL,
  department_id bigint(20)  NOT NULL,
  created datetime  NOT NULL,
  created_by varchar(255) NOT  NULL,
  modified datetime  NOT NULL,
  modified_by varchar(255)  NOT NULL,
  CONSTRAINT FOREIGN KEY (supervisor_id) REFERENCES employees (employee_id),
  CONSTRAINT FOREIGN KEY (department_id) REFERENCES departments (department_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE employee_territories (
  employee_territory_id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  employee_id bigint(20)  NOT NULL,
  territory_id bigint(20)  NOT NULL,
  CONSTRAINT FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
  CONSTRAINT FOREIGN KEY (territory_id) REFERENCES territories (territory_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE entity_history (
  entity_history_id bigint(20) NOT NULL AUTO_INCREMENT,
  api_version int(11) NOT NULL,
  entity_id bigint(20) NOT NULL,
  entity_type varchar(255) NOT NULL,
  history_action varchar(255) NOT NULL,
  serialized_entry longtext,
  timestamp datetime NOT NULL,
  user varchar(255) NOT NULL,
  PRIMARY KEY (entity_history_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


