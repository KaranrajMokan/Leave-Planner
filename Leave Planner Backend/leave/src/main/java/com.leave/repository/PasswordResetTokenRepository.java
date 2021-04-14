package com.leave.repository;

import com.leave.request.PasswordResetTokenInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenInformation, Long> {

    @Query(value = "SELECT * from password_reset_token_information p where p.token = ?1", nativeQuery = true)
    PasswordResetTokenInformation getPasswordResetInformationByToken(String token);
}
