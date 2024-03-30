-- Inserindo categorias a tabela tb_categoria
INSERT INTO tb_categoria(nome) VALUES ('Carnes');
INSERT INTO tb_categoria(nome) VALUES ('Frutas');
INSERT INTO tb_categoria(nome) VALUES ('Cereais');

-- Insira os produtos na tabela tb_produto
INSERT INTO tb_produto(nome, marca, img_url) VALUES ('Contra filé', 'Friboi', 'https://exemplo.com/imagem-laranja.jpg');
INSERT INTO tb_produto(nome, marca, img_url) VALUES ('Laranja', 'Polpa top', 'https://exemplo.com/imagem-laranja.jpg');
INSERT INTO tb_produto(nome, marca, img_url) VALUES ('Milharina', 'Milhão', 'https://exemplo.com/imagem-laranja.jpg');

-- Associar o produto "Contra filé" à categoria "Carnes"
INSERT INTO tb_produto_categoria(produto_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_produto_categoria(produto_id, categoria_id) VALUES (2, 2);
INSERT INTO tb_produto_categoria(produto_id, categoria_id) VALUES (3, 3);

-- Inserindo usuarios
INSERT INTO tb_usuario (nome, email, senha) VALUES ('João', 'joao@example.com', 'senha123');
INSERT INTO tb_usuario (nome, email, senha) VALUES ('Maria', 'maria@example.com', 'senha456');
INSERT INTO tb_usuario (nome, email, senha) VALUES ('Pedro', 'pedro@example.com', 'senha789');

-- Inserindo uma lista de compras na tabela tb_compra
INSERT INTO tb_compra (quantidade, cliente_id) VALUES (2, 1); -- Compra do usuário João (id = 1) com quantidade 2

-- Inserindo itens de compra na tabela tb_compra_item
INSERT INTO tb_compra_item (produto_id, compra_id, quantidade) VALUES (1, 1, 3); -- Produto Contra filé (id = 1) na compra 1 com quantidade 3
INSERT INTO tb_compra_item (produto_id, compra_id, quantidade) VALUES (2, 1, 4); -- Produto Laranja (id = 2) na compra 1 com quantidade 4

