package com.example.spring.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.dto.PaymentDto;
import com.example.spring.entity.Payment;
import com.example.spring.exception.PaymentNotFoundException;
import com.example.spring.service.IPaymentService;

@RestController
public class PaymentController {
	// constructor injection/setter injection/field injection
	@Autowired
	IPaymentService payServ;

	// get all payments
	@GetMapping("/payments")
	ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = payServ.getAllPayments();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	// get all payment based on id
	@GetMapping("/payment/{id}")
	ResponseEntity<Payment> getPaymentById(@PathVariable("id") int Paymentid) throws PaymentNotFoundException {
		Payment payment = payServ.getPaymentById(Paymentid);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	// update payment
	@PutMapping("/payment/{id}")
	ResponseEntity<Payment> updatePaymentById(@RequestBody Payment payment, @PathVariable("id") int paymentId)
			throws PaymentNotFoundException {

		Payment updatePay = payServ.updatePaymentById(paymentId, payment);
		return new ResponseEntity<>(updatePay, HttpStatus.OK);
	}

	// get total payment
	@GetMapping("/payment/total/{TotalPayment}")
	ResponseEntity<List<Payment>> getPaymentByTotalPayment(@PathVariable("TotalPayment") double payment) {

		List<Payment> payment1 = payServ.getPaymentByTotalPayment(payment);
		return new ResponseEntity<>(payment1, HttpStatus.OK);

	}

	// get payment Type
	@GetMapping("/payment/type/{PaymentType}")
	ResponseEntity<List<Payment>> getPaymentByPaymentType(@PathVariable("PaymentType") String CreditCard) {

		List<Payment> payment = payServ.getPaymentByPaymentType(CreditCard);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	// add payment
	@PostMapping("/payment")
	ResponseEntity<Payment> addPayment(@Valid @RequestBody Payment payment) {
		Payment newPay = payServ.addPayment(payment);
		return new ResponseEntity<>(newPay, HttpStatus.CREATED);
	}

	// delete payment
	@DeleteMapping("/payment/{id}")
	ResponseEntity<Payment> deletePayment(@PathVariable("id") int paymentId) {
		Payment payment = payServ.deletePayment(paymentId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	@GetMapping("/payment/dto/{id}")
	ResponseEntity<PaymentDto> getPaymentDtoById(@PathVariable("id") int paymentId) throws PaymentNotFoundException {
		PaymentDto payment = payServ.getpaymentDtoById(paymentId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

}
