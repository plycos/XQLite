-- Table definitions
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL
);

CREATE TABLE products
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users (id),
    product_id INTEGER REFERENCES products (id),
    amount     INTEGER NOT NULL
);
