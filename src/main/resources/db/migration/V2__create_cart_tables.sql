CREATE TABLE carts (
    id BINARY(16) NOT NULL PRIMARY KEY DEFAULT (uuid_to_bin(uuid())),
    date_created DATE NOT NULL DEFAULT (CURDATE())
);

CREATE TABLE cart_items (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cart_id BINARY(16) NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT,
    CONSTRAINT cart_items_cart_product_unique UNIQUE (cart_id, product_id)
);