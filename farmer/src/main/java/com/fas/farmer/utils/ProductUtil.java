package com.fas.farmer.utils;

import com.fas.farmer.dtos.ProductDetails;
import com.fas.farmer.entities.Farmer;
import com.fas.farmer.entities.Product;
import com.fas.farmer.exceptions.FarmerNotFoundException;
import com.fas.farmer.repository.IFarmersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductUtil {

    @Autowired
    private IFarmersRepository farmersRepository;
    public ProductDetails toProductDetails(Product product){
        Optional<Farmer> farmerOptional = farmersRepository.findById(product.getFarmerId());
        if(!farmerOptional.isPresent())
            throw new FarmerNotFoundException("No Farmer found for id: "+product.getFarmerId());
        Farmer farmer =  farmerOptional.get();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(product.getId());
        productDetails.setName(product.getName());
        productDetails.setPrice(product.getPrice());
        productDetails.setQuantity(product.getQuantity());
        productDetails.setFarmerFirstName(farmer.getFirstName());
        productDetails.setFarmerLastName(farmer.getLastName());
        productDetails.setPincode(farmer.getPincode());
        productDetails.setPhnNumber(farmer.getPhnNumber());
        return productDetails;
    }

    public List<ProductDetails> toProductDetailsList(List<Product> productList) {
        List<ProductDetails> productDetailsList = new ArrayList<>();
        for(Product product : productList){
            Optional<Farmer> farmerOptional = farmersRepository.findById(product.getFarmerId());
            if(!farmerOptional.isPresent())
                continue;
            Farmer farmer =  farmerOptional.get();
            ProductDetails productDetails = new ProductDetails();
            productDetails.setId(product.getId());
            productDetails.setName(product.getName());
            productDetails.setPrice(product.getPrice());
            productDetails.setQuantity(product.getQuantity());
            productDetails.setFarmerFirstName(farmer.getFirstName());
            productDetails.setFarmerLastName(farmer.getLastName());
            productDetails.setPincode(farmer.getPincode());
            productDetails.setPhnNumber(farmer.getPhnNumber());

            productDetailsList.add(productDetails);
        }
        return productDetailsList;
    }
}
