package com.leave.repository;

import com.leave.model.LeaveDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface LeaveDetailsRepository extends CrudRepository<LeaveDetails, String> {

    @Query(value = "SELECT * from leave_details l where l.rollno = ?1 and l.leave_end_date >= ?2", nativeQuery = true)
    List<LeaveDetails> findUpcomingLeaves(String rollNumber, Date date);

    @Query(value = "SELECT * from leave_details l where l.rollno = ?1 and l.leave_end_date < ?2", nativeQuery = true)
    List<LeaveDetails> findPastLeaves(String rollNumber, Date date);
}
