CREATE TABLE if not exists users (
  "email" VARCHAR(255) NOT NULL,
   "password" VARCHAR(255) NOT NULL,
   "role" VARCHAR(255)NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (email)
);