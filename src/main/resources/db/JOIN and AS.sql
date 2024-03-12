CREATE TABLE author(
author_id SERIAL PRIMARY KEY,
name_author VARCHAR(50),
id_athor VARCHAR(50)
);

INSERT INTO author (name_author, id_athor)
VALUES('Александр Пушкин', 'AP007'),
('Лев Толстой', 'LT008'),
('Федор Достоевский', 'FD009'),
('Антон Чехов', 'AC010'),
('Николай Гоголь', 'NG011'),
('Иван Тургенев', 'IT012'),
('Михаил Булгаков', 'MB013'),
('Александр Солженицын', 'AS014'),
('Владимир Набоков', 'VN015'),
('Сергей Есенин', 'SE016');

SELECT * FROM author;

CREATE TABLE book(
book_id SERIAL PRIMARY KEY,
name_book VARCHAR(50),
name_author VARCHAR(50),
id_athor VARCHAR(50)
);

INSERT INTO book (name_book, id_athor)
VALUES
('Евгений Онегин', 'AP007'),
('Война и мир', 'LT008'),
('Преступление и наказание', 'FD009'),
('Дядя Ваня', 'AC010'),
('Мертвые души', 'NG011'),
('Отцы и дети', 'IT012'),
('Мастер и Маргарита', 'MB013'),
('Архипелаг ГУЛАГ', 'AS014'),
('Лолита', 'VN015'),
('Первый разговор', 'SE016');

SELECT * FROM book;

SELECT name_book AS Название, a.name_author AS Автор
FROM book AS b
JOIN author AS a ON b.id_athor = a.id_athor 
;