package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveInformation {
    String rollNumber;
    String leaveType;
    LocalDate startDate;
    LocalDate endDate;
    String emailId;
}
