package com.leave.service;

import com.leave.model.CourseDetails;
import com.leave.repository.CourseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailsService {

    @Autowired
    public CourseDetailsRepository courseDetailsRepository;

    public List<CourseDetails> findAll() {
        return (List<CourseDetails>) courseDetailsRepository.findAll();
    }
}
