package com.leave.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class LeaveRangeInformation {

    @Id
    String name;
    LocalDate leaveStartDate;
    LocalDate leaveEndDate;
}
