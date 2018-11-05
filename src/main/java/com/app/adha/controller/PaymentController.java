package com.app.adha.controller;

import java.util.List;

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
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") int id) {
        System.out.println("Fetching Payment with id " + id);
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @GetMapping("payments")
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> list = paymentService.getAllPayments();
		return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addpayment", headers="Accept=application/json")
	public ResponseEntity<Void> addPhoto(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {
    	       paymentService.addPayment(payment);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/payment/{id}").buildAndExpand(payment.getPaymentId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
