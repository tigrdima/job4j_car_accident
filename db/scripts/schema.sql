CREATE TABLE if not exists accident_type (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE if not exists rule (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE if not exists accident (
  id serial primary key,
  name varchar(2000),
  text varchar,
  address varchar,
  accident_type_id int references accident_type(id)
);

CREATE TABLE if not exists accident_rule (
  rule_id int references rule(id),
  accident_id int references accident(id)

);




