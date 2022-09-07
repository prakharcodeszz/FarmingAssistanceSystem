package com.fas.supplier.contorllers;

import com.fas.supplier.dtos.*;
import com.fas.supplier.entities.Supplier;
import com.fas.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
@Validated
public class SupplierController {

    @Autowired
    private ISupplierService service;

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginCredentials loginCredentials) {
        return service.loginWithCredentials(loginCredentials);
    }

    @GetMapping("/logout/{username}")
    public User logout(@PathVariable String username) {
        return service.logout(username);
    }

    @PostMapping("/changePassword")
    public User changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return service.changePassword(changePasswordRequest);
    }

    @PostMapping("/updateSupplier")
    public Supplier updateSupplier(@Valid @RequestBody UpdateSupplier updateSupplier){
        return service.updateSupplier(updateSupplier);
    }

    @GetMapping("/products/byPicode/{pincode}")
    public List<ProductDetails> getProductsByPincode(@PathVariable Long pincode){
        return service.getProductsByPincode(pincode);
    }
}
