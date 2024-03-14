CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

CREATE TABLE salaries (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employees(id),
    amount DECIMAL(10, 2),
    date DATE
);

INSERT INTO departments (name) VALUES
('HR'),
('Finance'),
('IT');

INSERT INTO employees (name, department_id) VALUES
('John', 1),
('Jane', 2),
('Mike', 3);

INSERT INTO salaries (employee_id, amount, date) VALUES
(1, 5000, '2022-01-01'),
(2, 6000, '2022-01-01'),
(3, 7000, '2022-01-01');

CREATE VIEW employee_salaries AS
SELECT e.name AS employee_name, d.name AS department_name, s.amount, s.date
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN salaries s ON e.id = s.employee_id;

SELECT * FROM employee_salaries;




