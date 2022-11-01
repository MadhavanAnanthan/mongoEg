package com.amigoscode.mongoEg.Controller;

import com.amigoscode.mongoEg.Repository.ShipmentService;
import com.amigoscode.mongoEg.model.ShippingInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/addShipInfo")
    public String addShipInfo(@RequestBody ShippingInformation shippingInformation) {
        shipmentService.saveShipInfo(shippingInformation);

        return "Record inserted succesfully ---> " + shippingInformation;
    }

    @GetMapping("/getAllShipInfo")
    public List<ShippingInformation> getAllShipInfo() {
        return shipmentService.getAllShipInformation();
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public String deleteShipInfo(@PathVariable String email) {
        String response = "";
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<ShippingInformation> shippingInfoInDB =
                mongoTemplate.find(query, ShippingInformation.class);
        //List<ShippingInformation> shippingInfoInDB = shipmentService.deleteByEmail(email);
        if (shippingInfoInDB.size() >= 1) {
            mongoTemplate.findAndRemove(query, ShippingInformation.class);
            response = "Deleted document : " + shippingInfoInDB;
        } else {
            response = "Deleted document is not exists";
        }
        return response;
    }
}
