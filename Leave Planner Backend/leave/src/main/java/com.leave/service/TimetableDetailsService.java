package com.leave.service;

import com.leave.model.TimetableDetails;
import com.leave.repository.TimetableDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableDetailsService {

    @Autowired
    public TimetableDetailsRepository timetableDetailsRepository;

    public List<TimetableDetails> findAll(){
        return (List<TimetableDetails>) timetableDetailsRepository.findAll();
    }
}
