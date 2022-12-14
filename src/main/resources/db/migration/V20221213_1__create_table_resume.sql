CREATE TABLE if not exists resume (
  id VARCHAR(20) NOT NULL,
   name VARCHAR(80) NOT NULL,
   birth_date date NOT NULL,
   gender INTEGER NOT NULL,
   phone VARCHAR(16),
   cell_phone VARCHAR(16) NOT NULL,
   email VARCHAR(80) NOT NULL,
   postal_code VARCHAR(12) NOT NULL,
   district VARCHAR(80) NOT NULL,
   job_option_one VARCHAR(50) NOT NULL,
   job_option_two VARCHAR(50),
   job_option_three VARCHAR(50),
   schooling VARCHAR(40) NOT NULL,
   CONSTRAINT pk_resume PRIMARY KEY (id)
);