package com.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "timetable_details")
public class TimetableDetails {

    @Id
    @Column(name = "timetable_details_key")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    @Column(name = "day")
    private String day;

    @Column(name = "duration")
    private String duration;

    @Column(name = "course_id")
    private String courseId;

    public TimetableDetails() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimetableDetails timetableDetails = (TimetableDetails) o;
        return Objects.equals(timetable, timetableDetails.timetable) &&
                Objects.equals(day, timetableDetails.day) &&
                Objects.equals(duration, timetableDetails.duration) &&
                Objects.equals(courseId, timetableDetails.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timetable, duration, day, courseId);
    }

    @Override
    public String toString() {
        return "{ Timetable Id=" + timetable.getTimetableId() +
                ", Duration=" + duration +
                ", Day=" + day +
                ", Course Id=" + courseId +
                "}";
    }
}

