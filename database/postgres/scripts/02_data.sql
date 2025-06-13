-- Sample data
INSERT INTO users (username, email)
VALUES ('alice', 'alice@example.com'),
       ('bob', 'bob@example.com');

INSERT INTO products (name)
VALUES ('Laptop'),
       ('Mouse'),
       ('Keyboard');

INSERT INTO orders (user_id, product_id, amount)
VALUES (1, 1, 2), -- Alice orders 2 Laptops
       (1, 2, 5), -- Alice orders 5 Mice
       (2, 3, 1); -- Bob orders 1 Keyboard