package com.fas.farmer.repository;

import com.fas.farmer.entities.BuyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBuyRequestsRepository extends JpaRepository<BuyRequest, Long> {

    @Query("from BuyRequest where productId=:productId")
    public List<BuyRequest> buyRequestsForProductId(@Param("productId") Long productId);
}
