CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO car_bodies (name) VALUES
('Седан'),
('Хэтчбек'),
('Кроссовер');

CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO car_engines (name) VALUES
('Бензиновый'),
('Дизельный'),
('Электрический');

CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO car_transmissions (name) VALUES
('Механическая'),
('Автоматическая'),
('Вариатор');

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Camry', 1, 1, 2),
('Volkswagen Golf', 2, 1, 1),
('Tesla Model S', 1, 3, 3);

SELECT cars.name, car_bodies.name, car_engines.name, car_transmissions.name
FROM cars
LEFT JOIN car_bodies ON cars.body_id = car_bodies.id
LEFT JOIN car_engines ON cars.engine_id = car_engines.id
LEFT JOIN car_transmissions ON cars.transmission_id = car_transmissions.id;

SELECT car_bodies.name, cars.name
FROM car_bodies
LEFT JOIN cars ON cars.body_id = car_bodies.id
WHERE cars.name IS NULL;

SELECT car_engines.name AS Двигатель
FROM car_engines
LEFT JOIN cars ON cars.engine_id = car_engines.id
WHERE cars.name IS NULL;

SELECT car_transmissions.name AS Коробка_передач
FROM car_transmissions
LEFT JOIN cars ON cars.transmission_id = car_transmissions.id
WHERE cars.name IS NULL;

