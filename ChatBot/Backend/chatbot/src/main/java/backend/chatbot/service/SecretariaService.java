package backend.chatbot.service;

import backend.chatbot.entity.Secretaria;
import backend.chatbot.repository.SecretariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretariaService {

    private SecretariaRepository secretariaRepository;

    public SecretariaService(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    public List<Secretaria> listarTodasSecretarias() {
        return secretariaRepository.findAll();
    }
}
