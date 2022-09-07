package com.fas.farmer.service;

import com.fas.farmer.dtos.*;
import com.fas.farmer.entities.BuyRequest;
import com.fas.farmer.entities.Complaint;
import com.fas.farmer.entities.Farmer;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Service
public interface IFarmersService {

    User loginWithCredentials(@Valid LoginCredentials loginCredentials);

    User logout(@Length(min = 6, max = 16, message = "Username should be of length b/w 6 and 16") String username);

    User changePassword(@Valid ChangePasswordRequest changPasswordRequest);

    Farmer updateFarmer(@Valid UpdateFarmer updateFarmer);

    List<Supplier> getNearbySupplier(Long pincode);

}
