package com.leave.repository;

import com.leave.model.StudentsDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsDetailsRepository extends CrudRepository<StudentsDetails, String> {

}
