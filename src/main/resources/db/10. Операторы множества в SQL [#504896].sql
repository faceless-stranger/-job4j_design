--выведите названия всех фильмов, которые сняты по книге
SELECT name FROM movie
INTERSECT
SELECT title FROM book;

-- выведите все названия книг, у которых нет экранизации
SELECT title FROM book
EXCEPT
SELECT name FROM movie;

-- выведите все уникальные названия произведений из таблиц movie и book
SELECT title FROM book
EXCEPT
SELECT name FROM movie
UNION
(SELECT name FROM movie
EXCEPT
SELECT title FROM book);