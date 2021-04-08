package com.leave.controller;

import com.leave.config.JwtCreator;
import com.leave.config.JwtVerifier;
import com.leave.config.MailGenerator;
import com.leave.config.Utils;
import com.leave.model.LeaveDetails;
import com.leave.model.LoginDetails;
import com.leave.model.StudentsDetails;
import com.leave.repository.LeaveDetailsRepository;
import com.leave.request.*;
import com.leave.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class Controller {

	@Autowired
	StudentsDetailsService studentsDetailsService;

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

	@Value("${LP_MAIL}")
	String senderMail;

	@Value("${LP_PASS}")
	String senderPassword;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/upcoming-leaves")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LeaveDetails>> getUpcomingLeaves(@RequestBody DetailsInformation detailsInformation){
		String rollNumber = detailsInformation.getRollNumber();
		String token = detailsInformation.getToken();
		jwtVerifier.verifier(secret,rollNumber,token);
		List<LeaveDetails> leaveDetailsList = leaveDetailsService.findUpcomingLeavesByRollNumber(rollNumber);
		if(leaveDetailsList.size()>0)
			logger.info("Successfully fetched upcoming leaves for "+rollNumber);
		else
			logger.info("No upcoming leaves are available for "+rollNumber);
		return ResponseEntity.status(HttpStatus.OK).body(leaveDetailsList);
	}

	@PostMapping("/leaves-history")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LeaveDetails>> getPastLeaves(@RequestBody DetailsInformation detailsInformation){
		String rollNumber = detailsInformation.getRollNumber();
		String token = detailsInformation.getToken();
		jwtVerifier.verifier(secret,rollNumber,token);
		List<LeaveDetails> leaveDetailsList = leaveDetailsService.findPastLeavesByRollNumber(rollNumber);
		leaveDetailsList.sort(Collections.reverseOrder());
		if(leaveDetailsList.size()>0)
			logger.info("Successfully fetched past leaves for "+rollNumber);
		else
			logger.info("No leaves history is available for "+rollNumber);
		return ResponseEntity.status(HttpStatus.OK).body(leaveDetailsList);
	}

	@PutMapping("/update-leaves")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> updateLeave(@RequestBody UpdateInformation updateInformation){
		String leaveId = updateInformation.getLeaveId();
		String leaveType = updateInformation.getLeaveType();
		String rollNumber = updateInformation.getRollNumber();
		String token = updateInformation.getStudentToken();
		jwtVerifier.verifier(secret,rollNumber,token);
		String responseMessage="Leave with "+leaveId+" can not be found";
		StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
		LocalDate startDate = updateInformation.getStartDate();
		LocalDate endDate = updateInformation.getEndDate();
		int duration = Utils.calculateDuration(startDate,endDate);
		LeaveDetails leaveDetails = leaveDetailsService.getLeaveById(leaveId);
		if(leaveDetails != null){
			leaveDetails.setLeaveId(leaveId);
			leaveDetails.setLeaveType(leaveType);
			leaveDetails.setLeaveDuration(duration);
			leaveDetails.setLeaveStartDate(startDate);
			leaveDetails.setLeaveEndDate(endDate);
			leaveDetails.setStudentsDetails(studentsDetails);
			leaveDetailsRepository.save(leaveDetails);
			responseMessage="Leave is updated successfully";
			logger.info(responseMessage);
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		logger.info(responseMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	}

	@DeleteMapping("/delete-leaves")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteLeave(@RequestBody DeleteInformation deleteInformation){
		String leaveId = deleteInformation.getLeaveId();
		String rollNumber = deleteInformation.getRollNumber();
		String token = deleteInformation.getToken();
		jwtVerifier.verifier(secret,rollNumber,token);
		String responseMessage="Leave with "+leaveId+" can not be found";
		int response = leaveDetailsService.deleteLeavesByLeaveId(leaveId);
		if(response != 0){
			responseMessage="Leave is deleted successfully";
			logger.info(responseMessage);
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		logger.info(responseMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	}

	@PostMapping("/leave-details")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> postLeaveDetails(@RequestBody LeaveInformation leaveInformation) throws UnsupportedEncodingException, MessagingException {
		String rollNumber = leaveInformation.getRollNumber();
		String token = leaveInformation.getStudentToken();
		LeaveDetails leaveDetails = new LeaveDetails();
		StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
		String name = studentsDetailsService.findNameByRollNumber(rollNumber);
		jwtVerifier.verifier(secret,rollNumber,token);
		String leaveId,message;
		LocalDate startDate = leaveInformation.getStartDate();
		LocalDate endDate = leaveInformation.getEndDate();
 		int duration = Utils.calculateDuration(startDate,endDate);
 		if(duration > 0) {
			List<LeaveDetails> leaveDetailsList = leaveDetailsService.findAll();
			if (leaveDetailsList.size()==0){
				leaveId="0";
			}
			else {
				int max=0,temp=0;
				for(int i=0;i<leaveDetailsList.size();i++){
					temp = Integer.parseInt(leaveDetailsList.get(i).getLeaveId());
					if(temp > max)
						max = temp;
				}
				leaveId = String.valueOf(max+1);
			}
			leaveDetails.setLeaveId(leaveId);
			leaveDetails.setStudentsDetails(studentsDetails);
			leaveDetails.setLeaveStartDate(startDate);
			leaveDetails.setLeaveEndDate(endDate);
			leaveDetails.setLeaveType(leaveInformation.getLeaveType());
			leaveDetails.setLeaveDuration(duration);
			leaveDetailsRepository.save(leaveDetails);
			if(!leaveInformation.getEmailId().equals("")) {
				String[] listOfEmails = leaveInformation.getEmailId().split(",");
				for (String listOfEmail : listOfEmails) {
					MailGenerator.sendMails(senderMail, senderPassword, listOfEmail, name, rollNumber, leaveInformation.getLeaveType(), startDate, endDate, duration);
				}
			}
			message="Leave is planned successfully";
			logger.info(message);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
 		message="Leaves can not be planned on weekends";
 		logger.info(message);
 		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
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
