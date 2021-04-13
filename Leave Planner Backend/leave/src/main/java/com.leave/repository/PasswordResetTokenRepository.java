package com.leave.repository;

import com.leave.request.PasswordResetTokenInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenInformation, Long> {

}
