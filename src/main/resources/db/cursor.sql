BEGIN;

DECLARE cursor_products SCROLL CURSOR FOR
    SELECT * FROM products;
	
FETCH LAST FROM cursor_products;

FETCH BACKWARD 5 FROM cursor_products;
FETCH BACKWARD 8 FROM cursor_products;
FETCH BACKWARD 5 FROM cursor_products;
FETCH BACKWARD FROM cursor_products;

FETCH LAST FROM cursor_products;
MOVE BACKWARD 5 FROM cursor_products;
MOVE BACKWARD 8 FROM cursor_products;
MOVE BACKWARD 5 FROM cursor_products;
MOVE BACKWARD 1 FROM cursor_products;
close cursor_products;
commit;
