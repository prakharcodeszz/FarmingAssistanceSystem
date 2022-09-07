package com.fas.farmer.service;

import com.fas.farmer.constants.ComplaintStatus;
import com.fas.farmer.constants.RequestStatus;
import com.fas.farmer.dtos.*;
import com.fas.farmer.entities.BuyRequest;
import com.fas.farmer.entities.Complaint;
import com.fas.farmer.entities.Farmer;
import com.fas.farmer.entities.Product;
import com.fas.farmer.exceptions.BuyRequestNotFoundException;
import com.fas.farmer.exceptions.FarmerNotFoundException;
import com.fas.farmer.repository.IBuyRequestsRepository;
import com.fas.farmer.repository.IComplaintRepository;
import com.fas.farmer.repository.IFarmersRepository;
import com.fas.farmer.repository.IProductRepository;
import com.fas.farmer.utils.FarmersUtil;
import com.fas.farmer.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FarmerService implements IFarmersService {

    @Autowired
    private FarmersUtil farmersUtil;

    @Autowired
    private IFarmersRepository farmersrepository;

    @Override
    public User loginWithCredentials(LoginCredentials loginCredentials) {
        UserDetails userDetails = farmersUtil.getUserDetails(loginCredentials.getUsername());
        farmersUtil.isUserFarmer(userDetails);
        return farmersUtil.sendLoginRequest(loginCredentials);
    }

    @Override
    public User logout(String username) {
        UserDetails userDetails = farmersUtil.getUserDetails(username);
        farmersUtil.isUserFarmer(userDetails);
        return farmersUtil.sendLogoutRequest(username);
    }

    @Override
    public User changePassword(ChangePasswordRequest changPasswordRequest) {
        UserDetails userDetails = farmersUtil.getUserDetails(changPasswordRequest.getUsername());
        farmersUtil.isUserFarmer(userDetails);
        return farmersUtil.sendChangePasswordRequest(changPasswordRequest);
    }

    @Override
    public Farmer updateFarmer(UpdateFarmer updateFarmer) {
        UserDetails userDetails = farmersUtil.getUserDetails(updateFarmer.getUsername());
        farmersUtil.isUserFarmer(userDetails);
        farmersUtil.isFarmerLoggedIn(userDetails);
        List<Farmer> farmersList = farmersrepository.findByUsername(updateFarmer.getUsername());
        Farmer farmer;
        if (farmersList.isEmpty())
            farmer = new Farmer();
        else
            farmer = farmersList.get(0);
        farmer.setUsername(updateFarmer.getUsername());
        farmer.setFirstName(updateFarmer.getFirstName());
        farmer.setLastName(updateFarmer.getLastName());
        farmer.setPincode(updateFarmer.getPincode());
        farmer.setPhnNumber(updateFarmer.getPhnNumber());
        return farmersrepository.save(farmer);
    }


    @Override
    public List<Supplier> getNearbySupplier(Long pincode) {
        return farmersUtil.getNearbySuppliers(pincode);
    }
}
