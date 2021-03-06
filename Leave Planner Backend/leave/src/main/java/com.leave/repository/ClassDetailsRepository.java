package com.leave.repository;

import com.leave.model.ClassDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends CrudRepository<ClassDetails, String> {

}
