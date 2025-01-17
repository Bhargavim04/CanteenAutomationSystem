package com.example.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dto.AddressDto;
import com.example.spring.dto.CustomerDetailsDto;
import com.example.spring.dto.CustomerDto;
import com.example.spring.dto.RegRespDto;
import com.example.spring.dto.RegisterDto;
import com.example.spring.entity.Address;
import com.example.spring.entity.Customer;
import com.example.spring.entity.Login;
import com.example.spring.exception.CustomerNotFoundException;
import com.example.spring.exception.CustomerFoundException;
import com.example.spring.repository.ICustomerRepository;
import com.example.spring.repository.ILoginRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository cusRepo;

	@Autowired
	ILoginRepository loginRepo;

	// get all customer details
	@Override
	public List<Customer> getAllCustomers() {

		return cusRepo.findAll();
	}

	// get customer based on customer ID
	@Override
	public Customer getCusById(int cusId) throws CustomerNotFoundException {
		Optional<Customer> cus = cusRepo.findById(cusId);
		if (cus.isPresent()) {
			return cus.get();
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}
	}

	// add new customer
	@Override
	public Customer addCustomer(Customer cus) {

		return cusRepo.save(cus);
	}

	@Override
	public Customer updateCustomerById(int cusId, Customer cus) throws CustomerNotFoundException {
		// find customer based on id
		Optional<Customer> dbCus = cusRepo.findById(cusId);

		// if customer present, update customer with new details else return exception
		if (dbCus.isPresent()) {
			return cusRepo.save(cus);
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}

	}

	// Update customer address by Id
	@Override
	public Customer updateCusAddr(int cusId, Address newAddr) throws CustomerNotFoundException {
		// find customer by Id
		Optional<Customer> cus = cusRepo.findById(cusId);
		if (cus.isPresent()) {
			// update address
			// get customer from optional container
			Customer dbCus = cus.get();

			// add new address to the existing customer
			dbCus.getAddress().add(newAddr);

			// update customer details in db
			return cusRepo.save(dbCus);
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}

	}

	// delete customer based on Id
	@Override
	public Customer deleteCustomer(int cusId) throws CustomerNotFoundException {
		// find customer based on customer id
		Optional<Customer> cus = cusRepo.findById(cusId);

		// delete customer if present else return null
		if (cus.isPresent()) {
			cusRepo.deleteById(cusId);
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}
		// return customer
		return cus.get();
	}

	@Override
	public CustomerDto getCusDtoById(int cusId) throws CustomerNotFoundException {

		Optional<Customer> cus1 = cusRepo.findById(cusId);
		if (cus1.isPresent()) {
			Customer cus = cus1.get();
			CustomerDto cusDto = new CustomerDto();
			cusDto.setCusId(cus.getCusId());
			cusDto.setCusName(cus.getCusName());
			cusDto.setCusContactNo(cus.getCusContactNo());
			cusDto.setEmail(cus.getLogin().getEmail());

			return cusDto;
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}
	}

	@Override
	public CustomerDto updateCusDtoById(int cusId, CustomerDto cusDto) throws CustomerNotFoundException {

		// find customer based on id
		Optional<Customer> cusOpt = cusRepo.findById(cusId);

		// if customer present, update customer with new details else return exception
		if (cusOpt.isPresent()) {
			// convert CustomerDto to Customer obj
			Customer dbCus = cusOpt.get();
			dbCus.setCusName(cusDto.getCusName());
			dbCus.setCusContactNo(cusDto.getCusContactNo());
			Login login = dbCus.getLogin();
			login.setEmail(cusDto.getEmail());
			dbCus.setLogin(login);
			cusRepo.save(dbCus);
			return cusDto;
		} else {
			throw new CustomerNotFoundException("Customer not found with this id " + cusId);
		}

	}

	// Registration
	@Override
	public RegRespDto regCustomer(RegisterDto regDto) throws CustomerFoundException {

		Optional<Login> loginOpt = loginRepo.findById(regDto.getEmail());
		if (loginOpt.isPresent()) {
			throw new CustomerFoundException(
					"Given email address " + regDto.getEmail() + " present already! Choose different one");
		}

		// Convert RegisterDto to customer obj
		// Create cus obj
		Customer cus = new Customer();

		// Update cus obj details
		cus.setCusName(regDto.getCusName());
		cus.setCusContactNo(regDto.getCusContactNo());

		Login login = new Login();
		login.setEmail(regDto.getEmail());
		login.setPassword(regDto.getPassword());
		login.setRole(regDto.getRole());
		login.setLoggedIn(false);

		cus.setLogin(login);

		// Save cus obj in db
		Customer newCus = cusRepo.save(cus);

		// convert customer obj to RegRespDto obj

		RegRespDto resDto = new RegRespDto();
		resDto.setCusName(newCus.getCusName());
		resDto.setCusContactNo(newCus.getCusContactNo());
		resDto.setEmail(newCus.getLogin().getEmail());
		resDto.setRole(newCus.getLogin().getRole());
		resDto.setLoggedIn(newCus.getLogin().isLoggedIn());

		return resDto;
	}

	// Get request- get customer by email which is foreign key in login
	@Override
	public Customer getCusByEmail(String email) {
		return cusRepo.getCusByEmail(email);
	}

	@Override
	public CustomerDto getCusDetailsByEmail(String email) {
		Customer newCus = cusRepo.getCusByEmail(email);
		CustomerDto cusDto = new CustomerDto();
		cusDto.setCusId(newCus.getCusId());
		cusDto.setCusName(newCus.getCusName());
		cusDto.setCusContactNo(newCus.getCusContactNo());
		cusDto.setEmail(newCus.getLogin().getEmail());
		return cusDto;

	}

	@Override
	public AddressDto getCusAddrByEmail(String email) {
		Customer newCus = cusRepo.getCusByEmail(email);
		List<Address> addr = new ArrayList<Address>();
		addr.addAll(newCus.getAddress());
		AddressDto addrDto = new AddressDto();
		addrDto.setAddress(addr);
		return addrDto;
	}
}
