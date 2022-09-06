CREATE TABLE payments
(
    id            VARCHAR(255) NOT NULL,
    order_id      VARCHAR(255) NOT NULL,
    status        VARCHAR(255) NOT NULL,
    cancel_reason TEXT,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);