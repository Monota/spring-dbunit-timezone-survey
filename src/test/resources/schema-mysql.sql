CREATE TABLE IF NOT EXISTS testdb.sample
(
	id integer NOT NULL,
	name varchar(32) NOT NULL,
	mail varchar(256) NOT NULL,
	created_at datetime,
	created_at_timezone timestamp,
	CONSTRAINT pk_sample PRIMARY KEY (id)
);
