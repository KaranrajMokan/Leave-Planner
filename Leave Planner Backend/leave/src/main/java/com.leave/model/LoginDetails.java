package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "login_details")
public class LoginDetails {

    @Id
    @Column(name = "login_details_key")
    private int loginDetailsKey;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rollno")
    private StudentsDetails studentsDetails;

    @Column(name = "password")
    private String password;

    public LoginDetails() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDetails loginDetails = (LoginDetails) o;
        return Objects.equals(studentsDetails.getRollNumber(), loginDetails.studentsDetails.getRollNumber()) &&
                Objects.equals(password, loginDetails.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentsDetails, password);
    }

    @Override
    public String toString() {
        return "{ Roll Number=" + studentsDetails.getRollNumber() +
                ", Password=" + password +
                "}";
    }
}
