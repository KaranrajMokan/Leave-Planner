package com.leave.repository;

import com.leave.model.LeaveDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDetailsRepository extends CrudRepository<LeaveDetails, String> {

}
