package org.soumia.customerservice;

import org.soumia.customerservice.config.GlobalConfig;
import org.soumia.customerservice.entities.Customer;
import org.soumia.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {

			List<Customer> customerList = List.of(
					Customer.builder()
							.firstName("Ilyas")
							.lastName("Elharti")
							.email("ie@gmail.com")
							.build(),
					Customer.builder()
							.firstName("Soumia")
							.lastName("Najed")
							.email("sn@gmail.com")
							.build(),
					Customer.builder()
							.firstName("Hind")
							.lastName("Najed")
							.email("hn@gmail.com")
							.build()
			);

			customerRepository.saveAll(customerList);
		};
	}

}
