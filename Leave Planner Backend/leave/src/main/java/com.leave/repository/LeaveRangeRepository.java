package com.leave.repository;

import com.leave.request.LeaveRangeInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRangeRepository extends CrudRepository<LeaveRangeInformation,String> {

    @Query(value = "SELECT sd.name, ld.leave_start_date, ld.leave_end_date from leave_details ld join students_details sd on ld.rollno = sd.rollno " +
            "where sd.rollno <> ?1 and ld.leave_start_date <= ?2 and ld.leave_end_date >= ?2", nativeQuery = true)
    List<LeaveRangeInformation> getLeavesByDate(String rollNumber, LocalDate date);
}
