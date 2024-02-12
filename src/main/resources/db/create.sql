CREATE TABLE categories( 
id SERIAL PRIMARY KEY,
categorie CHAR(64) NOT NULL
);

CREATE TABLE states( 
id SERIAL PRIMARY KEY,
state CHAR(64) NOT NULL
);

CREATE TABLE rules(
id SERIAL PRIMARY KEY,
rules_user CHAR(64) NOT NULL
);

CREATE TABLE roles(
id SERIAL PRIMARY KEY,
job_title CHAR(64) NOT NULL
);

CREATE TABLE rol_and_rul(
id SERIAL PRIMARY KEY,
rules_id INT REFERENCES rules(id),
roles_id INT REFERENCES roles(id)	
);

CREATE TABLE users(
id SERIAL PRIMARY KEY,
first_name CHAR(64) NOT NULL,
last_name CHAR(64) NOT NULL,
roles_id INT REFERENCES roles(id)
);

CREATE TABLE items( 
id SERIAL PRIMARY KEY,
name CHAR(64) NOT NULL,
users_id INT REFERENCES users(id),
states_id INT REFERENCES states(id),
categories_id INT REFERENCES categories(id)
);

CREATE TABLE comments( 
id SERIAL PRIMARY KEY,
comment_text CHAR(500) NOT NULL,
items_id INT REFERENCES items(id)
);

CREATE TABLE attach( 
id SERIAL PRIMARY KEY,
file INT NOT NULL,
items_id INT REFERENCES items(id)
);

