package com.ihubtest.test.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "Product")
public class Product {

	@Id
	private int id;
	
	private String name;
	private BigDecimal price;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	public Product() {
		
	}
	
	public Product(int id, String name, BigDecimal price, Date date) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	  public String toString() {
	    return String.format(
	        "Product[id=%d, name='%s', price='%d', date='%s']",
	        id, name, price, date);
	  }
}
