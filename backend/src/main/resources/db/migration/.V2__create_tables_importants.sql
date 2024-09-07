-- Criação da tabela adress
CREATE TABLE adress (
    adressId BIGSERIAL PRIMARY KEY,
    adressCep VARCHAR(20),
    adressStreet VARCHAR(255),
    adressCity VARCHAR(100),
    adressNumber INT,
    adressComplement VARCHAR(255),
    adressReference VARCHAR(255)
);

-- Criação da tabela Buyer
CREATE TABLE buyer (
    idbuyer BIGSERIAL PRIMARY KEY,
    role VARCHAR(20) CHECK (role IN ('ADMIN', 'USER', 'SELLER', 'SELLER_INACTIVE', 'BUYER', 'BUYER_INACTIVE')) NOT NULL DEFAULT 'BUYER',
    buyerFone_number VARCHAR(20),
    adress_id BIGINT,
    FOREIGN KEY (adress_id) REFERENCES adress(adressId)
);

-- Criação da tabela Stock associada ao Seller
CREATE TABLE stock (
    stockId BIGSERIAL PRIMARY KEY
);

-- Criação da tabela Seller
CREATE TABLE seller (
    sellerId BIGSERIAL PRIMARY KEY,
    role VARCHAR(20) CHECK (role IN ('ADMIN', 'USER', 'SELLER', 'SELLER_INACTIVE', 'BUYER', 'BUYER_INACTIVE')) NOT NULL DEFAULT 'SELLER',
    sellerStock BIGINT,
    FOREIGN KEY (sellerStock) REFERENCES stock(stockId)
);

-- Criação da tabela Product
CREATE TABLE product (
    productId BIGSERIAL PRIMARY KEY,
    productName VARCHAR(255),
    productPrice FLOAT8,  -- Float8 é recomendado em PostgreSQL para maior precisão
    productImageLink VARCHAR(255),
    productDescription TEXT,
    productCategory VARCHAR(20) CHECK (productCategory IN ('HAMBURGUER', 'DRINK'))
);

-- Criação da tabela Order
CREATE TABLE orders (
    orderId BIGSERIAL PRIMARY KEY,
    idBuyer BIGINT,
    orderDeliveryTax FLOAT8,  -- Float8 é recomendado em PostgreSQL para maior precisão
    orderTotal FLOAT8,  -- Float8 é recomendado em PostgreSQL para maior precisão
    currentOrderStatus VARCHAR(50),
    FOREIGN KEY (idBuyer) REFERENCES buyer(idbuyer)
);

-- Criação da tabela OrderItem associada a Order e Product
CREATE TABLE order_item (
    orderItemId BIGSERIAL PRIMARY KEY,
    orderId BIGINT,
    productId BIGINT,
    quantity INT,
    notes TEXT,
    FOREIGN KEY (orderId) REFERENCES orders(orderId),
    FOREIGN KEY (productId) REFERENCES product(productId)
);

-- Criação da tabela OrderStatus associada a Order
CREATE TABLE order_status (
    orderStatusId BIGSERIAL PRIMARY KEY,
    idOrder BIGINT,
    orderStatusTimeStamp TIMESTAMPTZ,  -- TIMESTAMPTZ é recomendado para armazenar timestamps com timezone
    orderStatusDescription VARCHAR(255),
    FOREIGN KEY (idOrder) REFERENCES orders(orderId)
);

-- Criação da tabela Payment associada a Order
CREATE TABLE payment (
    idPayment BIGSERIAL PRIMARY KEY,
    order_id BIGINT,
    total FLOAT8 NOT NULL,  -- Float8 é recomendado em PostgreSQL para maior precisão
    isPaid BOOLEAN NOT NULL,
    paymentMethod VARCHAR(10) CHECK (paymentMethod IN ('CARD', 'CASH', 'PIX')) NOT NULL,
    paymentStatus VARCHAR(10) CHECK (paymentStatus IN ('PAID', 'PENDING', 'DENIED')) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(orderId)
);