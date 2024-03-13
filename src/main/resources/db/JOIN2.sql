CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments (id)
);

INSERT INTO departments (name) VALUES
('Отдел продаж'),
('Отдел маркетинга'),
('Отдел разработки');

INSERT INTO employees (name, department_id) VALUES
('Иван Иванов', 1),
('Петр Петров', 1),
('Мария Сидорова', 2),
('Алексей Николаев', 3),
('Елена Козлова', 3);

SELECT departments.name, employees.name
FROM departments 
LEFT JOIN employees ON employees.department_id = departments.id;

SELECT departments.name, employees.name
FROM departments 
LEFT JOIN employees ON employees.department_id = departments.id 
WHERE employees.name IS NOT NULL; 

SELECT departments.name, employees.name
FROM employees 
RIGHT JOIN departments ON employees.department_id = departments.id 
WHERE employees.name IS NOT NULL; 

SELECT departments.name, employees.name
FROM employees 
FULL JOIN departments ON employees.department_id = departments.id;

SELECT departments.name, employees.name
FROM employees 
CROSS JOIN departments;

CREATE TABLE teens (
    name VARCHAR(255),
    gender VARCHAR(10)
);

INSERT INTO teens (name, gender) VALUES
('Вася', 'М'),
('Петя', 'М'),
('Маша', 'Ж'),
('Катя', 'Ж');

SELECT t1.name AS Первый, t2.name AS Второй
FROM teens AS t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender AND t1.name < t2.name;


