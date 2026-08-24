package com.example.brandproductcrud.entity;

import jakarta.persistence.*; 
import jakarta.validation.constraints.NotBlank; 

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="Product Name is required")
	private String productName;
	
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	public Product() {

	}
	
	
	public Product(Integer id, String productName, Brand brand) {
		this.id = id;
		this.productName = productName;
		this.brand = brand;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
