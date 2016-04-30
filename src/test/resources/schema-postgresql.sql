CREATE TABLE IF NOT EXISTS sample
(
	id integer NOT NULL,
	name varchar(32) NOT NULL,
	mail varchar(256) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	created_at_timezone timestamp with time zone NOT NULL,
	CONSTRAINT pk_sample PRIMARY KEY (id)
);
