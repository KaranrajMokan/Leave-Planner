package com.leave.repository;

import com.leave.model.LoginDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LoginDetailsRepository extends CrudRepository<LoginDetails, String> {

    @Modifying
    @Query(value = "INSERT into login_details (login_details_key, rollno, password) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int insertDetailsById(int loginDetailsId, String rollNumber, String password);

    @Query(value = "SELECT password from login_details l where l.rollno = ?1", nativeQuery = true)
    String getPasswordByRollNumber(String rollNumber);

    @Query(value = "SELECT login_details_key from login_details l where l.rollno = ?1", nativeQuery = true)
    int getLoginIdByRollNumber(String rollNumber);

    @Query(value = "SELECT * from login_details l where l.rollno = ?1", nativeQuery = true)
    LoginDetails getLoginDetailsByRollNumber(String rollNumber);
}
