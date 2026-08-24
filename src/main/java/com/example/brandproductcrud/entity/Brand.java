package com.example.brandproductcrud.entity;

import java.util.List;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

 

import jakarta.persistence.OneToMany;

@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String brandName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private List<Product> products;
	
	public Brand() {
		
	}
	
	public Brand(Integer id, String brandName, List<Product> products) {
		this.id = id;
		this.brandName = brandName;
		this.products  = products;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
