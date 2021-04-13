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
@Table(name = "timetable")
public class Timetable {

    @Id
    @Column(name = "timetable_id")
    private String timetableId;

    @Column(name = "class_id")
    private String classId;

    public Timetable() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timetable timetable = (Timetable) o;
        return Objects.equals(timetableId, timetable.timetableId) &&
                Objects.equals(classId, timetable.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timetableId, classId);
    }

    @Override
    public String toString() {
        return "{ Timetable Id=" + timetableId +
                ", Class Id=" + classId +
                "}";
    }
}
