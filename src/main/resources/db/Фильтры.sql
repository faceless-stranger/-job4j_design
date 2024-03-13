CREATE TABLE types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type_id INT REFERENCES types (id),
    expired_date DATE,
    price FLOAT
);

INSERT INTO types (name) VALUES
('Сыр'),
('Мясо'),
('Овощи'),
('Фрукты'),
('Молочная_продукция');

INSERT INTO products (name, type_id, expired_date, price) VALUES
('Сыр Моцарелла', 1, '2025-01-01', 250.00),
('Сыр Чеддер', 1, '2025-12-31', 300.00),
('Сыр Рокфор', 1, '2025-12-30', 400.00),
('Свинина', 2, '2024-11-30', 450.00),
('Говядина', 2, '2024-10-30', 500.00),
('Курица', 2, '2024-09-30', 350.00),
('Помидоры', 3, '2024-08-30', 150.00),
('Баклажаны', 3, '2024-07-30', 200.00),
('Яблоки', 4, '2024-06-30', 100.00),
('Молоко', 5, '2024-01-30', 110.00),
('Мороженное Большой папа', 5, '2024-03-30', 80.00),
('Апельсины', 4, '2024-05-30', 120.00);

SELECT *
FROM products
WHERE type_id = (
	SELECT id
	FROM types 
	WHERE name = 'Сыр'
);

SELECT *
FROM products 
WHERE LOWER(name) LIKE '%мороженное%';

SELECT name, expired_date
FROM products
WHERE CURRENT_DATE >= expired_date;

SELECT name, price
FROM products
WHERE price = (
	SELECT price FROM products
	ORDER BY price DESC
	LIMIT 1
);

SELECT types.name, COUNT(products.type_id) AS Количество 
FROM types
JOIN products ON types.id = products.type_id
GROUP BY types.name;

SELECT products.name
FROM products
JOIN types ON types.id = products.type_id
WHERE types.name = 'Сыр' OR types.name = 'Молоко';

SELECT types.name, COUNT(type_id) AS Количество
FROM types
JOIN products ON types.id = products.type_id
GROUP BY types.name
HAVING COUNT(type_id) < 10;

SELECT products.name, types.name
FROM products
JOIN types ON types.id = products.type_id;



