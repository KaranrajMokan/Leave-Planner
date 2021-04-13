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

    public List<LoginDetails> findAll() {
        return (List<LoginDetails>) loginDetailsRepository.findAll();
    }

    public int insertDetailsById(int loginDetailsId, String rollNumber, String password) {
        return loginDetailsRepository.insertDetailsById(loginDetailsId, rollNumber, password);
    }

    public String getPasswordByRollNumber(String rollNumber) {
        return loginDetailsRepository.getPasswordByRollNumber(rollNumber);
    }

    public int getLoginIdByRollNumber(String rollNumber) {
        return loginDetailsRepository.getLoginIdByRollNumber(rollNumber);
    }
}
