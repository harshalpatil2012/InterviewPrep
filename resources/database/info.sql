SELECT c.customer_id,
       c.name,
       c.email,
       c.country,
       c.city,
       su.software_product,
       SUM(su.hours_used) AS total_hours_used
FROM customers c
         JOIN
     software_usage su ON c.customer_id = su.customer_id
GROUP BY c.customer_id,
         c.name,
         c.email,
         c.country,
         c.city,
         su.software_product
ORDER BY c.customer_id,
         su.software_product;
