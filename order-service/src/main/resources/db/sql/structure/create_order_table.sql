CREATE TABLE orders
(
    id           VARCHAR(255) NOT NULL,
    product_id   VARCHAR(255),
    user_id      VARCHAR(255),
    quantity     INTEGER,
    address_id   VARCHAR(255),
    order_status VARCHAR(255),
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT uc_orders_id UNIQUE (id);