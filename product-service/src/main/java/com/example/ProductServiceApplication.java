package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long> {

}
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start (ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null,"dali",20));
            productRepository.save(new Product(null,"dali2",30));
            productRepository.save(new Product(null,"dali3",40));
        };
    }



}



@RestController
class TestController {

    @GetMapping("/admin")
    public String admin() {
        return "this is for admin";
    }


    @GetMapping("/user")
    public String user() {
        return "this is for user";
    }


}

