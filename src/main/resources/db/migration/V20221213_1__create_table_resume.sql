CREATE TABLE if not exists resume (
  cid VARCHAR(20) NOT NULL,
   "first_name" VARCHAR(20) NOT NULL,
   "last_name" VARCHAR(60) NOT NULL,
   birth_date date NOT NULL,
   gender VARCHAR(3) NOT NULL,
   phone VARCHAR(16),
   cell_phone VARCHAR(16) NOT NULL,
   email VARCHAR(80) NOT NULL,
   linkedin VARCHAR(120),
   postal_code VARCHAR(12) NOT NULL,
   district VARCHAR(80) NOT NULL,
   city VARCHAR(80) NOT NULL,
   schooling VARCHAR(40) NOT NULL,
   job_option_one VARCHAR(50) NOT NULL,
   job_option_two VARCHAR(50),
   job_option_three VARCHAR(50),
   CONSTRAINT pk_resume PRIMARY KEY (cid)
);