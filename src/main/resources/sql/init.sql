CREATE TABLE url_dao (
  id          INTEGER NOT NULL,
  code        VARCHAR(255),
  create_time DATETIME,
  is_deleted  INTEGER,
  update_time DATETIME,
  url         VARCHAR(255),
  PRIMARY KEY (id)
)ENGINE = innodb
