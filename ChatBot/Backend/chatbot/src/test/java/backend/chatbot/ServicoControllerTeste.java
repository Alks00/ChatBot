package backend.chatbot;
import backend.chatbot.entity.Servico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicoControllerTeste {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void deveRetornarServicosDaSecretaria1() {
        String url = "http://localhost:" + port + "/chatbot/1";

        ResponseEntity<Servico[]> response = restTemplate.getForEntity(url, Servico[].class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0, "Deveria retornar pelo menos um serviço");

        Arrays.stream(response.getBody())
                .forEach(servico -> System.out.println("Serviço: " + servico.getNome()));
    }
}

