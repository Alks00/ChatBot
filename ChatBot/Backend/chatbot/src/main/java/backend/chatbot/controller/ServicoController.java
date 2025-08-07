package backend.chatbot.controller;

import backend.chatbot.entity.Servico;
import backend.chatbot.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatbot")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{secretaria_id}")
    public List<Servico> listarServicosPorSecretaria(@PathVariable("secretaria_id") Long secretariaId) {
        return servicoService.listarServicosPorSecretaria(secretariaId);
    }
}
