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

    public StudentsDetails findByRollNumber(String rollNumber){
        return studentsDetailsRepository.findByRollNumber(rollNumber);
    }

    public String findNameByRollNumber(String rollNumber){
        return studentsDetailsRepository.findNameByRollNumber(rollNumber);
    }

    public int insertDetailsByRollNumber(String rollNumber, String name, String email, String classId){
        return studentsDetailsRepository.insertDetailsByRollNumber(rollNumber,name,email,classId);
    }

    public String findClassIdByRollNumber(String rollNumber){
        return studentsDetailsRepository.findClassIdByRollNumber(rollNumber);
    }
}
