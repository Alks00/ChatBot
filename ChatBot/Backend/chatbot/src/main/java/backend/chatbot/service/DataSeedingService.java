package backend.chatbot.service;

import backend.chatbot.entity.Secretaria;
import backend.chatbot.entity.Servico;
import backend.chatbot.repository.SecretariaRepository;
import backend.chatbot.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DataSeedingService {

    @Autowired
    private SecretariaRepository secretariaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public void seedData() {
        // Create Secretarias
        Secretaria saude = new Secretaria(null, "Secretaria de Saúde", "Cuida da saúde pública", "Rua da Saúde, 123", null);
        Secretaria educacao = new Secretaria(null, "Secretaria de Educação", "Cuida da educação pública", "Rua da Educação, 456", null);
        Secretaria seguranca = new Secretaria(null, "Secretaria de Segurança", "Cuida da segurança pública", "Rua da Segurança, 789", null);

        secretariaRepository.saveAll(Arrays.asList(saude, educacao, seguranca));

        // Create Servicos
        Servico vacinacao = new Servico(null, "Vacinação", "Campanha de vacinação contra a gripe", "1111-1111", "Posto de Saúde Central", saude);
        Servico matricula = new Servico(null, "Matrícula Escolar", "Matrículas para o ano letivo de 2025", "2222-2222", "Escola Municipal Principal", educacao);
        Servico patrulha = new Servico(null, "Patrulha do Bairro", "Policiamento ostensivo nos bairros", "3333-3333", "Base da Polícia Comunitária", seguranca);
        Servico emergencia = new Servico(null, "Atendimento de Emergência", "Atendimento 24h para emergências médicas", "192", "Hospital Municipal", saude);

        servicoRepository.saveAll(Arrays.asList(vacinacao, matricula, patrulha, emergencia));

        saude.setServico(Arrays.asList(vacinacao, emergencia));
        educacao.setServico(Collections.singletonList(matricula));
        seguranca.setServico(Collections.singletonList(patrulha));

        secretariaRepository.saveAll(Arrays.asList(saude, educacao, seguranca));
    }
}
