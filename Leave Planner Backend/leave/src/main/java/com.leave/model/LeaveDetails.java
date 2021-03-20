package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "leave_details")
public class LeaveDetails {

    @Id
    @Column(name="leave_id")
    private String leaveId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rollno")
    private StudentsDetails studentsDetails;

    @Column(name="leave_start_date")
    private LocalDate leaveStartDate;

    @Column(name="leave_end_date")
    private LocalDate leaveEndDate;

    @Column(name="leave_duration")
    private int leaveDuration;

    @Column(name="leave_type")
    private String leaveType;

    public LeaveDetails(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveDetails leaveDetails = (LeaveDetails) o;
        return Objects.equals(leaveId, leaveDetails.leaveId) &&
                Objects.equals(studentsDetails.getRollNumber(), leaveDetails.studentsDetails.getRollNumber()) &&
                Objects.equals(leaveStartDate, leaveDetails.leaveStartDate) &&
                Objects.equals(leaveEndDate, leaveDetails.leaveEndDate) &&
                Objects.equals(leaveDuration, leaveDetails.leaveDuration) &&
                Objects.equals(leaveType, leaveDetails.leaveType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaveId, studentsDetails.getRollNumber(), leaveStartDate, leaveEndDate, leaveDuration, leaveType);
    }

    @Override
    public String toString() {
        return "{ LeaveId=" + leaveId +
                ", Roll Number=" + studentsDetails.getRollNumber() +
                ", Leave Start Date=" + leaveStartDate +
                ", Leave End Date=" + leaveEndDate +
                ", Leave Duration=" + leaveDuration +
                ", Leave Type=" + leaveType +
                "}";
    }

}
