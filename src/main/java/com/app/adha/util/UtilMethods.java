package com.app.adha.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {
	
	@Autowired
    private  JavaMailSender sender;
	
	// model, customer, admin and super-admin Roles constants
	public static int ROLE_MODEL = 0;
	public static int ROLE_CUSTOMER = 1;
	public static int ROLE_ADMIN = 2;
	public static int ROLE_SUPERADMIN = 3;
	
	//account status constants
	public static int DEACTIVE = 0;
	public static int ACTIVE = 1;
	public static int INPROGRESS = 2;
	
	//tearms and conditions constants
	
	public static int YES = 1;
	public static int NO = 0;
	
	
	public String sendMail(String to_mail, String otp_number) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to_mail);
            helper.setText(otp_number + " is your verification code to login to your Adha Service Account."
            		+ " It is valid for 8 minutes");
            helper.setSubject("Adha Service OTP");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }
	
	public String genrateOTP(String phoneNumber, String otp_number) {
	  String otp_url = "http://onlinebulksmslogin.com/spanelv2/api.php?username=codelite@labs&to="+phoneNumber+"&from=Adah&message="+otp_number;
	  return "SMS Sent Success!";
	}
}
