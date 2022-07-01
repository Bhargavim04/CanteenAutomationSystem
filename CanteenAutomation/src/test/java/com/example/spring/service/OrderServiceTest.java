package com.example.spring.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.spring.entity.Order;
import com.example.spring.repository.OrderRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	private OrderService service;
	@MockBean
	private OrderRepository repository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void listAllOrdersTest() {
		when(repository.listAllOrders())
		.thenReturn(Stream.of(new Order((long)345,"First Order","Food Items","California"),
				new Order((long)346,"Second Order","Regular Items","California"),
				new Order((long)347,"Third Order","Clothes","California")).collect(Collectors.toList()));
		assertEquals(3,service.listAllOrders().size());
	}
	@Test
	public void getOrderByIdTest() {
		Order order =new Order((long)345,"First Order","Food Items","California");
		when(repository.selectOrderById((long)345)).thenReturn(order);
		assertEquals((Order)order,(Order)service.selectOrderById((long)345));		
	}
	
	@Test
	public void deleteOrderByIdTest() {
		service.deleteOrderById((long)345);
		verify(repository,times(1)).deleteOrderById((long)345);
	}
}
