package com.fas.supplier.service;

import com.fas.supplier.dtos.BuyRequestDetails;
import com.fas.supplier.dtos.ProductDetails;
import com.fas.supplier.dtos.ProposedRequest;
import com.fas.supplier.dtos.UserDetails;
import com.fas.supplier.entities.BuyRequest;
import com.fas.supplier.entities.Supplier;
import com.fas.supplier.exceptions.SupplierNotFoundException;
import com.fas.supplier.repository.IBuyRequestRepository;
import com.fas.supplier.repository.ISupplierRepository;
import com.fas.supplier.utilities.BuyRequestUtility;
import com.fas.supplier.utilities.SupplierUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuyRequestService implements IBuyRequestService {

    @Autowired
    private BuyRequestUtility buyRequestUtility;
    @Autowired
    private SupplierUtility suppliersUtil;
    @Autowired
    private IBuyRequestRepository buyRequestRepository;
    @Autowired
    private ISupplierRepository suppliersRepository;

    @Override
    public BuyRequestDetails sendBuyRequest(@Valid ProposedRequest proposedRequest) {
        Optional<Supplier> supplierOptional = suppliersRepository.findById(proposedRequest.getSupplierId());
        if (!supplierOptional.isPresent())
            throw new SupplierNotFoundException("No supplier found for id: " + proposedRequest.getSupplierId());
        Supplier supplier = new Supplier();
        UserDetails userDetails = suppliersUtil.getUserDetails(supplier.getUsername());
        suppliersUtil.isSupplierLoggedIn(userDetails);

        BuyRequest buyRequest = new BuyRequest();
        buyRequestUtility.getProductDetailsById(proposedRequest.getProductId());
        buyRequest.setProductId(proposedRequest.getProductId());
        buyRequest.setSupplierId(proposedRequest.getSupplierId());
        buyRequest.setAskedPrice(proposedRequest.getAskedPrice());
        buyRequestRepository.save(buyRequest);
        return buyRequestUtility.toBuyRequestDetails(buyRequest);
    }

    @Override
    public List<BuyRequestDetails> fetchAllRequest(Long supplierId) {
        Optional<Supplier> supplierOptional = suppliersRepository.findById(supplierId);
        if (!supplierOptional.isPresent())
            throw new SupplierNotFoundException("No supplier found for id: " + supplierId);
        Supplier supplier = new Supplier();
        UserDetails userDetails = suppliersUtil.getUserDetails(supplier.getUsername());
        suppliersUtil.isSupplierLoggedIn(userDetails);

        List<BuyRequest> buyRequestsList = buyRequestRepository.getBuyRequestsBySupplierId(supplierId);
        return buyRequestUtility.toBuyRequestDetails(buyRequestsList);
    }
}
