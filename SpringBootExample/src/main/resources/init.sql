CREATE TABLE cats.cat
(
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(512) NOT NULL,
  description VARCHAR(2048),
  hungry BOOLEAN NOT NULL
);

CREATE SEQUENCE cat_id_seq START 10000;
