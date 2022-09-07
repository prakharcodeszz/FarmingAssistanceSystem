package com.fas.farmer.service;

import com.fas.farmer.entities.BuyRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public interface IBuyRequestService {

    List<BuyRequest> getBuyRequests(Long productId);

    BuyRequest acceptRequest(Long buyRequestId);

    BuyRequest rejectRequest(Long buyRequestId);
}
