package com.fas.farmer.utils;

import com.fas.farmer.FarmerApplication;
import com.fas.farmer.dtos.*;
import com.fas.farmer.exceptions.FarmerLoggedOutException;
import com.fas.farmer.exceptions.FarmerNotFoundException;
import com.fas.farmer.exceptions.InvalidUserTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FarmersUtil {
    Logger logger = LoggerFactory.getLogger(FarmerApplication.class);

    @Value("${admins.baseUrl}")
    private String baseAdminsUrl;

    @Value("${suppliers.baseUrl}")
    private String baseSuppliersUrl;

    @Autowired
    private RestTemplate restTemplate;

    public User sendLoginRequest(LoginCredentials loginCredentials){
        String url = baseAdminsUrl + "/login";
        ResponseEntity<User> result = restTemplate.postForEntity(url, loginCredentials, User.class);
        return result.getBody();
    }

    public User sendLogoutRequest(String username){
        String url = baseAdminsUrl + "/logout/" + username;
        return restTemplate.getForEntity(url, User.class).getBody();
    }

    public User sendChangePasswordRequest(@Valid ChangePasswordRequest changePasswordRequest){
        String url = baseAdminsUrl + "/changePassword";
        ResponseEntity<User> result = restTemplate.postForEntity(url, changePasswordRequest, User.class);
        logger.info(result.toString());
        return result.getBody();
    }

//    public List<Supplier> getNearbySuppliers(Long pincode) {
//        String url = baseSuppliersUrl +"/getByPincode/" +pincode;
//        ResponseEntity<Supplier[]> result = restTemplate.getForEntity(url, Supplier[].class);
//        Supplier[] arrayList = result.getBody();
//        List<Supplier> suppliersList = new ArrayList<>();
//        Collections.addAll(suppliersList, arrayList);
//        return suppliersList;
//    }

    public UserType getUserType(String userType) {
        for (UserType type : UserType.values())
            if (userType.equals(type.toString()))
                return type;
        throw new InvalidUserTypeException("Invalid User Type: " + userType);
    }

    public UserDetails getUserDetails(String username){
        String url = baseAdminsUrl + "/getUserDetails/" + username;
        ResponseEntity<UserDetails> result = restTemplate.getForEntity(url, UserDetails.class);
        logger.info(result.getBody().toString());
        return result.getBody();
    }

    public void isUserFarmer(UserDetails userDetails){
        UserType userType = getUserType(userDetails.getUserType());
        if (userType != UserType.FARMER)
            throw new FarmerNotFoundException("No farmer found for username: " + userDetails.getUsername());
    }

    public void isFarmerLoggedIn(UserDetails userDetails){
        if (!userDetails.getLoggedIn())
            throw new FarmerLoggedOutException("Farmer for username is not logged in: " + userDetails.getUsername());
    }

}
