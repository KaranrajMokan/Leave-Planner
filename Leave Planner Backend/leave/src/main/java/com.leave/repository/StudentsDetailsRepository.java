package com.leave.repository;

import com.leave.model.StudentsDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentsDetailsRepository extends CrudRepository<StudentsDetails, String> {

    @Query(value = "SELECT * from students_details s where s.rollno = ?1", nativeQuery = true)
    StudentsDetails findByRollNumber(String rollNumber);

    @Query(value = "SELECT name from students_details s where s.rollno = ?1", nativeQuery = true)
    String findNameByRollNumber(String rollNumber);

    @Modifying
    @Query(value = "INSERT into students_details (rollno, name, email_id, class_id) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertDetailsByRollNumber(String rollNumber, String name, String email, String classId);
}
