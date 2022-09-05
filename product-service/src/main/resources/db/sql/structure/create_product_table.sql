CREATE TABLE products
(
    id          VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    updated_at  VARCHAR(255),
    created_at  VARCHAR(255),
    version     INTEGER,
    is_active   BOOLEAN,
    price       FLOAT,
    quantity    INTEGER,
    CONSTRAINT pk_products PRIMARY KEY (id)
);