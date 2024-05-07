password
\c insulation;
begin transaction;
SELECT * FROM cars;
savepoint one;
UPDATE cars SET brand = 'NewBrand' WHERE id = 1;
DELETE FROM cars WHERE id = 1;
rollback to savepoint one;
commit;