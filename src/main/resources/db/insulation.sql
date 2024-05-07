\c insulation
BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
CREATE TABLE IF NOT EXISTS cars (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    year INTEGER,
    color VARCHAR(20),
    mileage INTEGER
);
INSERT INTO cars (brand, model, year, color, mileage) VALUES
('Toyota', 'Corolla', 2018, 'White', 35000),
('Honda', 'Civic', 2017, 'Black', 42000),
('Ford', 'Focus', 2019, 'Blue', 28000),
('Chevrolet', 'Cruze', 2016, 'Red', 50000),
('Nissan', 'Altima', 2020, 'Silver', 15000);

SELECT * FROM cars;

UPDATE cars
SET mileage = 30000
WHERE id = 3;

UPDATE cars
SET mileage = 40000
WHERE id = 3;