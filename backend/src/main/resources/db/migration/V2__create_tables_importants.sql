CREATE TABLE adress (
    adressId BIGINT PRIMARY KEY AUTO_INCREMENT,
    adressCep VARCHAR(20),
    adressStreet VARCHAR(255),
    adressCity VARCHAR(100),
    adressNumber INT,
    adressComplement VARCHAR(255),
    adressReference VARCHAR(255)
);

-- Criação da tabela Buyer
CREATE TABLE buyer (
    idbuyer BIGINT PRIMARY KEY AUTO_INCREMENT,

    role ENUM('ADMIN','USER' ,'SELLER', 'SELLER_INACTIVE', 'BUYER', 'BUYER_INACTIVE') NOT NULL DEFAULT 'BUYER',
    buyerFone_number VARCHAR(20),
    adress_id BIGINT,
    FOREIGN KEY (adress_id) REFERENCES adress(adressId)
);

-- Criação da tabela Stock associada ao Seller
CREATE TABLE stock (
    stockId BIGINT PRIMARY KEY AUTO_INCREMENT
);


-- Criação da tabela Seller
CREATE TABLE seller (
    sellerId BIGINT PRIMARY KEY AUTO_INCREMENT,
    role ENUM('ADMIN','USER' ,'SELLER', 'SELLER_INACTIVE', 'BUYER', 'BUYER_INACTIVE') NOT NULL DEFAULT 'SELLER',
    sellerStock BIGINT,
    FOREIGN KEY (sellerStock) REFERENCES stock(stockId)
);



-- Criação da tabela Product
CREATE TABLE product (
    productId BIGINT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(255),
    productPrice FLOAT,
    productImageLink VARCHAR(255),
    productDescription TEXT,
    productCategory ENUM('HAMBURGUER','DRINK')
);


-- Criação da tabela Order
CREATE TABLE orders (
    orderId BIGINT PRIMARY KEY AUTO_INCREMENT,
    idBuyer BIGINT,
    orderDeliveryTax FLOAT,
    orderTotal FLOAT,
    currentOrderStatus VARCHAR(50),
    FOREIGN KEY (idBuyer) REFERENCES buyer(idBuyer)
);

-- Criação da tabela OrderItem associada a Order e Product
CREATE TABLE order_item (
    orderItemId BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderId BIGINT,
    productId BIGINT,
    quantity INT,
    notes TEXT,
    FOREIGN KEY (orderId) REFERENCES orders(orderId),
    FOREIGN KEY (productId) REFERENCES product(productId)
);

-- Criação da tabela OrderStatus associada a Order
CREATE TABLE order_status (
    orderStatusId BIGINT PRIMARY KEY AUTO_INCREMENT,
    idOrder BIGINT,
    orderStatusTimeStamp DATETIME,
    orderStatusDescription VARCHAR(255),
    FOREIGN KEY (idOrder) REFERENCES orders(orderId)
);

-- Criação da tabela Payment associada a Order
CREATE TABLE payment (
    idPayment BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    total FLOAT NOT NULL,
    isPaid BOOLEAN NOT NULL,
    paymentMethod ENUM('CARD', 'CASH', 'PIX') NOT NULL,
    paymentStatus ENUM('PAID', 'PENDING', 'DENIED') NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(orderId)
);








