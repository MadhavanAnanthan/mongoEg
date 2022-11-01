package com.amigoscode.mongoEg.Repository;

import com.amigoscode.mongoEg.model.ShippingInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShipmentRepository extends
        MongoRepository<ShippingInformation,String> {
    Optional<ShippingInformation> findShippingInformationByEmail(String email);
}
