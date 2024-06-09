CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Алексей', 'Иванов', 34, 'Россия'),
('Мария', 'Петрова', 28, 'Беларусь'),
('Дмитрий', 'Смирнов', 45, 'Казахстан'),
('Екатерина', 'Кузнецова', 22, 'Украина'),
('Сергей', 'Попов', 37, 'Узбекистан'),
('Анна', 'Васильева', 31, 'Армения'),
('Иван', 'Соколов', 29, 'Киргизия'),
('Елена', 'Михайлова', 26, 'Молдова'),
('Павел', 'Новиков', 41, 'Таджикистан'),
('Ольга', 'Федорова', 33, 'Азербайджан');

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Женя', 'Мусалов', 22, 'Россия');

SELECT *
FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES
(5, 1),  
(3, 2),  
(7, 3),  
(2, 5),  
(4, 4); 

SELECT *
FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);
