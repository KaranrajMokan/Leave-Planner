package com.leave.repository;

import com.leave.model.LoginDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDetailsRepository extends CrudRepository<LoginDetails, String> {

}
