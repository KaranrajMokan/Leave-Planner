package com.leave.request;

import com.leave.model.StudentsDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PasswordResetTokenInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne(targetEntity = StudentsDetails.class)
    @JoinColumn(name = "rollno")
    StudentsDetails studentsDetails;

    String token;
    LocalDateTime expiryTime;

}
