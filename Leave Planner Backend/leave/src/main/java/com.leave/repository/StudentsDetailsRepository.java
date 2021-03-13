package com.leave.repository;

import com.leave.model.StudentsDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsDetailsRepository extends CrudRepository<StudentsDetails, String> {

    @Query(value = "SELECT * from students_details s where s.rollno = ?1", nativeQuery = true)
    StudentsDetails findByRollNumber(String rollNumber);
}
