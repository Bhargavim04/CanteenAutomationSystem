package com.example.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.dto.AddressDto;
import com.example.spring.dto.CustomerDetailsDto;
import com.example.spring.dto.CustomerDto;
import com.example.spring.dto.RegRespDto;
import com.example.spring.dto.RegisterDto;
import com.example.spring.entity.Address;
import com.example.spring.entity.Customer;
import com.example.spring.exception.CustomerFoundException;
import com.example.spring.exception.CustomerNotFoundException;
import com.example.spring.service.ICustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	ICustomerService cusServ;

	// get all customer details
	@GetMapping("/customers")
	ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = cusServ.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// get customer based on customer id
	@GetMapping("/customer/{id}")
	ResponseEntity<Customer> getCusById(@PathVariable("id") int cusId) throws CustomerNotFoundException {
		Customer cus = cusServ.getCusById(cusId);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}

	// get customer Dto based on customer id
	@GetMapping("/customer/dto/{id}")
	ResponseEntity<CustomerDto> getCusDtoById(@PathVariable("id") int cusId) throws CustomerNotFoundException {
		CustomerDto cus = cusServ.getCusDtoById(cusId);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}

	// get customer based on email
	@GetMapping("/customer/email/{email}")
	ResponseEntity<Customer> getCusByEmail(@PathVariable("email") String email) throws CustomerNotFoundException {
		Customer cus = cusServ.getCusByEmail(email);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}

	// get customer based on email
	@GetMapping("/customer/addr/cusId/{email}")
	ResponseEntity<AddressDto> getCusAddrByEmail(@PathVariable("email") String email)
			throws CustomerNotFoundException {
		AddressDto addr = cusServ.getCusAddrByEmail(email);
		return new ResponseEntity<>(addr, HttpStatus.OK);
	}

	// get customer based on email
	@GetMapping("/customer/detail/email/{email}")
	ResponseEntity<CustomerDto> getCusDetailsByEmail(@PathVariable("email") String email)
			throws CustomerNotFoundException {
		CustomerDto cus = cusServ.getCusDetailsByEmail(email);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}

	// add new customer
	@PostMapping("/customer")
	ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer cus) {
		Customer newCus = cusServ.addCustomer(cus);
		return new ResponseEntity<>(newCus, HttpStatus.CREATED);
	}

	// Register customer
	@PostMapping("/customer/register")
	ResponseEntity<RegRespDto> regCustomer(@Valid @RequestBody RegisterDto cus) throws CustomerFoundException {
		System.out.println();
		RegRespDto newCus = cusServ.regCustomer(cus);
		return new ResponseEntity<>(newCus, HttpStatus.CREATED);
	}

	// Update Customer
	@PutMapping("/customer/{id}")
	ResponseEntity<Customer> updateCustomerById(@RequestBody Customer cus, @PathVariable("id") int cusId)
			throws CustomerNotFoundException {
		Customer updatedCus = cusServ.updateCustomerById(cusId, cus);
		return new ResponseEntity<>(updatedCus, HttpStatus.OK);
	}

	// Update Customer
	@PutMapping("/customer/dto/{id}")
	ResponseEntity<CustomerDto> updateCustomerDtoById(@RequestBody CustomerDto cusDto, @PathVariable("id") int cusId)
			throws CustomerNotFoundException {
		CustomerDto updatedCus = cusServ.updateCusDtoById(cusId, cusDto);
		return new ResponseEntity<>(updatedCus, HttpStatus.OK);
	}

	// update employee address details
	@PatchMapping("/customer/addr/{id}")
	ResponseEntity<Customer> updateCusAddr(@PathVariable("id") int cusId, @Valid @RequestBody Address newAddr)
			throws CustomerNotFoundException {
		Customer cus = cusServ.updateCusAddr(cusId, newAddr);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}

	// delete customer
	@DeleteMapping("/customer/{id}")
	ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int cusId) throws CustomerNotFoundException {
		Customer cus = cusServ.deleteCustomer(cusId);
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}
}
