package com.leave.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Properties;


public class MailGenerator {

    public static void sendMails(String senderMail, String senderPassword, String recipientMail, String name, String rollNumber, String leaveType, LocalDate startDate, LocalDate endDate, int duration, boolean isUpdated, String updatedLeaveType, LocalDate updatedStartDate, LocalDate updatedEndDate, int updatedDuration) throws MessagingException {

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.port","587");
        Session session=Session.getInstance(prop,new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(senderMail,senderPassword);
            }
        });
        session.setDebug(true);
        Message message;
        if(isUpdated){
            message = createUpdatedMessage(session, senderMail, recipientMail, name, rollNumber, leaveType, startDate, endDate, duration, updatedLeaveType, updatedStartDate, updatedEndDate, updatedDuration);
        }
        else {
            message = createMessage(session, senderMail, recipientMail, name, rollNumber, leaveType, startDate, endDate, duration);
        }
        Transport.send(message);
    }

    private static String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return date.format(formatter);
    }

    private static String getDayOfTheWeek(LocalDate date){
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    private static Message createMessage(Session session, String senderMail, String recipientMail, String name, String rollNumber, String leaveType, LocalDate startDate, LocalDate endDate, int duration) throws MessagingException{
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderMail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));
        String messageSubject = "[Leave Notification] "+name+" has planned "+leaveType;
        String newLine =System.getProperty("line.separator");
        String messageContent = "Hello!"+newLine+"Leave plan for "+name+" ("+rollNumber
                +") is as follows:"+newLine+newLine+"Leave Type: "+leaveType+newLine+"Start Date: "+getDayOfTheWeek(startDate)
                +", "+formatDate(startDate)+newLine+"End Date: "+getDayOfTheWeek(endDate)+", "
                +formatDate(endDate)+newLine+"Duration: "+duration+" day[s]";
        message.setSubject(messageSubject);
        message.setContent(messageContent,"text/plain");
        message.saveChanges();
        return message;
    }

    private static Message createUpdatedMessage(Session session, String senderMail, String recipientMail, String name, String rollNumber, String leaveType, LocalDate startDate, LocalDate endDate, int duration, String updatedLeaveType, LocalDate updatedStartDate, LocalDate updatedEndDate, int updatedDuration) throws MessagingException{
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderMail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));
        String messageSubject = "[Leave Notification] "+name+" has updated "+leaveType;
        String newLine=System.getProperty("line.separator");
        String messageContent = "Hello!"+newLine+"The previous leave plan for "+name+" ("+rollNumber
                +") is as follows:"+newLine+"Leave Type: "+leaveType+newLine+"Start Date: "+getDayOfTheWeek(startDate)
                +", "+formatDate(startDate)+newLine+"End Date: "+getDayOfTheWeek(endDate)+", "
                +formatDate(endDate)+newLine+"Duration: "+duration+" day[s]"+newLine+newLine+newLine
                +"The new updated leave plan for "+name+" ("+rollNumber
                +") is as follows:"+newLine+"Leave Type: "+updatedLeaveType+newLine+"Start Date: "+getDayOfTheWeek(updatedStartDate)
                +", "+formatDate(updatedStartDate)+newLine+"End Date: "+getDayOfTheWeek(updatedEndDate)+", "
                +formatDate(updatedEndDate)+newLine+"Duration: "+updatedDuration+" day[s]";
        message.setSubject(messageSubject);
        message.setContent(messageContent,"text/plain");
        message.saveChanges();
        return message;
    }
}
