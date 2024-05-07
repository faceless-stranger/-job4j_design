create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

INSERT INTO products (name, producer, count, price) VALUES
('Товар1', 'Производитель1', 10, 100),
('Товар2', 'Производитель2', 20, 200),
('Товар3', 'Производитель3', 30, 300),
('Товар4', 'Производитель4', 40, 400),
('Товар5', 'Производитель5', 50, 500),
('Товар6', 'Производитель6', 60, 600),
('Товар7', 'Производитель7', 70, 700),
('Товар8', 'Производитель8', 80, 800),
('Товар9', 'Производитель9', 90, 900),
('NA', 'NA', 0, 0);

CREATE OR REPLACE PROCEDURE delete_id(d_id integer)
LANGUAGE 'plpgsql'
as $$
	BEGIN
	DELETE FROM products
	WHERE d_id = id;
	END;
	$$;
	
CALL delete_id(1);

CREATE OR REPLACE FUNCTION delete_zero()
returns void
LANGUAGE 'plpgsql'
as $$
	BEGIN
	DELETE FROM products
	WHERE count = 0;
	END;
	$$;

SELECT delete_zero();
