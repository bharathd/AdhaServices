package com.app.adha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Payment;
import com.app.adha.service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class); 
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") int id) {
		logger.info("Fetching Payment with id " + id);
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @GetMapping("payments")
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> list = paymentService.getAllPayments();
		logger.info("Fetching All Payments " + list);
		return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addpayment", headers="Accept=application/json")
	public String addPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {
    	     String paymentURL =  paymentService.addPayment(payment);
    	       logger.info("Adding payment to database " + payment);
               
               return paymentURL;
    }

}
