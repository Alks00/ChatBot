package backend.chatbot.controller;

import backend.chatbot.entity.Secretaria;
import backend.chatbot.service.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatbot")
public class SecretariaController {

    @Autowired
    private SecretariaService secretariaService;

    @GetMapping
    public List<Secretaria> listarTodasSecretarias() {
        return secretariaService.listarTodasSecretarias();
    }
}
