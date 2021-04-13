package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "course_details")
public class CourseDetails {

    @Id
    @Column(name = "course_details_key")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rollno")
    private StudentsDetails studentsDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseDetails() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDetails courseDetails = (CourseDetails) o;
        return Objects.equals(studentsDetails.getRollNumber(), courseDetails.studentsDetails.getRollNumber()) &&
                Objects.equals(course.getCourseId(), courseDetails.course.getCourseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentsDetails, course);
    }

    @Override
    public String toString() {
        return "{ Roll Number=" + studentsDetails.getRollNumber() +
                ", Course Id" + course.getCourseId() +
                ", Course Name=" + course.getCourseName() +
                "}";
    }
}
