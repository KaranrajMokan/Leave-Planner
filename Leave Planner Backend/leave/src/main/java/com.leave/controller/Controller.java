package com.leave.controller;

import com.leave.config.JwtCreator;
import com.leave.config.JwtVerifier;
import com.leave.config.MailGenerator;
import com.leave.config.Utils;
import com.leave.model.LeaveDetails;
import com.leave.model.LoginDetails;
import com.leave.model.StudentsDetails;
import com.leave.repository.LeaveDetailsRepository;
import com.leave.repository.LoginDetailsRepository;
import com.leave.repository.PasswordResetTokenRepository;
import com.leave.repository.StudentsDetailsRepository;
import com.leave.request.*;
import com.leave.service.LeaveDetailsService;
import com.leave.service.LoginDetailsService;
import com.leave.service.StudentsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class Controller {

    @Autowired
    StudentsDetailsService studentsDetailsService;

    @Autowired
    StudentsDetailsRepository studentsDetailsRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    LoginDetailsRepository loginDetailsRepository;

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
    public ResponseEntity<List<LeaveDetails>> getUpcomingLeaves(@RequestBody DetailsInformation detailsInformation) {
        String rollNumber = detailsInformation.getRollNumber();
        String token = detailsInformation.getToken();
        jwtVerifier.verifier(secret, rollNumber, token);
        List<LeaveDetails> leaveDetailsList = leaveDetailsService.findUpcomingLeavesByRollNumber(rollNumber);
        Collections.sort(leaveDetailsList);
        if (leaveDetailsList.size() > 0)
            logger.info("Successfully fetched upcoming leaves for " + rollNumber);
        else
            logger.info("No upcoming leaves are available for " + rollNumber);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDetailsList);
    }

    @PostMapping("/leaves-history")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LeaveDetails>> getPastLeaves(@RequestBody DetailsInformation detailsInformation) {
        String rollNumber = detailsInformation.getRollNumber();
        String token = detailsInformation.getToken();
        jwtVerifier.verifier(secret, rollNumber, token);
        List<LeaveDetails> leaveDetailsList = leaveDetailsService.findPastLeavesByRollNumber(rollNumber);
        leaveDetailsList.sort(Collections.reverseOrder());
        if (leaveDetailsList.size() > 0)
            logger.info("Successfully fetched past leaves for " + rollNumber);
        else
            logger.info("No leaves history is available for " + rollNumber);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDetailsList);
    }

    @PutMapping("/update-leaves")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateLeave(@RequestBody UpdateInformation updateInformation) throws MessagingException {
        String leaveId = updateInformation.getLeaveId();
        String leaveType = updateInformation.getLeaveType();
        String rollNumber = updateInformation.getRollNumber();
        String token = updateInformation.getStudentToken();
        jwtVerifier.verifier(secret, rollNumber, token);
        String name = studentsDetailsService.findNameByRollNumber(rollNumber);
        String responseMessage = "Leave with " + leaveId + " can not be found";
        StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
        LocalDate startDate = updateInformation.getStartDate();
        LocalDate endDate = updateInformation.getEndDate();
        int duration = Utils.calculateDuration(startDate, endDate);
        LeaveDetails leaveDetails = leaveDetailsService.getLeaveById(leaveId);
        if (leaveDetails != null) {
            String oldLeaveType = leaveDetails.getLeaveType();
            LocalDate oldStartDate = leaveDetails.getLeaveStartDate();
            LocalDate oldEndDate = leaveDetails.getLeaveEndDate();
            int oldDuration = leaveDetails.getLeaveDuration();
            leaveDetails.setLeaveId(leaveId);
            leaveDetails.setLeaveType(leaveType);
            leaveDetails.setLeaveDuration(duration);
            leaveDetails.setLeaveStartDate(startDate);
            leaveDetails.setLeaveEndDate(endDate);
            leaveDetails.setStudentsDetails(studentsDetails);
            leaveDetailsRepository.save(leaveDetails);
            if (!updateInformation.getEmailId().equals("")) {
                String[] listOfEmails = updateInformation.getEmailId().split(",");
                for (String listOfEmail : listOfEmails) {
                    MailGenerator.sendMails(senderMail, senderPassword, listOfEmail, name, rollNumber, oldLeaveType, oldStartDate, oldEndDate, oldDuration, true, leaveType, startDate, endDate, duration, false, null);
                }
            }
            responseMessage = "Leave is updated successfully";
            logger.info(responseMessage);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
        logger.info(responseMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @DeleteMapping("/delete-leaves")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteLeave(@RequestBody DeleteInformation deleteInformation) {
        String leaveId = deleteInformation.getLeaveId();
        String rollNumber = deleteInformation.getRollNumber();
        String token = deleteInformation.getToken();
        jwtVerifier.verifier(secret, rollNumber, token);
        String responseMessage = "Leave with " + leaveId + " can not be found";
        int response = leaveDetailsService.deleteLeavesByLeaveId(leaveId);
        if (response != 0) {
            responseMessage = "Leave is deleted successfully";
            logger.info(responseMessage);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
        logger.info(responseMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @PostMapping("/leaves")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LeaveRangeInformation>> getLeavesRange(@RequestBody LeaveCountInformation leaveCountInformation) {
        String rollNumber = leaveCountInformation.getRollNumber();
        String classId = studentsDetailsService.findClassIdByRollNumber(rollNumber);
        String token = leaveCountInformation.getToken();
        LocalDate date = leaveCountInformation.getDate();
        jwtVerifier.verifier(secret, rollNumber, token);
        List<LeaveRangeInformation> leaveRangeInformationList = leaveDetailsService.getLeavesByDate(rollNumber, date, classId);
        logger.info("Leave Details are found for the date " + date);
        return ResponseEntity.status(HttpStatus.OK).body(leaveRangeInformationList);
    }

    @PostMapping("/leave-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> postLeaveDetails(@RequestBody LeaveInformation leaveInformation) throws UnsupportedEncodingException, MessagingException {
        String rollNumber = leaveInformation.getRollNumber();
        String token = leaveInformation.getStudentToken();
        LeaveDetails leaveDetails = new LeaveDetails();
        StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
        String name = studentsDetailsService.findNameByRollNumber(rollNumber);
        jwtVerifier.verifier(secret, rollNumber, token);
        String leaveId, message;
        LocalDate startDate = leaveInformation.getStartDate();
        LocalDate endDate = leaveInformation.getEndDate();
        int duration = Utils.calculateDuration(startDate, endDate);
        if (duration > 0) {
            List<LeaveDetails> leaveDetailsList = leaveDetailsService.findAll();
            if (leaveDetailsList.size() == 0) {
                leaveId = "0";
            } else {
                int max = 0, temp;
                for (int i = 0; i < leaveDetailsList.size(); i++) {
                    temp = Integer.parseInt(leaveDetailsList.get(i).getLeaveId());
                    if (temp > max)
                        max = temp;
                }
                leaveId = String.valueOf(max + 1);
            }
            leaveDetails.setLeaveId(leaveId);
            leaveDetails.setStudentsDetails(studentsDetails);
            leaveDetails.setLeaveStartDate(startDate);
            leaveDetails.setLeaveEndDate(endDate);
            leaveDetails.setLeaveType(leaveInformation.getLeaveType());
            leaveDetails.setLeaveDuration(duration);
            leaveDetailsRepository.save(leaveDetails);
            if (!leaveInformation.getEmailId().equals("")) {
                String[] listOfEmails = leaveInformation.getEmailId().split(",");
                for (String listOfEmail : listOfEmails) {
                    MailGenerator.sendMails(senderMail, senderPassword, listOfEmail, name, rollNumber, leaveInformation.getLeaveType(), startDate, endDate, duration, false, null, null, null, 0, false, null);
                }
            }
            message = "Leave is planned successfully";
            logger.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        message = "Leaves can not be planned on weekends";
        logger.info(message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }

    @PostMapping("/login-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentsDetails> getLoginDetails(@RequestBody LoginInformation loginInformation) {
        List<LoginDetails> loginDetailsList = loginDetailsService.findAll();
        String errorMessage = "Incorrect roll number & password";
        StudentsDetails studentsDetails = new StudentsDetails();
        for (LoginDetails loginDetail : loginDetailsList) {
            if (loginDetail.getStudentsDetails().getRollNumber().equals(loginInformation.getRollno())) {
                if (loginDetail.getPassword().equals(loginInformation.getPassword())) {
                    String rollNumber = loginInformation.getRollno();
                    studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
                    String token = jwtCreator.creator(secret, rollNumber);
                    studentsDetails.setStudentToken(token);
                    logger.info("Login is successful");
                    return ResponseEntity.status(HttpStatus.OK).header("Message", "Login is successful").body(studentsDetails);
                } else
                    errorMessage = "Incorrect password";
            }
        }
        logger.info(errorMessage);
        studentsDetails.setStudentToken(errorMessage);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Message", errorMessage).body(studentsDetails);
    }

    @PostMapping("/register-users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getRegistrationDetails(@RequestBody RegisterInformation registerInformation) {
        String rollNumber = registerInformation.getRollNumber();
        String name = registerInformation.getName();
        String emailId = registerInformation.getEmail();
        String classId = registerInformation.getClassId();
        int response1 = studentsDetailsService.insertDetailsByRollNumber(rollNumber, name, emailId, classId);
        String password = registerInformation.getPassword();
        int loginDetailsId;
        List<LoginDetails> loginDetailsList = loginDetailsService.findAll();
        if (loginDetailsList.size() == 0) {
            loginDetailsId = 0;
        } else {
            int max = 0, temp;
            for (int i = 0; i < loginDetailsList.size(); i++) {
                temp = loginDetailsList.get(i).getLoginDetailsKey();
                if (temp > max)
                    max = temp;
            }
            loginDetailsId = max + 1;
        }
        int response2 = loginDetailsService.insertDetailsById(loginDetailsId, rollNumber, password);
        if (response1 != 0 && response2 != 0) {
            logger.info("Successfully registered");
            return ResponseEntity.status(HttpStatus.OK).body("Successfully registered");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration failed");
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> postNewPassword(@RequestBody PasswordInformation passwordInformation) {
        String rollNumber = passwordInformation.getRollNumber();
        String token = passwordInformation.getToken();
        jwtVerifier.verifier(secret, rollNumber, token);
        String message = "Current password is wrong";
        String oldPassword = passwordInformation.getOldPassword();
        String actualPassword = loginDetailsService.getPasswordByRollNumber(rollNumber);
        if (oldPassword.equals(actualPassword)) {
            LoginDetails loginDetails = new LoginDetails();
            int loginDetailsId = loginDetailsService.getLoginIdByRollNumber(rollNumber);
            StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
            String newPassword = passwordInformation.getNewPassword();
            loginDetails.setLoginDetailsKey(loginDetailsId);
            loginDetails.setStudentsDetails(studentsDetails);
            loginDetails.setPassword(newPassword);
            loginDetailsRepository.save(loginDetails);
            message = "Password is changed successfully";
            logger.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        logger.info(message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @PostMapping("/forgot-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> postForgotToken(@RequestBody String rollNumber) throws MessagingException {
        StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
        if(studentsDetails != null){
            String token = UUID.randomUUID().toString();
            PasswordResetTokenInformation passwordResetTokenInformation = new PasswordResetTokenInformation();
            passwordResetTokenInformation.setStudentsDetails(studentsDetails);
            passwordResetTokenInformation.setToken(token);
            passwordResetTokenInformation.setExpiryTime(LocalDateTime.now().plusHours(1));
            passwordResetTokenRepository.save(passwordResetTokenInformation);
            MailGenerator.sendMails(senderMail, senderPassword, studentsDetails.getEmailId(), studentsDetails.getName(), studentsDetails.getRollNumber(), null,  null, null, 0, false, null, null, null, 0, true, token);
            logger.info("Successfully token sent to mail");
            return ResponseEntity.status(HttpStatus.OK).body("Successfully token sent to mail");
        }
        logger.info("Incorrect password");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect roll number");
    }

    @GetMapping("/validate-token")
    ResponseEntity<String> validateToken(@RequestBody String token){
        PasswordResetTokenInformation passwordResetTokenInformation = passwordResetTokenRepository.getPasswordResetInformationByToken(token);
        if (passwordResetTokenInformation != null){
            if(passwordResetTokenInformation.getExpiryTime().isAfter(LocalDateTime.now())){
                logger.info("Token is validated successfully");
                return ResponseEntity.status(HttpStatus.OK).body("Token is validated successfully");
            }
            logger.info("Token expired");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Token expired");
        }
        logger.info("Invalid Token");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
    }

    @PostMapping("/save-password")
    ResponseEntity<String> postNewPassword(@RequestBody LoginInformation loginInformation){
        String rollNumber = loginInformation.getRollno();
        LoginDetails oldLoginDetails = loginDetailsService.getLoginDetailsByRollNumber(rollNumber);
        StudentsDetails studentsDetails = studentsDetailsService.findByRollNumber(rollNumber);
        LoginDetails newLoginDetails = new LoginDetails();
        newLoginDetails.setLoginDetailsKey(oldLoginDetails.getLoginDetailsKey());
        newLoginDetails.setStudentsDetails(studentsDetails);
        newLoginDetails.setPassword(loginInformation.getPassword());
        loginDetailsRepository.save(newLoginDetails);
        logger.info("Password is reset successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Password is reset successfully");
    }

}
