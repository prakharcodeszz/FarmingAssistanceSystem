package com.fas.farmer.service;

import com.fas.farmer.constants.RequestStatus;
import com.fas.farmer.entities.BuyRequest;
import com.fas.farmer.exceptions.BuyRequestNotFoundException;
import com.fas.farmer.repository.IBuyRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BuyRequestService implements IBuyRequestService {

    @Autowired
    private IBuyRequestsRepository buyRequestRepository;

    @Override
    public List<BuyRequest> getBuyRequests(Long productId) {
        return buyRequestRepository.buyRequestsForProductId(productId);
    }

    @Override
    public BuyRequest acceptRequest(Long buyRequestId) {
        Optional<BuyRequest> buyRequestOptional = buyRequestRepository.findById(buyRequestId);
        if (!buyRequestOptional.isPresent())
            throw new BuyRequestNotFoundException("No buy request found for id: " + buyRequestId);
        BuyRequest buyRequest = buyRequestOptional.get();
        buyRequest.setStatus(RequestStatus.ACCEPTED);
        List<BuyRequest> buyRequestList = buyRequestRepository.buyRequestsForProductId(buyRequest.getProductId());
        for (BuyRequest buyRequestIter : buyRequestList) {
            buyRequestIter.setStatus(RequestStatus.REJECTED);
            buyRequestRepository.save(buyRequestIter);
        }
        return buyRequestRepository.save(buyRequest);
    }

    @Override
    public BuyRequest rejectRequest(Long buyRequestId) {
        Optional<BuyRequest> buyRequestOptional = buyRequestRepository.findById(buyRequestId);
        if (!buyRequestOptional.isPresent())
            throw new BuyRequestNotFoundException("No buy request found for id: " + buyRequestId);
        BuyRequest buyRequest = buyRequestOptional.get();
        buyRequest.setStatus(RequestStatus.REJECTED);
        return buyRequestRepository.save(buyRequest);
    }
}
