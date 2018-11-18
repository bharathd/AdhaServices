package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PaymentDAO;
import com.app.adha.entity.Payment;

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
	public void addPayment(Payment payment){
		payment.setPaymentDate(df.format(dateobj));
		paymentDAO.addPayment(payment);
		String description = "Customer Made a payment";
        notificationService.addNotification(payment.getCustomerId(), payment.getCustomerId(), description);
	}


}
