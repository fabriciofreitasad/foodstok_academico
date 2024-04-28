INSERT INTO tb_categoria (descricao, nome) VALUES ('Carnes Vermelhas e Brancas', 'Carne');
INSERT INTO tb_categoria (descricao, nome) VALUES ('Frutas Frescas e Secas', 'Frutas');
INSERT INTO tb_categoria (descricao, nome) VALUES ('Cereais Matinais e Grãos', 'Cereais');

INSERT INTO tb_usuario (email, nome, senha) VALUES ('joao@example.com', 'João', 'senha123');
INSERT INTO tb_usuario (email, nome, senha) VALUES ('maria@example.com', 'Maria', 'senha456');
INSERT INTO tb_usuario (email, nome, senha) VALUES ('pedro@example.com', 'Pedro', 'senha789');

INSERT INTO tb_produto (categoria_id, descricao, img_url, marca, nome, preco, estoque) VALUES (1, 'Contrafilé', 'imagem_contrafile.jpg', 'Marca de Qualidade', 'Contrafilé Angus', 39.90, 10);  -- Carne
INSERT INTO tb_produto (categoria_id, descricao, img_url, marca, nome, preco, estoque) VALUES (2, 'Maçã Fuji', 'imagem_maca_fuji.jpg', 'Frutas Frescas', 'Maçã Fuji 1kg', 5.99, 20);    -- Frutas
INSERT INTO tb_produto (categoria_id, descricao, img_url, marca, nome, preco, estoque) VALUES (3, 'Aveia em Flocos', 'imagem_aveia_flocos.jpg', 'Natural Life', 'Aveia em Flocos 500g', 4.50, 15);  -- Cereais
INSERT INTO tb_produto (categoria_id, descricao, img_url, marca, nome, preco, estoque) VALUES (2, 'Laranja Selecionada', 'imagem_laranja_selecionada.jpg', 'Frutas da Estação', 'Laranja Selecionada 1kg', 3.99, 30); -- Frutas
INSERT INTO tb_produto (categoria_id, descricao, img_url, marca, nome, preco, estoque) VALUES (1, 'Peito de Frango', 'imagem_peito_frango.jpg', 'Açougue do Bem', 'Peito de Frango sem pele 1kg', 22.50, 8);  -- Carne

INSERT INTO tb_estoque (produto_id, quantidade) VALUES (1, 10); -- Produto 1 com 10 unidades em estoque
INSERT INTO tb_estoque (produto_id, quantidade) VALUES (2, 5);  -- Produto 2 com 5 unidades em estoque
INSERT INTO tb_estoque (produto_id, quantidade) VALUES (3, 15); -- Produto 3 com 15 unidades em estoque


INSERT INTO tb_compra (data, usuario_id)  VALUES (DATEADD('DAY', -2, now()), 2); 
-- Compra pelo usuário Maria (dois dias atrás)


-- Supondo que o usuário João comprou alguns produtos (ajuste os dados de acordo)
INSERT INTO tb_compra_item (quantidade, lista_compra_id, produto_id) VALUES (2, 1, 1);  -- 2 Contrafilé na compra 1
INSERT INTO tb_compra_item (quantidade, lista_compra_id, produto_id) VALUES (1, 1, 3);  -- 1 Aveia em Flocos na compra 1




