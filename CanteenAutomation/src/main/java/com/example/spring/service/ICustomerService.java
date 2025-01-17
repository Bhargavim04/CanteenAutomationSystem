package com.example.spring.service;

import java.util.List;

import com.example.spring.dto.AddressDto;
import com.example.spring.dto.CustomerDetailsDto;
import com.example.spring.dto.CustomerDto;
import com.example.spring.dto.RegRespDto;
import com.example.spring.dto.RegisterDto;
import com.example.spring.entity.Address;
import com.example.spring.entity.Customer;
import com.example.spring.exception.CustomerFoundException;
import com.example.spring.exception.CustomerNotFoundException;

public interface ICustomerService {

	List<Customer> getAllCustomers();

	Customer getCusById(int cusId) throws CustomerNotFoundException;

	Customer addCustomer(Customer cus);

	Customer deleteCustomer(int cusId) throws CustomerNotFoundException;

	Customer updateCusAddr(int cusId, Address newAddr) throws CustomerNotFoundException;

	Customer updateCustomerById(int cusId, Customer cus) throws CustomerNotFoundException;

	CustomerDto getCusDtoById(int cusId) throws CustomerNotFoundException;

	CustomerDto updateCusDtoById(int cusId, CustomerDto cusDto) throws CustomerNotFoundException;

	RegRespDto regCustomer(RegisterDto cus) throws CustomerFoundException;

	Customer getCusByEmail(String email);

	CustomerDto getCusDetailsByEmail(String email);

	AddressDto getCusAddrByEmail(String email);

}
