package com.example.brandproductcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.brandproductcrud.dto.ProductResponseDTO;
import com.example.brandproductcrud.entity.Brand;
import com.example.brandproductcrud.entity.Product;
import com.example.brandproductcrud.exception.ProductNotFoundException;
import com.example.brandproductcrud.repository.BrandRepository;
import com.example.brandproductcrud.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	public Product saveProduct(Product product) {
		
		Brand existingBrand = brandRepository.findById(product.getBrand().getId()).orElse(null);
		product.setBrand(existingBrand);
		
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
		
	}
	public Product getProductById(Integer id) { 
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found by id : "+ id));
	}
	
	
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> getProductByName(String productName){
		return productRepository.findByProductName(productName);
	}
	
	public List<Product> getProductByBrand(Integer brandId){
		return productRepository.findByBrandId(brandId);
	}
	
	public long getTotalProducts() {
		return productRepository.count();
	}
	
	public boolean existById(Integer id) {
		return productRepository.existsById(id);
	}
	
	
	public Product updateProduct(Integer id, Product product) {
		
		Product existingProduct = productRepository.findById(id).orElse(null);
		
		if(existingProduct != null) {
			existingProduct .setProductName(product.getProductName());
			
			Brand existingBrand = brandRepository.findById(product.getBrand().getId()).orElse(null);
			
			existingProduct.setBrand(existingBrand);
			return productRepository.save(existingProduct);
		}
		return null;
	}
	
	public List<Product> searchProducts(String keyword){
		return productRepository.findByProductNameContaining(keyword);
	}
	
	 public List<Product> getProductsByBrandName(String brandName){
		 return productRepository.findByBrandBrandName(brandName);
	 }
	 
	 public long countProductByBrand(Integer brandId) {
		 return productRepository.countByBrandId(brandId);
	 }
	 
	 public List<Product> getProductByStartingNameWith(String prefix){
		 return productRepository.findByProductNameStartingWith(prefix);
	 }
	 
	 public ProductResponseDTO getProductDTOById(Integer id) {
		 
		 Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		 
		 ProductResponseDTO dto = new ProductResponseDTO();
		 dto.setId(product.getId());
		 
		 dto.setProductName(product.getProductName());
		 
		 dto.setBrandName(product.getBrand().getBrandName());
		 
		 return dto;
		 
	 }
	 
	 public Page<Product> getProductsByPage(int page, int size){
		 Pageable pageable = PageRequest.of(page, size);
		 return  productRepository.findAll(pageable);
	 }
	 
	 public List<Product> getProductsSortedByName(){
		 return productRepository.findAll(Sort.by("productName"));
	 }
	 
	public List<Product> getAllPRoductsJPQL(){
		return productRepository.getAllProducts();
	}
	 
	public List<Product> getProductByNameJPQL(String productName){
		return productRepository.getProductByName(productName);
	}
	
	public List<Product> getProductsByBrandNameJPQL(String brandName){
		return productRepository.getProductsByBrand(brandName);
	}
	
	

}
