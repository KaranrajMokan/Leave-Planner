package com.leave.service;


import com.leave.model.Timetable;
import com.leave.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    public TimetableRepository timetableRepository;

    public List<Timetable> findAll(){
        return (List<Timetable>) timetableRepository.findAll();
    }
}
