package com.leave.service;

import com.leave.model.Course;
import com.leave.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    public CourseRepository courseRepository;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }
}
