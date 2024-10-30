package in.nareshit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"in.nareshit.entities , in.nareshit.dto, in.nareshit.repos, in.nareshit.service, in.nareshit.controller "})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
