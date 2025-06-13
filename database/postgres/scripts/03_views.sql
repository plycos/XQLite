CREATE
OR REPLACE VIEW order_details AS
SELECT o.id   AS order_id,
       u.username,
       u.email,
       p.name AS product,
       o.amount
FROM orders o
         JOIN users u ON o.user_id = u.id
         JOIN products p ON o.product_id = p.id;
