package backend.chatbot.service;

import backend.chatbot.entity.Servico;
import backend.chatbot.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarServicosPorSecretaria(Long secretariaId) {
        return servicoRepository.findBySecretariaId(secretariaId);
    }
}
