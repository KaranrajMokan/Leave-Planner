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
@Table(name = "class_details")
public class ClassDetails {

    @Id
    @Column(name="class_id")
    private String classId;

    @Column(name="tutor_name")
    private String tutorName;

    @Column(name="tutor_email")
    private String tutorEmail;

    public ClassDetails(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDetails classDetails = (ClassDetails) o;
        return Objects.equals(classId, classDetails.classId) &&
                Objects.equals(tutorName, classDetails.tutorName) &&
                Objects.equals(tutorEmail, classDetails.tutorEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, tutorName, tutorEmail);
    }

    @Override
    public String toString() {
        return "{ Class Id=" + classId +
                ", Tutor Name=" + tutorName +
                ", Tutor Email=" + tutorEmail +
                "}";
    }

}
