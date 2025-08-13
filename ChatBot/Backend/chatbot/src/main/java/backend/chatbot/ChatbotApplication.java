package backend.chatbot;

import backend.chatbot.service.DataSeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication implements CommandLineRunner {

	@Autowired
	private DataSeedingService dataSeedingService;

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataSeedingService.seedData();
	}
}
