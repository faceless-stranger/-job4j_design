CREATE TABLE coffee (
id serial PRIMARY KEY,
product_name VARCHAR(60), 
typ VARCHAR(60) NOT NULL,
price VARCHAR(60) NOT NULL
);
--------------------------------------------------------
SELECT id, product_name, typ, price
	FROM public.coffee;
--------------------------------------------------------
INSERT INTO coffee (id, product_name, typ, price )
VALUES (1, 'coffee', 'cappuccino', '300' ),
(2, 'lemonade', 'cola', '200' ),
(3, 'milkshake', 'vanil', '350' );
--------------------------------------------------------
UPDATE coffee SET
typ = 'vanilla'
WHERE id = 3
--------------------------------------------------------
DELETE FROM coffee