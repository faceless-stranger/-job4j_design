create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);


INSERT INTO products (name, producer, count, price)
VALUES
    ('iPhone 13 Pro', 'Apple Inc.', 10, 120000),
    ('Galaxy S22 Ultra', 'Samsung Electronics', 5, 110000),
    ('MacBook Pro 16', 'Apple Inc.', 7, 250000),
    ('Surface Pro 8', 'Microsoft', 8, 150000),
    ('PlayStation 5', 'Sony', 3, 50000);

-------------------------------------------------------------------

CREATE OR REPLACE FUNCTION function_nalog()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE products
    SET price = price * 1.2
    WHERE id = (SELECT id FROM inserted);
	RETURN NEW;
END;
$$
 LANGUAGE 'plpgsql';


create trigger nalog_triger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure function_nalog();	

-----------------------------------------------------------------

CREATE OR REPLACE FUNCTION function_nalog_do()
RETURNS TRIGGER AS $$
BEGIN
    NEW.price := NEW.price * 1.2;
    RETURN NEW;
END;
$$
 LANGUAGE 'plpgsql';


create trigger nalog_triger_do
    BEFORE insert
    on products
    for each row
    execute procedure function_nalog_do();

-----------------------------------------------------------------

CREATE OR REPLACE FUNCTION history_function()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
    RETURN NEW;
END;
$$

LANGUAGE plpgsql;

CREATE TRIGGER history_trigger
AFTER INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE history_function();
