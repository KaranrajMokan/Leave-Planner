package com.leave.repository;

import com.leave.model.CourseDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends CrudRepository<CourseDetails, String> {
}
