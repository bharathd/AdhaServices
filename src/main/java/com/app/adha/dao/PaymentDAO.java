package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Payment;

public interface PaymentDAO {
	
	List<Payment> getAllPayments();
	Payment getPaymentById(int paymentId);
    void addPayment(Payment payment);

}
