package com.example.brandproductcrud.dto;

public class ProductResponseDTO {

	private Integer id;
	
	private String productName;
	
	private String brandName;
	
	public ProductResponseDTO() {
		
	}
	
	public ProductResponseDTO(Integer id, String productName, String brandName) {
		this.id = id;
		this.productName= productName;
		this.brandName = brandName;
	}
	
	public Integer getId() {
		return  id;
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
	 
	 public String getBrandName() {
		 return brandName;
	 }
	 public void setBrandName(String brandName) {
		 this.brandName = brandName;
	 }
}
