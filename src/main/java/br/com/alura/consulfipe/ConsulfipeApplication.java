package br.com.alura.consulfipe;

import br.com.alura.consulfipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsulfipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsulfipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
