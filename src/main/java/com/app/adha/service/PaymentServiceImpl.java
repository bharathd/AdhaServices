package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PaymentDAO;
import com.app.adha.entity.Payment;
import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentDAO paymentDAO;
	
	@Autowired
    private NotificationService notificationService;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public Payment getPaymentById(int paymentId) {
		Payment payment = paymentDAO.getPaymentById(paymentId);
		return payment;
	}	
	
	@Override
	public List<Payment> getAllPayments(){
		return paymentDAO.getAllPayments();
	}
	
	@Override
	public String addPayment(Payment payment){
		String paymentURL="";
		payment.setTransactionId(UUID.randomUUID().toString());
		ApiContext context = ApiContext.create("cA8zEqigsF2TQGfARLekYlZ3t0fFbfIIwAEmE9f2", "e3WC9OJYBUAGl8D9GA8uoxsQUYLVFCgEAIGHv7ywLzbYZBLZfqlTdUTFjSbvGX1AvVGwbqwDdYCuz5tJ8OyANKDXR1UrpWINLsJcMZwrecxSSha2POpWVIEK1mPYBb6j", ApiContext.Mode.LIVE);
		//ApiContext context = ApiContext.create("28d785b55b6eb46145c8fb5bc599de09", "45a992f60333b2938b5decc591098c4c", ApiContext.Mode.TEST);
		Instamojo api = new InstamojoImpl(context);
		
		/*
		 * Create a new payment order
		 */
		PaymentOrder order = new PaymentOrder();
		order.setName(payment.getName());
		order.setEmail("adhaapp9@gmail.com");
		order.setPhone(payment.getPhonenumber());
		order.setCurrency("INR");
		order.setAmount(Double.parseDouble(payment.getAmount()));
		order.setDescription("This is a Adha transaction.");
		order.setRedirectUrl("https://www.instamojo.com/@codelitelabsprivatelimited");
		order.setWebhookUrl("http://www.google.com/");
		order.setTransactionId(payment.getTransactionId());

		try {
		    PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
		    paymentURL = paymentOrderResponse.getPaymentOptions().getPaymentUrl();
		    System.out.println(paymentOrderResponse.getPaymentOrder().getStatus());
		    System.out.println(paymentOrderResponse.getPaymentOptions().getPaymentUrl());

		} catch (HTTPException e) {
		    System.out.println(e.getStatusCode());
		    System.out.println(e.getMessage());
		    System.out.println(e.getJsonPayload());

		} catch (ConnectionException e) {
		    System.out.println(e.getMessage());
		}
		
		  payment.setPaymentDate(df.format(dateobj));
		  paymentDAO.addPayment(payment);
		  String description = "Customer Made a payment";
          notificationService.addNotification(payment.getCustomerId(), payment.getCustomerId(), description);
	return paymentURL;
	}


}
