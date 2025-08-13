INSERT INTO secretaria (nome, descricao, endereco) VALUES
('Secretaria de Saúde', 'Cuida da saúde pública', 'Rua da Saúde, 123'),
('Secretaria de Educação', 'Cuida da educação pública', 'Rua da Educação, 456'),
('Secretaria de Segurança', 'Cuida da segurança pública', 'Rua da Segurança, 789');

INSERT INTO servico (nome, descricao, telefone, endereco, secretaria_id) VALUES
('Vacinação', 'Campanha de vacinação contra a gripe', '1111-1111', 'Posto de Saúde Central', 1),
('Atendimento de Emergência', 'Atendimento 24h para emergências médicas', '192', 'Hospital Municipal', 1),
('Matrícula Escolar', 'Matrículas para o ano letivo de 2025', '2222-2222', 'Escola Municipal Principal', 2),
('Patrulha do Bairro', 'Policiamento ostensivo nos bairros', '3333-3333', 'Base da Polícia Comunitária', 3);
