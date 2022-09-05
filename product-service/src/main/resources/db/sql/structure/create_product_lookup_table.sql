CREATE TABLE "products_lookup"
(
    product_id   VARCHAR(255) NOT NULL,
    product_name VARCHAR(255),
    CONSTRAINT "pk_products-lookup" PRIMARY KEY (product_id)
);

ALTER TABLE "products_lookup"
    ADD CONSTRAINT "uc_products-lookup_product_name" UNIQUE (product_name);