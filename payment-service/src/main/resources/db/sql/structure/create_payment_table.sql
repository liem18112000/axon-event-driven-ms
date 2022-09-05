CREATE TABLE payments
(
    id       VARCHAR(255) NOT NULL,
    order_id VARCHAR(255),
    CONSTRAINT pk_payments PRIMARY KEY (id)
);