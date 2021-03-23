package com.leave.controller;

import com.leave.config.JwtCreator;
import com.leave.config.JwtVerifier;
import com.leave.model.*;
import com.leave.repository.LeaveDetailsRepository;
import com.leave.request.LeaveInformation;
import com.leave.request.LoginInformation;
import com.leave.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
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
	LeaveDetailsRepository leaveDetailsRepository;

	@Autowired
	LoginDetailsService loginDetailsService;

	@Autowired
	LeaveDetailsService leaveDetailsService;

	Logger logger = Logger.getLogger(Logger.class.getName());
	JwtVerifier jwtVerifier = new JwtVerifier();
	JwtCreator jwtCreator = new JwtCreator();

	@Value("${JWT_SECRET}")
	String secret;

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

	@PostMapping("/leave-details")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> postLeaveDetails(@RequestBody LeaveInformation leaveInformation){
		String rollNumber = leaveInformation.getRollNumber();
		String token = leaveInformation.getStudentToken();
		LeaveDetails leaveDetails = new LeaveDetails();
		StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
		jwtVerifier.verifier(secret, rollNumber,token);
		String leaveId;
		List<LeaveDetails> leaveDetailsList = leaveDetailsService.findAll();
		if (leaveDetailsList.size()==0){
			leaveId="0";
		}
		else{
			long leavesId = Long.parseLong(leaveDetailsList.stream().max(Comparator.comparing(LeaveDetails::getLeaveId)).get().getLeaveId());
			leaveId = String.valueOf(leavesId+1);
		}

		leaveDetails.setLeaveId(leaveId);
		leaveDetails.setStudentsDetails(studentsDetails);
		leaveDetails.setLeaveStartDate(leaveInformation.getStartDate());
		leaveDetails.setLeaveEndDate(leaveInformation.getEndDate());
		leaveDetails.setLeaveType(leaveInformation.getLeaveType());
		leaveDetails.setLeaveDuration(0);
		leaveDetailsRepository.save(leaveDetails);
		logger.info("Leave is planned successfully");
		return ResponseEntity.status(HttpStatus.OK).body("");
	}


	@PostMapping("/login-details")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<StudentsDetails> getLoginDetails(@RequestBody LoginInformation loginInformation){
		List<LoginDetails> loginDetailsList = loginDetailsService.findAll();
		String errorMessage = "Incorrect roll number & password";
		StudentsDetails studentsDetails = new StudentsDetails();
		for(LoginDetails loginDetail: loginDetailsList){
			if (loginDetail.getStudentsDetails().getRollNumber().equals(loginInformation.getRollno())){
				if (loginDetail.getPassword().equals(loginInformation.getPassword())) {
					String rollNumber = loginInformation.getRollno();
					studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
					String token = jwtCreator.creator(secret,rollNumber);
					studentsDetails.setStudentToken(token);
					logger.info("Login is successful");
					return ResponseEntity.status(HttpStatus.OK).header("Message","Login is successful").body(studentsDetails);
				}
				else
					errorMessage = "Incorrect password";
			}
		}
		logger.info(errorMessage);
		studentsDetails.setStudentToken(errorMessage);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Message",errorMessage).body(studentsDetails);
	}
}
