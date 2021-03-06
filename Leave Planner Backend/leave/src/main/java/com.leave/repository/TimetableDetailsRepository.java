package com.leave.repository;

import com.leave.model.TimetableDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableDetailsRepository extends CrudRepository<TimetableDetails, String> {

}
