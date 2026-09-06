package com.example.brandproductcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.brandproductcrud.dto.ProductResponseDTO;
import com.example.brandproductcrud.entity.Product;
import com.example.brandproductcrud.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearerAuth")
public class productController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public Product addProduct(@Valid @RequestBody Product product){
		return productService.saveProduct(product);
	}
	
	
	@GetMapping
	public List<Product> getAllProduct(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productService.getProductById(id);
	}
	
	@PutMapping("/{id}")
	public Product upadteProduct(@PathVariable Integer id,@RequestBody Product product) {
		return productService.updateProduct(id,product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable Integer id) {
		productService.deleteById(id);
	}
	
	@GetMapping("/name/{productName}")
	public List<Product> getProductByName(@PathVariable String productName){
		return productService.getProductByName(productName);
	}
	
	@GetMapping("/brand/{brandId}")
	public List<Product> getProductByBrand(@PathVariable Integer brandId){
		return productService.getProductByBrand(brandId);			
	}
	
	@GetMapping("/count")
	public long getTotalProducts() {
		return productService.getTotalProducts(); 
	}
	
	@GetMapping("/exists/{id}")
	public boolean isProductexists(@PathVariable Integer id) {
		return productService.existById(id);
	}
	
	@GetMapping("/search/{keyword}")
	public List<Product> searchProducts(@PathVariable String keyword){
		return productService.searchProducts(keyword);
	}
	
	@GetMapping("/brand-name/{brandName}")
	public List<Product> getProductsByBrandName(@PathVariable String brandName){
		return productService.getProductsByBrandName(brandName);
	}
	
	@GetMapping("/count/brand/{brandId}")
	public long getTotalBrand(@PathVariable Integer brandId) {
		return productService.countProductByBrand(brandId);
	}
	
	@GetMapping("/startwith/{prefix}")
	public List<Product> getProducStartingNamewith(@PathVariable String prefix){
		return productService.getProductByStartingNameWith(prefix);
	}

	@GetMapping("/dto/{id}")
	public ProductResponseDTO getProductDTOById(@PathVariable Integer id) {
		return productService.getProductDTOById(id);
	}
	
	@GetMapping("/page")
	public Page<Product> getProductByPage(@RequestParam int page, @RequestParam int size){
		return productService.getProductsByPage(page,size);
	}
	@GetMapping("/sort/name")
	public List<Product> getProductsSortedByName(){
		return productService.getProductsSortedByName();
	}
	
	@GetMapping("/jpql")
	public List<Product> getAllProductsJPQL(){
		return productService.getAllPRoductsJPQL();
	}
	
	@GetMapping("/jpql/name/{name}")
	public List<Product> getProductByNameJPQL(@PathVariable String name){
		return productService.getProductByNameJPQL(name);
	}
	
	@GetMapping("/jpql/brandName/{brand}")
	public List<Product> getProductsByBrand(@PathVariable String brand){
		return productService.getProductsByBrandNameJPQL(brand);
	}
	
	
	
	
	
}
