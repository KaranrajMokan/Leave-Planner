package com.leave.service;


import com.leave.model.StudentsDetails;
import com.leave.repository.StudentsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsDetailsService {

    @Autowired
    public StudentsDetailsRepository studentsDetailsRepository;

    public List<StudentsDetails> findAll(){
        return (List<StudentsDetails>) studentsDetailsRepository.findAll();
    }
}
