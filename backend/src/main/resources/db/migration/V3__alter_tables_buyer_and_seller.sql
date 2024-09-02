ALTER TABLE buyer
DROP COLUMN idbuyer;  -- Remove a coluna idbuyer

ALTER TABLE buyer
ADD COLUMN id BIGINT; -- Adiciona a coluna id que é a FK para users

ALTER TABLE buyer
ADD CONSTRAINT fk_buyer_user FOREIGN KEY (id) REFERENCES users(id);

ALTER TABLE buyer
DROP COLUMN role; -- Remove a coluna role, pois já está na tabela users

ALTER TABLE seller
DROP COLUMN sellerId; -- Remove a coluna sellerId

ALTER TABLE seller
ADD COLUMN id BIGINT; -- Adiciona a coluna id que é a FK para users

ALTER TABLE seller
ADD CONSTRAINT fk_seller_user FOREIGN KEY (id) REFERENCES users(id);

ALTER TABLE seller
DROP COLUMN role;     -- Remove a coluna role, pois já está na tabela users