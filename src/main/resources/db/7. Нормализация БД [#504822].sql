CREATE TABLE  client (
	id SERIAL PRIMARY KEY,
	full_name VARCHAR(250) NOT NULL,
	adress VARCHAR(250) NOT NULL,
	sex VARCHAR(12) NOT NULL,
	UNIQUE (id, full_name)
);

insert into client(full_name, adress, sex)
values ('Ольга Егорова', '1-ый Казанский Переулок, д. 14', 'Женский'),
       ('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 'Мужской'),
       ('Иванов Сергей', 'ул. Ленина, д. 7, кв. 130', 'Мужской');
	   
CREATE TABLE movies(
	id SERIAL PRIMARY KEY,
	title VARCHAR(250) NOT NULL
);

insert into movies(title)
values ('Пираты Карибского Моря'),
       ('Матрица: революция'),
       ('Человек, который изменил все'),
       ('Интерстеллар');
	   
CREATE TABLE watched_movies(
    person_id INT REFERENCES client (id),
    movie_id  INT REFERENCES movies (id),
    PRIMARY KEY (person_id, movie_id)
);

insert into watched_movies(person_id,movie_id)
values (1,1),
(2,2),
(2,3),
(3,4);