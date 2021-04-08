package com.leave.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateInformation {
    String rollNumber;
    String leaveId;
    String leaveType;
    LocalDate startDate;
    LocalDate endDate;
    String emailId;
    String studentToken;
}
