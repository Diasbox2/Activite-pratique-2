package com.example.customerservice;

import com.example.customerservice.config.CustomerConfigParams;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args ->{
            customerRepository.save(Customer.builder().name("Yahya").email("yahya@gmail.com").build());
            customerRepository.save(Customer.builder().name("Ayman").email("ayman@gmail.com").build());
            customerRepository.save(Customer.builder().name("Ismail").email("ismail@gmail.com").build());
            customerRepository.findAll().forEach(c->{
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
            });

        };
    }
}
