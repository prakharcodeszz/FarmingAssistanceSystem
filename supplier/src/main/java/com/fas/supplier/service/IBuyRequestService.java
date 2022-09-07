package com.fas.supplier.service;

import com.fas.supplier.dtos.BuyRequestDetails;
import com.fas.supplier.dtos.ProposedRequest;
import org.hibernate.validator.constraints.Length;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Transactional
@Valid
public interface IBuyRequestService {
    BuyRequestDetails sendBuyRequest(@Valid ProposedRequest proposedRequest);
    List<BuyRequestDetails> fetchAllRequest(@Min(1) Long supplierId);
}
