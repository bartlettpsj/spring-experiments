package com.example.paul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
public class PaulApplication {

//	private static final Logger log = LoggerFactory.getLogger(PaulApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaulApplication.class, args);
	}

	@Bean String fred(CustomerRepository repository) {
		log.info("PAUL is TESTING");
		log.info("Repository is {}", repository);
		return "HELLO";
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {

		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer", null));
			repository.save(new Customer("Chloe", "O'Brian", null));
			repository.save(new Customer("Kim", "Bauer", null));
			repository.save(new Customer("David", "Palmer", null));
			repository.save(new Customer("Michelle", "Dessler", null));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			if (customer != null) {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			}

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
