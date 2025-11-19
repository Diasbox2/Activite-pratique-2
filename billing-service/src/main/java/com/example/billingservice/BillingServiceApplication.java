package com.example.billingservice;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient) {
        return args -> {
            try {
                // Attempt to fetch data from other services
                Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
                Collection<Product> products = productRestClient.getAllProducts().getContent();

                customers.forEach(customer -> {
                    // 1. Check ID existence
                    if (customer.getId() == null) {
                        return;
                    }

                    Bill bill = Bill.builder()
                            .billingDate(new Date())
                            .customerId(customer.getId())
                            .build();
                    billRepository.save(bill);

                    products.forEach(product -> {
                        // 2. Check Product ID existence
                        if (product.getId() != null) {
                            ProductItem productItem = ProductItem.builder()
                                    .bill(bill)
                                    .productId(product.getId())
                                    .quantity(1 + new Random().nextInt(10))
                                    .unitPrice(product.getPrice())
                                    .build();
                            productItemRepository.save(productItem);
                        }
                    });
                });

            } catch (Exception e) {
                // 3. CATCH THE 503 ERROR HERE
                // This prevents the application from crashing if other services are down
                System.err.println("⚠️  WARNING: Could not initialize demo data.");
                System.err.println("Reason: " + e.getMessage());
                System.err.println("This is expected if customer-service or inventory-service are not ready yet.");
                System.err.println("The Billing Service has started successfully anyway.");
            }
        };
    }
}