CREATE TABLE if not exists job_experience (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   cid VARCHAR(20) NOT NULL,
   title VARCHAR(50) NOT NULL,
   start_month INTEGER NOT NULL,
   start_year INTEGER NOT NULL,
   is_current_job BOOLEAN NOT NULL,
   end_month INTEGER,
   end_year INTEGER,
   description VARCHAR(255) NOT NULL,
   CONSTRAINT pk_job_experience PRIMARY KEY (id)
);