package backend.chatbot.service;

import backend.chatbot.entity.Secretaria;
import backend.chatbot.entity.Servico;
import backend.chatbot.repository.SecretariaRepository;
import backend.chatbot.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DataSeedingService {

    @Autowired
    private SecretariaRepository secretariaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public void seedData() {
        Secretaria saude = new Secretaria(null, "Secretaria de Saúde", "Cuida da saúde pública", "Rua da Saúde, 123", null);
        Secretaria educacao = new Secretaria(null, "Secretaria de Educação", "Cuida da educação pública", "Rua da Educação, 456", null);
        Secretaria seguranca = new Secretaria(null, "Secretaria de Segurança", "Cuida da segurança pública", "Rua da Segurança, 789", null);

        secretariaRepository.saveAll(Arrays.asList(saude, educacao, seguranca));

        Servico vacinacao = new Servico(null, "Vacinação", "Campanha de vacinação contra a gripe", "1111-1111", "Posto de Saúde Central", saude);
        Servico matricula = new Servico(null, "Matrícula Escolar", "Matrículas para o ano letivo de 2025", "2222-2222", "Escola Municipal Principal", educacao);
        Servico patrulha = new Servico(null, "Patrulha do Bairro", "Policiamento ostensivo nos bairros", "3333-3333", "Base da Polícia Comunitária", seguranca);
        Servico emergencia = new Servico(null, "Atendimento de Emergência", "Atendimento 24h para emergências médicas", "192", "Hospital Municipal", saude);

        servicoRepository.saveAll(Arrays.asList(vacinacao, matricula, patrulha, emergencia));

        saude.setServico(Arrays.asList(vacinacao, emergencia));
        educacao.setServico(Collections.singletonList(matricula));
        seguranca.setServico(Collections.singletonList(patrulha));

        secretariaRepository.saveAll(Arrays.asList(saude, educacao, seguranca));

        Secretaria transporte = new Secretaria(null, "Secretaria de Transportes", "Gerencia o transporte público e mobilidade urbana", "Av. dos Transportes, 101", null);
        Secretaria cultura = new Secretaria(null, "Secretaria de Cultura", "Promove atividades culturais e artísticas", "Praça das Artes, 202", null);
        Secretaria esportes = new Secretaria(null, "Secretaria de Esportes", "Fomenta a prática esportiva", "Rua do Estádio, 303", null);
        Secretaria meioAmbiente = new Secretaria(null, "Secretaria do Meio Ambiente", "Cuida da preservação ambiental", "Parque Verde, 404", null);
        Secretaria turismo = new Secretaria(null, "Secretaria de Turismo", "Promove o turismo local", "Av. das Palmeiras, 505", null);
        Secretaria habitacao = new Secretaria(null, "Secretaria de Habitação", "Gerencia políticas de moradia", "Rua das Casas, 606", null);
        Secretaria tecnologia = new Secretaria(null, "Secretaria de Tecnologia", "Promove inovação tecnológica", "Av. Digital, 707", null);
        Secretaria trabalho = new Secretaria(null, "Secretaria do Trabalho", "Apoia geração de emprego e renda", "Rua das Profissões, 808", null);
        Secretaria agricultura = new Secretaria(null, "Secretaria de Agricultura", "Apoia o desenvolvimento rural", "Estrada Rural, 909", null);
        Secretaria fazenda = new Secretaria(null, "Secretaria da Fazenda", "Administra os recursos financeiros da cidade", "Rua Central, 1001", null);

        secretariaRepository.saveAll(Arrays.asList(
                transporte, cultura, esportes, meioAmbiente, turismo,
                habitacao, tecnologia, trabalho, agricultura, fazenda
        ));

        List<Servico> servicos = new ArrayList<>();

        servicos.add(new Servico(null, "Bilhete Único", "Emissão e recarga de cartões de transporte", "4001-0001", "Terminal Central", transporte));
        servicos.add(new Servico(null, "Ônibus Noturno", "Linhas de ônibus 24h", "4001-0002", "Garagem Municipal", transporte));
        servicos.add(new Servico(null, "Ciclovias", "Gestão e manutenção das ciclovias", "4001-0003", "Secretaria de Transportes", transporte));
        servicos.add(new Servico(null, "Táxi Legal", "Cadastro e fiscalização de taxistas", "4001-0004", "Posto de Atendimento", transporte));
        servicos.add(new Servico(null, "Aplicativos de Mobilidade", "Regulação de aplicativos de transporte", "4001-0005", "Secretaria de Transportes", transporte));
        servicos.add(new Servico(null, "Estacionamento Rotativo", "Gestão das vagas rotativas", "4001-0006", "Centro da Cidade", transporte));
        servicos.add(new Servico(null, "Fiscalização de Trânsito", "Acompanhamento das vias urbanas", "4001-0007", "Base de Trânsito", transporte));
        servicos.add(new Servico(null, "Licenciamento de Vans", "Controle de transporte alternativo", "4001-0008", "Posto de Transportes", transporte));
        servicos.add(new Servico(null, "Corredores Exclusivos", "Monitoramento dos corredores de ônibus", "4001-0009", "Secretaria de Transportes", transporte));
        servicos.add(new Servico(null, "Educação no Trânsito", "Campanhas educativas", "4001-0010", "Escolas Municipais", transporte));

        servicos.add(new Servico(null, "Bibliotecas Públicas", "Empréstimo e acesso a livros", "4002-0001", "Biblioteca Central", cultura));
        servicos.add(new Servico(null, "Cursos de Artes", "Oficinas de teatro, música e dança", "4002-0002", "Casa da Cultura", cultura));
        servicos.add(new Servico(null, "Teatro Municipal", "Programação de peças e eventos", "4002-0003", "Teatro Municipal", cultura));
        servicos.add(new Servico(null, "Museus Locais", "Visitação e exposições", "4002-0004", "Museu Histórico", cultura));
        servicos.add(new Servico(null, "Cinema Popular", "Exibição gratuita de filmes", "4002-0005", "Cine Popular", cultura));
        servicos.add(new Servico(null, "Feiras de Arte", "Espaço para artistas locais", "4002-0006", "Praça Central", cultura));
        servicos.add(new Servico(null, "Orquestra Municipal", "Apresentações musicais", "4002-0007", "Auditório Municipal", cultura));
        servicos.add(new Servico(null, "Patrimônio Histórico", "Conservação de prédios históricos", "4002-0008", "Centro Histórico", cultura));
        servicos.add(new Servico(null, "Centro Cultural", "Atividades culturais diversas", "4002-0009", "Centro Cultural", cultura));
        servicos.add(new Servico(null, "Eventos Culturais", "Organização de festivais", "4002-0010", "Secretaria de Cultura", cultura));

        servicos.add(new Servico(null, "Quadras Públicas", "Gestão das quadras poliesportivas", "4003-0001", "Praça dos Esportes", esportes));
        servicos.add(new Servico(null, "Academia ao Ar Livre", "Manutenção dos equipamentos", "4003-0002", "Parque Central", esportes));
        servicos.add(new Servico(null, "Campeonatos Escolares", "Organização de torneios estudantis", "4003-0003", "Escolas Municipais", esportes));
        servicos.add(new Servico(null, "Iniciação Esportiva", "Aulas de esportes para crianças", "4003-0004", "Ginásio Municipal", esportes));
        servicos.add(new Servico(null, "Atletismo", "Treinamentos e competições de corrida", "4003-0005", "Pista Municipal", esportes));
        servicos.add(new Servico(null, "Esportes Inclusivos", "Projetos para pessoas com deficiência", "4003-0006", "Centro Esportivo", esportes));
        servicos.add(new Servico(null, "Natação", "Aulas e treinos de natação", "4003-0007", "Piscina Municipal", esportes));
        servicos.add(new Servico(null, "Futsal", "Campeonatos locais de futsal", "4003-0008", "Quadras Municipais", esportes));
        servicos.add(new Servico(null, "Judô", "Projeto social de judô", "4003-0009", "Dojo Municipal", esportes));
        servicos.add(new Servico(null, "Basquete Comunitário", "Times comunitários", "4003-0010", "Quadra Central", esportes));

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço Ambiental " + i, "Atividade de preservação " + i, "4004-000" + i, "Parque Ecológico", meioAmbiente));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço de Turismo " + i, "Atendimento turístico " + i, "4005-000" + i, "Centro de Turismo", turismo));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço Habitacional " + i, "Apoio em moradia " + i, "4006-000" + i, "Secretaria de Habitação", habitacao));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço de Tecnologia " + i, "Projeto tecnológico " + i, "4007-000" + i, "Parque Tecnológico", tecnologia));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço de Trabalho " + i, "Atendimento ao trabalhador " + i, "4008-000" + i, "Agência do Trabalhador", trabalho));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço de Agricultura " + i, "Suporte agrícola " + i, "4009-000" + i, "Secretaria de Agricultura", agricultura));
        }

        for (int i = 1; i <= 10; i++) {
            servicos.add(new Servico(null, "Serviço da Fazenda " + i, "Gestão financeira " + i, "4010-000" + i, "Secretaria da Fazenda", fazenda));
        }

        servicoRepository.saveAll(servicos);

        transporte.setServico(servicos.stream().filter(s -> s.getSecretaria() == transporte).toList());
        cultura.setServico(servicos.stream().filter(s -> s.getSecretaria() == cultura).toList());
        esportes.setServico(servicos.stream().filter(s -> s.getSecretaria() == esportes).toList());
        meioAmbiente.setServico(servicos.stream().filter(s -> s.getSecretaria() == meioAmbiente).toList());
        turismo.setServico(servicos.stream().filter(s -> s.getSecretaria() == turismo).toList());
        habitacao.setServico(servicos.stream().filter(s -> s.getSecretaria() == habitacao).toList());
        tecnologia.setServico(servicos.stream().filter(s -> s.getSecretaria() == tecnologia).toList());
        trabalho.setServico(servicos.stream().filter(s -> s.getSecretaria() == trabalho).toList());
        agricultura.setServico(servicos.stream().filter(s -> s.getSecretaria() == agricultura).toList());
        fazenda.setServico(servicos.stream().filter(s -> s.getSecretaria() == fazenda).toList());

        secretariaRepository.saveAll(Arrays.asList(
                transporte, cultura, esportes, meioAmbiente, turismo,
                habitacao, tecnologia, trabalho, agricultura, fazenda
        ));
    }
}
