package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PaymentDAO;
import com.app.adha.entity.Payment;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentDAO paymentDAO;
	
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
		paymentDAO.addPayment(payment);
	}


}
