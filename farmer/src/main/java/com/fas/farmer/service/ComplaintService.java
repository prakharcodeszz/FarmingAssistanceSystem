package com.fas.farmer.service;

import com.fas.farmer.constants.ComplaintStatus;
import com.fas.farmer.dtos.AddComplaintRequest;
import com.fas.farmer.dtos.UserDetails;
import com.fas.farmer.entities.Complaint;
import com.fas.farmer.entities.Farmer;
import com.fas.farmer.exceptions.FarmerNotFoundException;
import com.fas.farmer.repository.IComplaintRepository;
import com.fas.farmer.repository.IFarmersRepository;
import com.fas.farmer.utils.FarmersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class ComplaintService implements IComplaintService {
    @Autowired
    private IComplaintRepository complaintRepository;
    @Autowired
    private IFarmersRepository farmersrepository;

    @Autowired
    private FarmersUtil farmersUtil;


    @Override
    public Complaint addComplaint(AddComplaintRequest addComplaintRequest) {
        Optional<Farmer> farmerOptional = farmersrepository.findById(addComplaintRequest.getFarmerId());
        if (!farmerOptional.isPresent())
            throw new FarmerNotFoundException("Farmer not found for id: " + addComplaintRequest.getFarmerId());
        Farmer farmer = farmerOptional.get();
        UserDetails userDetails = farmersUtil.getUserDetails(farmer.getUsername());
        farmersUtil.isFarmerLoggedIn(userDetails);


        Complaint complaint = new Complaint();
        complaint.setFarmerId(addComplaintRequest.getFarmerId());
        complaint.setComplaintDescription(addComplaintRequest.getComplaintDescription());
        complaint.setStatus(ComplaintStatus.UNRESOLVED);
        return complaintRepository.save(complaint);
    }
}
