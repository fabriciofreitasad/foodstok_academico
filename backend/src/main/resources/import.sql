INSERT INTO tb_user (name, email, password) VALUES ('Christopher Fernandes', 'chris@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Abraão Mendes', 'Abraao@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Fabricio Freitas', 'fabricio@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Teste', 'teste@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);

INSERT INTO tb_category (name, created_At) VALUES  ('Carnes', NOW());
INSERT INTO tb_category (name, created_At) VALUES ('Frutas', NOW());
INSERT INTO tb_category (name, created_At) VALUES ('Cereais', NOW());

INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Farinha de Aveia', 12.50, NOW(), 'Farinha de aveia integral para consumo diário.', 'https://example.com/aveia.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Granola Natural', 15.75, NOW(), 'Granola rica em fibras e vitaminas.', 'https://example.com/granola.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Flocos de Milho', 10.90, NOW(), 'Flocos de milho crocantes, ideais para o café da manhã.', 'https://example.com/flocosdemilho.jpg');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 3);

INSERT INTO tb_pessoa(email, name, telefone, tipo_pessoa) VALUES ('fabricio@gmail.com','fabricio', '62 99598008', 1);

INSERT INTO tb_endereco(bairro, cep, cidade, complemento, numero, rua_logra, tipo_endereco, uf, pessoa_id) VALUES ('Los Angeles', '750000', 'Goianira', 'Praça', '1', 'Analupe', 'COBRANCA', 'go', 1);

