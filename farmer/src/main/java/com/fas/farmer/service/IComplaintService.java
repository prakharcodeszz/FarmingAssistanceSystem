package com.fas.farmer.service;

import com.fas.farmer.dtos.AddComplaintRequest;
import com.fas.farmer.entities.Complaint;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public interface IComplaintService {
    Complaint addComplaint(@Valid AddComplaintRequest addComplaintRequest);
}
