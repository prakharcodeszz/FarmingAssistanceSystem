package com.fas.farmer.controller;

import com.fas.farmer.dtos.*;
import com.fas.farmer.entities.BuyRequest;
import com.fas.farmer.entities.Complaint;
import com.fas.farmer.entities.Farmer;
import com.fas.farmer.service.IBuyRequestService;
import com.fas.farmer.service.IComplaintService;
import com.fas.farmer.service.IFarmersService;
import com.fas.farmer.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmers")
@Validated
public class FarmersController {

    @Autowired
    private IFarmersService farmerService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IBuyRequestService buyRequestService;
    @Autowired
    private IComplaintService complaintService;

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginCredentials loginCredentials) {
        return farmerService.loginWithCredentials(loginCredentials);
    }

    @GetMapping("/logout/{username}")
    public User logout(@PathVariable String username) {
        return farmerService.logout(username);
    }

    @PostMapping("/changePassword")
    public User changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return farmerService.changePassword(changePasswordRequest);
    }

    @PostMapping("/updateFarmer")
    public Farmer updateSupplier(@Valid @RequestBody UpdateFarmer updateFarmer) {
        return farmerService.updateFarmer(updateFarmer);
    }

    @PostMapping("/products/add")
    public ProductDetails addProducts(@Valid @RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }

    @GetMapping("/products/getById/{productId}")
    public ProductDetails getProductById(@PathVariable Long productId){
        return productService.getProductDetails(productId);
    }
    @GetMapping("/products/findByPincode/{pincode}")
    public List<ProductDetails> getProductsByPincode(@PathVariable Long pincode){
        return productService.getProductsByPincode(pincode);
    }

    @GetMapping("/products/byFarmerId/{farmerId}")
    public List<ProductDetails> getProductsByFarmerId(@PathVariable Long farmerId){
        return productService.getProductsByFarmerId(farmerId);
    }
    @GetMapping("/getBuyRequests/{productId}")
    public List<BuyRequest> getBuyRequests(@PathVariable Long productId){
        return buyRequestService.getBuyRequests(productId);
    }

    @GetMapping("/acceptRequest/{buyRequestId}")
    public BuyRequest acceptRequest(@PathVariable Long buyRequestId){
        return buyRequestService.acceptRequest(buyRequestId);
    }

    @GetMapping("/rejectRequest/{buyRequestId}")
    public BuyRequest rejectRequest(@PathVariable Long buyRequestId){
        return buyRequestService.rejectRequest(buyRequestId);
    }

    @PostMapping("/complaints/raise")
    public Complaint addComplaint(@Valid @RequestBody AddComplaintRequest addComplaintRequest){
        return complaintService.addComplaint(addComplaintRequest);
    }

}
