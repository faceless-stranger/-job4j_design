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

CREATE OR REPLACE FUNCTION function_nalog() - Cначала создаем функцию
RETURNS TRIGGER AS $$
Клауза RETURNS TRIGGER указывает тип возвращаемого значения функции как trigger, который представляет собой тип данных, представляющий значение триггера.
Синтаксис AS $$ используется для определения тела функции с использованием строк в долларовых кавычках, что позволяет использовать одинарные кавычки и другие специальные символы без экранирования.
BEGIN
    UPDATE products - обновить продукты
    SET price = price * 1.2 устанавливаем новый ценник
    WHERE id = (SELECT id FROM inserted); приравнение к id
	RETURN NEW; возвращаем новое значение 
END;
$$
 LANGUAGE 'plpgsql';


create trigger nalog_triger тут создаем сам наш тригер 
    after insert сработывание после insert 
    on products в таблице продукт
    referencing new table as  Это означает, что в триггере можно ссылаться на новую таблицу, которая будет использоваться для вставки данных, и называть ее inserted. ->
                    inserted   Это позволяет получить доступ к данным, которые будут вставлены в таблицу, и использовать их в триггере. Например, в триггере перед вставкой данных можно использовать эти данные для вычисления налога и изменить цену товара до вставки
    for each statement -устанавливаем работу по строковую или на все строки 
    execute procedure function_nalog();	ссылка на функцию 

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
