package com.example.brandproductcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.brandproductcrud.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
 
	List<Product> findByProductName(String productName);
	 
	List<Product> findByBrandId(Integer id);
	
	List<Product> findByProductNameContaining(String keyword);
	
	List<Product> findByBrandBrandName(String brandName);
	
	long countByBrandId(Integer brandId);
	
	List<Product> findByProductNameStartingWith(String prefix); 
	
	@Query("SELECT p FROM Product p")
	List<Product> getAllProducts();
	
	@Query("SELECT p FROM Product p WHERE p.productName=?1")
	List<Product> getProductByName(String productName);
	
	@Query("SELECT p FROM Product p WHERE p.brand.brandName = :brandName")
	List<Product> getProductsByBrand(@Param("brandName")String brandName);
 }
