CREATE TABLE orders_lookup
(
    id         VARCHAR(255) NOT NULL,
    product_id VARCHAR(255),
    user_id    VARCHAR(255),
    quantity   INTEGER,
    CONSTRAINT pk_orders_lookup PRIMARY KEY (id)
);

ALTER TABLE orders_lookup
    ADD CONSTRAINT uc_orders_lookup_id UNIQUE (id);