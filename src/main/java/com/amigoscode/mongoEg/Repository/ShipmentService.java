package com.amigoscode.mongoEg.Repository;

import com.amigoscode.mongoEg.model.ShippingInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ShippingInformation> getAllShipInformation() {
        return shipmentRepository.findAll();
    }

    public List<ShippingInformation> deleteByEmail(String email) {

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<ShippingInformation> shippingInfoInDB =
                mongoTemplate.find(query, ShippingInformation.class);


return shippingInfoInDB;

    }

    public void saveShipInfo(ShippingInformation shippingInformation) {
        shipmentRepository.save(shippingInformation);
    }
}
