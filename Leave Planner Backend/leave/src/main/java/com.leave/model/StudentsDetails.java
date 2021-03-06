package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "students_details")
public class StudentsDetails {

    @Id
    @Column(name="rollno")
    private String rollNumber;

    @Column(name="name")
    private String name;

    @Column(name="email_id")
    private String emailId;

    @Column(name="class_id")
    private String classId;

    public StudentsDetails(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsDetails studentsDetails = (StudentsDetails) o;
        return Objects.equals(rollNumber, studentsDetails.rollNumber) &&
                Objects.equals(emailId, studentsDetails.emailId) &&
                Objects.equals(name, studentsDetails.name) &&
                Objects.equals(classId, studentsDetails.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber, name, emailId, classId);
    }

    @Override
    public String toString() {
        return "{ Roll Number=" + rollNumber +
                ", Name=" + name +
                ", Email Id=" + emailId +
                ", Class Id=" + classId +
                "}";
    }


}
