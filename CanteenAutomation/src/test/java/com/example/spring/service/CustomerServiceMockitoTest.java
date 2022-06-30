package com.example.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.entity.Address;
import com.example.spring.entity.Customer;
import com.example.spring.exception.CustomerNotFoundException;
import com.example.spring.repository.ICustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {

	@InjectMocks
	CustomerServiceImpl cusServ;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	ICustomerRepository cusRepo;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetCustomerById() throws CustomerNotFoundException {

		Customer cus = new Customer();
		cus.setCusId(10);
		cus.setCusName("Roopa");

		Mockito.when(cusRepo.findById(10)).thenReturn(Optional.of(cus));

		Customer cus1 = cusServ.getCusById(10);
		assertEquals(10, cus1.getCusId());
		assertEquals("Roopa", cus1.getCusName());
	}
	
	@Test
	void testGetAllCustomers() {
		List<Customer> cusList = new ArrayList();
		List<Customer> cusList1 = new ArrayList();
		Customer cus = new Customer();
		cus.setCusId(10);
		cus.setCusName("Bhavana");
		cusList.add(cus);
		Mockito.when(cusRepo.findAll()).thenReturn(cusList);
		cusList1= cusServ.getAllCustomers();
		assertEquals(cusList, cusList1);
	}
	
	@Test
	void testAddCustomer() {
		Customer cus = new Customer();
		cus.setCusId(12);
		cus.setCusName("Ram");
		
		Mockito.when(cusRepo.save(cus)).thenReturn(cus);
		Customer emp1= cusServ.addCustomer(cus);
		assertEquals("Ram", emp1.getCusName());
	}
	
	@Test
	void testUpdateCustomerById() throws CustomerNotFoundException {
		Address addr = new Address(10,234,"Yelahanka","Bangalore","Karnataka",560064);
		List<Address> list= new ArrayList();
		Customer cus = new Customer();
		cus.setCusId(10);
		cus.setCusName("Ravi");
		cus.setCusContactNo("9845600809");
		cus.setAddress(list);
		
		
		Customer updatedCus = new Customer();
		updatedCus.setCusId(10);
		updatedCus.setCusName("Sam");
		updatedCus.setCusContactNo("9845600809");
		updatedCus.setAddress(list);
		
		Mockito.when(cusRepo.findById(10)).thenReturn(Optional.of(cus));
		Mockito.when(cusRepo.save(cus)).thenReturn(updatedCus);
		
		Customer cus2  =cusServ.updateCustomerById(10, cus);
		assertEquals("Sam", cus2.getCusName());
	}
	@Test
	void testUpdateCustomerAddr() throws CustomerNotFoundException {
		Address addr = new Address(10,234,"Yelahanka","Bangalore","Karnataka",560064);
		List<Address> listAddr= new ArrayList();
		Customer cus = new Customer();
		cus.setCusId(10);
		cus.setCusName("Ravi");
		cus.setCusContactNo("9845600809");
		cus.setAddress(listAddr);
		
		
		Customer updatedCus = new Customer();
		updatedCus.setCusId(10);
		updatedCus.setCusName("Sam");
		updatedCus.setCusContactNo("9845600809");
		updatedCus.setAddress(listAddr);
		
		Mockito.when(cusRepo.findById(10)).thenReturn(Optional.of(cus));
		Mockito.when(cusRepo.save(cus)).thenReturn(updatedCus);
		
		Customer cus2  =cusServ.updateCusAddr(10, addr);
		assertEquals(listAddr, cus2.getAddress());
	}
	
	
	@Test
	void testDeleteCustomer() throws CustomerNotFoundException {
		Customer cus = new Customer();
		cus.setCusId(20);
		cus.setCusName("Raji");
		
		Mockito.when(cusRepo.findById(20)).thenReturn(Optional.of(cus));
		//Mockito.when(empRepo.deleteById(10)).
		Customer cus1= cusServ.deleteCustomer(20);
		assertEquals(20, cus1.getCusId());
		
	}

}