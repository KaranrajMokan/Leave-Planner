package com.leave.service;

import com.leave.model.LeaveDetails;
import com.leave.repository.LeaveDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveDetailsService {

    @Autowired
    public LeaveDetailsRepository leaveDetailsRepository;

    public List<LeaveDetails> findAll(){
        return (List<LeaveDetails>) leaveDetailsRepository.findAll();
    }

    public List<LeaveDetails> findUpcomingLeavesByRollNumber(String rollNumber){
        return leaveDetailsRepository.findUpcomingLeaves(rollNumber,Date.valueOf(LocalDate.now()));
    }

    public List<LeaveDetails> findPastLeavesByRollNumber(String rollNumber){
        return leaveDetailsRepository.findPastLeaves(rollNumber,Date.valueOf(LocalDate.now()));
    }

    public int deleteLeavesByLeaveId(String leaveId){
        return leaveDetailsRepository.deleteLeavesById(leaveId);
    }

    public LeaveDetails getLeaveById(String leaveId){
        return leaveDetailsRepository.getLeaveById(leaveId);
    }
}

