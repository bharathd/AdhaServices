package com.app.adha.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URL;
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
	
	/**
     * The Constant INSTAMOJO_API_ENDPOINT. Every API request is made to a
     * method on top of this base URL. HTTPS is mandatory.
     */
    public static final String INSTAMOJO_LIVE_API_ENDPOINT = "https://www.instamojo.com/v2/";

    public static final String INSTAMOJO_TEST_API_ENDPOINT = "https://test.instamojo.com/v2/";

    /**
     * The constant INSTAMOJO_AUTH_ENDPOINT.
     */
    public static final String INSTAMOJO_LIVE_AUTH_ENDPOINT = "https://www.instamojo.com/oauth2/token/";

    public static final String INSTAMOJO_TEST_AUTH_ENDPOINT = "https://test.instamojo.com/oauth2/token/";

    /**
     * The constant PARAM_CLIENT_ID.
     */
    public static final String PARAM_CLIENT_ID = "client_id";

    /**
     * The constant PARAM_CLIENT_SECRET.
     */
    public static final String PARAM_CLIENT_SECRET = "client_secret";

    /**
     * The constant PARAM_GRANT_TYPE.
     */
    public static final String PARAM_GRANT_TYPE = "grant_type";

    public static final String PARAM_USERNAME = "username";

    /**
     * The constant PARAM_GRANT_TYPE.
     */
    public static final String PARAM_REFRESH_TOKEN = "refresh_token";

    /**
     * The constant PARAM_PASSWORD.
     */
    public static final String PARAM_PASSWORD = "password";

    /**
     * The constant GRAND_TYPE_CLIENT_CREDENTIALS.
     */
    public static final String GRAND_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    /**
     * The constant PARAM_REFRESH_TOKEN.
     */
    public static final String GRAND_TYPE_REFRESH_TOKEN = "refresh_token";

    /**
     * The constant HEADER_AUTHORIZATION.
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String HEADER_ACCEPT = "Accept";

    /**
     * The constant PATH_PAYMENT_ORDER.
     */
    public static final String PATH_PAYMENT_ORDER = "gateway/orders/";

    /**
     * The constant PATH_REFUND.
     */
    public static final String PATH_REFUND = "payments/";

    public static final String PATH_INVOICE = "invoices/";

    public static final String PATH_PAYOUT = "payouts/";

    public static final String PATH_PAYMENT_REQUEST = "payment_requests/";
}
