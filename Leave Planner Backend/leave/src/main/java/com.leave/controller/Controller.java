package com.leave.controller;

import com.leave.model.*;
import com.leave.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

	@Autowired
	StudentsDetailsService studentsDetailsService;

	@Autowired
	CourseService courseService;

	@Autowired
	TimetableDetailsService timetableDetailsService;

	@Autowired
	CourseDetailsService courseDetailsService;

	@Autowired
	LeaveDetailsService leaveDetailsService;

	@Autowired
	LoginDetailsService loginDetailsService;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/students-details")
	public List<String> getStudentsDetails(){
		List<StudentsDetails> studentsDetailsList = studentsDetailsService.findAll();
		//HashMap<String, Object> studentsDetails = new HashMap<>();
		//studentsDetails.put("students",studentsDetailsList);
		List<String> resultantString = new ArrayList<>();
		for(StudentsDetails studentsDetail: studentsDetailsList){
			resultantString.add(studentsDetail.toString());
		}
		return resultantString;
	}

	@GetMapping("/course")
	public List<String> getCourse(){
		List<Course> courseList = courseService.findAll();
		List<String> resultantString = new ArrayList<>();
		for(Course course: courseList){
			resultantString.add(course.toString());
		}
		return resultantString;
	}

	@GetMapping("/course-details")
	public List<String> getCourseDetails(){
		List<CourseDetails> courseDetailsList = courseDetailsService.findAll();
		List<String> resultantString = new ArrayList<>();
		for(CourseDetails courseDetail: courseDetailsList){
			resultantString.add(courseDetail.toString());
		}
		return resultantString;
	}

	@GetMapping("/timetable-details")
	public List<String> getTimetableDetails(){
		List<TimetableDetails> timetableDetailsList = timetableDetailsService.findAll();
		List<String> resultantString = new ArrayList<>();
		for(TimetableDetails timetableDetail: timetableDetailsList){
			resultantString.add(timetableDetail.toString());
		}
		return resultantString;
	}

	@GetMapping("/leave-details")
	public List<String> getLeaveDetails(){
		List<LeaveDetails> leaveDetailsList = leaveDetailsService.findAll();
		List<String> resultantString = new ArrayList<>();
		for(LeaveDetails leaveDetail: leaveDetailsList){
			resultantString.add(leaveDetail.toString());
		}
		return resultantString;
	}

	@GetMapping("/login-details")
	public List<String> getLoginDetails(){
		List<LoginDetails> loginDetailsList = loginDetailsService.findAll();
		List<String> resultantString = new ArrayList<>();
		for(LoginDetails loginDetail: loginDetailsList){
			resultantString.add(loginDetail.toString());
		}
		return resultantString;
	}
}
