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
@Table(name="course")
public class Course {

    @Id
    @Column(name="course_id")
    private String courseId;

    @Column(name="course_name")
    private String courseName;

    @Column(name="faculty_name")
    private String facultyName;
    
    public Course(){
        
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(facultyName, course.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, facultyName);
    }

    @Override
    public String toString() {
        return "{ Course Id=" + courseId +
                ", Course Name=" + courseName +
                ", Faculty Name=" + facultyName +
                "}";
    }
}
