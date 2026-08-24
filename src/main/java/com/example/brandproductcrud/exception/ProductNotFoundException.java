package com.example.brandproductcrud.exception;

public class ProductNotFoundException extends  RuntimeException{
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
