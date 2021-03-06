package com.leave.service;

import com.leave.model.LoginDetails;
import com.leave.repository.LoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginDetailsService {

    @Autowired
    public LoginDetailsRepository loginDetailsRepository;

    public List<LoginDetails> findAll(){
        return (List<LoginDetails>) loginDetailsRepository.findAll();
    }
}
