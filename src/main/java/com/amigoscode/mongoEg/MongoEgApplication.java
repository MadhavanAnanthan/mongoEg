package com.amigoscode.mongoEg;

import com.amigoscode.mongoEg.Repository.ShipmentRepository;
import com.amigoscode.mongoEg.model.Address;
import com.amigoscode.mongoEg.model.Gender;
import com.amigoscode.mongoEg.model.ShippingInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class MongoEgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoEgApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ShipmentRepository repository,
                                        MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "India",
                    "Chennai",
                    "600117"
            );
            String email = "amadhavan1997@gmail.com";
            ShippingInformation shippingInformation = new ShippingInformation(
                    "Madhavan",
                    "Ananthan",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Iphone","FastAdapter","LightningCable"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            //filterEmailByQueryAndMongoTemplate(repository, mongoTemplate, email, shippingInformation);
            repository.findShippingInformationByEmail(email).ifPresentOrElse(
                    shippingInformation1 -> {
                        System.out.println("Records are present in same email" + email);
                    },
                    ()-> {
                        System.out.println("Inserted Record is ---> " + shippingInformation);
                        repository.insert(shippingInformation);
                    }
            );

        };
    }

    private void filterEmailByQueryAndMongoTemplate(ShipmentRepository repository, MongoTemplate mongoTemplate, String email, ShippingInformation shippingInformation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<ShippingInformation> shippingInfoInDB =
        mongoTemplate.find(query, ShippingInformation.class);
        System.out.println(shippingInfoInDB.size());

        if (shippingInfoInDB.isEmpty()) {
            System.out.println("Inserted Record is ---> " + shippingInformation);
            repository.insert(shippingInformation);
        }
        else{
            throw new IllegalStateException("Records are present in same email" + email);
        }
    }
}
