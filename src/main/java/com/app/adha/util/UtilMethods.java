package com.app.adha.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

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
	public static int ACCOUNT_DEACTIVE = 0;
	public static int ACCOUNT_ACTIVE = 1;
	public static int ACCOUNT_INPROGRESS = 2;
	
	//tearms and conditions constants
	
	public static int YES = 1;
	public static int NO = 0;
	
	//Active or Delete records in the Database
	
	public static int ACTIVE = 1;
	public static int DELETE = 0;
	
	//aws keys
	public static String  myAccessKey = "2OXRQVNZN46NEA2NDKMA";
	public static String  mySecretKey = "jo0hEQbM4VpP8izGxizGxI3mUYVXJQIxH56RrJCQcKrWTz8";
	public static String bucketName = "adahappsql";
	public static String region = "sfo2";
	public static String endpoint = "https://sfo2.digitaloceanspaces.com";
	
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
	  String otp_url = "http://onlinebulksmslogin.com/spanelv2/api.php?username=codelitelabs&password=codelite@labs&to="+phoneNumber+"&from=PACBAG&message="+otp_number;
	  
	try {
		URL url = new URL(otp_url);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		if(!connection.getResponseMessage().equals("OK"))
			return "SMS Not Sent";
	   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
	  return "SMS Sent Success!";
	}
	
	
	
	
	
	public static void displayText(InputStream input) throws IOException{
		// Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            System.out.println("    " + line);
        }
	}
	
	
}
