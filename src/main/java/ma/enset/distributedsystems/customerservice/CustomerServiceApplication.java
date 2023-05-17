package ma.enset.distributedsystems.customerservice;

import ma.enset.distributedsystems.customerservice.entities.Customer;
import ma.enset.distributedsystems.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            Stream.of("Ahmed", "Salma", "Mustapha", "Othman", "Sanae").forEach(name ->{
                Customer customer = Customer.builder()
                        .id(null)
                        .name(name)
                        .email(name + "@gmail.com")
                        .build();
                customerRepository.save(customer);

            });
        };
    }
}