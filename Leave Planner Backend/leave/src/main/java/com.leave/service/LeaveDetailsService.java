package com.leave.service;

import com.leave.model.LeaveDetails;
import com.leave.repository.LeaveDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveDetailsService {

    @Autowired
    public LeaveDetailsRepository leaveDetailsRepository;

    public List<LeaveDetails> findAll(){
        return (List<LeaveDetails>) leaveDetailsRepository.findAll();
    }

}

