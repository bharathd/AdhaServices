package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Payment;

public interface PaymentService {

	List<Payment> getAllPayments();
	Payment getPaymentById(int paymentId);
    String addPayment(Payment payment);
}
