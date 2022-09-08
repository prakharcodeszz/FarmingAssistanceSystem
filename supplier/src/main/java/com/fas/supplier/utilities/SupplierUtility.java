package com.fas.supplier.utilities;

import com.fas.supplier.SupplierApplication;
import com.fas.supplier.dtos.*;
import com.fas.supplier.exceptions.FarmerNotFoundException;
import com.fas.supplier.exceptions.InvalidUserTypeException;
import com.fas.supplier.exceptions.SupplierLoggedOutException;
import com.fas.supplier.exceptions.SupplierNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SupplierUtility {

    Logger logger = LoggerFactory.getLogger(SupplierApplication.class);

    @Value("${admins.baseUrl}")
    private String baseAdminsUrl;
    @Value("${farmers.baseUrl}")
    private String baseFarmersUrl;
    @Value("${products.baseUrl}")
    private String baseProductsUrl;
    @Autowired
    private RestTemplate restTemplate;

    public User sendLoginRequest(LoginCredentials loginCredentials) {
        String url = baseAdminsUrl + "/login";
        ResponseEntity<User> result = restTemplate.postForEntity(url, loginCredentials, User.class);
        return result.getBody();
    }

    public User sendLogoutRequest(String username) {
        String url = baseAdminsUrl + "/logout/" + username;
        return restTemplate.getForEntity(url, User.class).getBody();
    }


    public User sendChangePasswordRequest(ChangePasswordRequest changePasswordRequest) {
        String url = baseAdminsUrl + "/changePassword";
        ResponseEntity<User> result = restTemplate.postForEntity(url, changePasswordRequest, User.class);
        logger.info(result.toString());
        return result.getBody();
    }

    public UserDetails getUserDetails(String username) {
        String url = baseAdminsUrl + "/getUserDetails/" + username;
        ResponseEntity<UserDetails> result = restTemplate.getForEntity(url, UserDetails.class);
        return result.getBody();
    }

    public UserType getUserType(String userType) {
        for (UserType type : UserType.values())
            if (userType.equals(type.toString()))
                return type;
        throw new InvalidUserTypeException("Invalid User Type: " + userType);
    }

    public void isUserSupplier(UserDetails userDetails) {
        UserType userType = getUserType(userDetails.getUserType());
        if (userType != UserType.SUPPLIER)
            throw new SupplierNotFoundException("No supplier found for username: " + userDetails.getUsername());
    }

    public void isSupplierLoggedIn(UserDetails userDetails) {
        if (!userDetails.getLoggedIn())
            throw new SupplierLoggedOutException("Supplier for username is not logged in: " + userDetails.getUsername());
    }

    public List<ProductDetails> getProductsByPincode(Long pincode) {
        String url = baseProductsUrl +"/findByPincode/" +pincode;
        ResponseEntity<ProductDetails[]> result = restTemplate.getForEntity(url, ProductDetails[].class);
        ProductDetails[] arrayList = result.getBody();
        List<ProductDetails> productDetailsList = new ArrayList<>();
        Collections.addAll(productDetailsList, arrayList);
        return productDetailsList;
    }
}
