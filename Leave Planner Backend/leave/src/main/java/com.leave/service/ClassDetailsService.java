package com.leave.service;

import com.leave.model.ClassDetails;
import com.leave.repository.ClassDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassDetailsService {

    @Autowired
    public ClassDetailsRepository classDetailsRepository;

    public List<ClassDetails> findAll(){
        return (List<ClassDetails>) classDetailsRepository.findAll();
    }

}
