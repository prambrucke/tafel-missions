
CREATE TABLE critical(
  id integer not null,
  name text not null,
  description text not null,
  created_at timestamp without time zone,
  updated_at timestamp without time zone,
  primary key (id)
);


INSERT INTO critical(id,name,description,created_at,updated_at)
values (1,'HOT_FIX','Issues that has criticality hot fix','2019-03-09 19:10:25-07','2019-03-09 19:10:25-07'),
(2,'CRITICAL','Issues that has criticality critical','2019-03-09 19:10:25-07','2019-03-09 19:10:25-07'),
(3,'NORMAL','Issues that has criticality normal','2019-03-09 19:10:25-07','2019-03-09 19:10:25-07');