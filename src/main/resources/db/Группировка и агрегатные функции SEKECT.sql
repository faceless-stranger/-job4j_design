SELECT AVG(price) AS Средняя_Цена
FROM devices;

SELECT people.name, AVG(devices.price) AS price
FROM people
JOIN devices_people dp ON people.id = dp.people_id
JOIN devices ON dp.device_id = devices.id
GROUP BY people.name
HAVING AVG(devices.price) > 5000
ORDER BY name;