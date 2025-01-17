package com.example.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CanteenFood {

	@Id
	@GeneratedValue
	private int foodId;
	private String foodName;
	private String foodImage;
	private double foodPrice;
	private int foodQty;
	private int itemTotalPrice;

}
