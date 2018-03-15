DROP TABLE IF EXISTS memo;
DROP SEQUENCE IF EXISTS memo_id_seq;

CREATE TABLE memo (
  id bigint NOT NULL,
  title varchar(255) NOT NULL,
  description text NOT NULL,
  done boolean DEFAULT false NOT NULL,
  updated timestamp without time zone DEFAULT current_timestamp NOT NULL,
  CONSTRAINT memo_pkey PRIMARY KEY(id)
);

CREATE SEQUENCE memo_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1
NO CYCLE;

ALTER SEQUENCE memo_id_seq OWNED BY memo.id;

ALTER TABLE ONLY memo ALTER COLUMN id SET DEFAULT nextval('memo_id_seq'::regclass);


CREATE TABLE memo (
  id bigserial NOT NULL,
  title varchar(255) NOT NULL,
  description text NOT NULL,
  done boolean DEFAULT false NOT NULL,
  updated timestamp without time zone DEFAULT current_timestamp NOT NULL,
  CONSTRAINT memo_pkey PRIMARY KEY(id)
);
