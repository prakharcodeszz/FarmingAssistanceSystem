package com.fas.supplier.utilities;

import com.fas.supplier.dtos.BuyRequestDetails;
import com.fas.supplier.dtos.ProductDetails;
import com.fas.supplier.entities.BuyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuyRequestUtility {
    @Value("${admins.baseUrl}")
    private String baseAdminsUrl;
    @Value("${farmers.baseUrl}")
    private String baseFarmersUrl;

    @Autowired
    private RestTemplate restTemplate;


    public ProductDetails getProductDetailsById(Long productId) {
        String url = baseFarmersUrl + "/products/getById/" +productId;
        ResponseEntity<ProductDetails> result = restTemplate.getForEntity(url, ProductDetails.class);
        return result.getBody();
    }

    public BuyRequestDetails toBuyRequestDetails(BuyRequest buyRequest) {
        BuyRequestDetails buyRequestDetails = new BuyRequestDetails();
        ProductDetails productDetails = getProductDetailsById(buyRequest.getProductId());
        buyRequestDetails.setSupplierId(buyRequest.getSupplierId());
        buyRequestDetails.setAskedPrice(buyRequest.getAskedPrice());
        buyRequestDetails.setRequestStatus(buyRequest.getRequestStatus().toString());

        buyRequestDetails.setProductId(buyRequest.getProductId());
        buyRequestDetails.setProductName(productDetails.getName());
        buyRequestDetails.setProductQuantity(productDetails.getQuantity());
        buyRequestDetails.setProductPrice(productDetails.getPrice());

        buyRequestDetails.setFarmerId(productDetails.getFarmerId());
        buyRequestDetails.setFarmerFirstName(productDetails.getFarmerFirstName());
        buyRequestDetails.setFarmerLastName(productDetails.getFarmerLastName());
        buyRequestDetails.setFarmerPincode(productDetails.getFarmerPincode());
        buyRequestDetails.setFarmerPhnNumber(productDetails.getFarmerPhnNumber());
        return buyRequestDetails;
    }

    public List<BuyRequestDetails> toBuyRequestDetails(List<BuyRequest> buyRequestsList) {
        List<BuyRequestDetails> buyRequestDetailsList = new ArrayList<>();
        for(BuyRequest buyRequest : buyRequestsList){
            buyRequestDetailsList.add(toBuyRequestDetails(buyRequest));
        }
        return  buyRequestDetailsList;
    }
}
