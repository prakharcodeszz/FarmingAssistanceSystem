package com.fas.supplier.service;

import com.fas.supplier.dtos.*;
import com.fas.supplier.entities.BuyRequest;
import com.fas.supplier.entities.Supplier;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Service
@Validated
public interface ISupplierService {

    User loginWithCredentials(@Valid LoginCredentials loginCredentials);
    User logout(@Length(min = 6, max = 16, message = "Username should be of length b/w 6 and 16") String username);
    User changePassword(@Valid ChangePasswordRequest changPasswordRequest);

    Supplier updateSupplier(@Valid UpdateSupplier updateSupplier);

    List<ProductDetails> getProductsByPincode(@Min(1) @Max(999999) Long pincode);

}
