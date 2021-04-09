package com.leave.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveCountInformation {
    String rollNumber;
    String token;
    LocalDate date;
}
