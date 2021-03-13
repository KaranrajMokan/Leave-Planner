package com.leave.controller;

import com.leave.model.*;
import com.leave.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
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

	Logger logger = Logger.getLogger(Logger.class.getName());

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


	@PostMapping("/login-details")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<StudentsDetails> getLoginDetails(@RequestBody LoginInformation loginInformation){
		List<LoginDetails> loginDetailsList = loginDetailsService.findAll();
		String errorMessage = "Incorrect Roll number and Incorrect password";
		for(LoginDetails loginDetail: loginDetailsList){
			if (loginDetail.getStudentsDetails().getRollNumber().equals(loginInformation.getRollno())){
				if (loginDetail.getPassword().equals(loginInformation.getPassword())) {
					StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(loginInformation.getRollno());
					logger.info("Login is successful");
					return ResponseEntity.status(HttpStatus.OK).header("Message","Login is successful").body(studentsDetails);
				}
				else
					errorMessage = "Incorrect Password";
			}
		}
		logger.info(errorMessage);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Message",errorMessage).body(null);
	}
}
