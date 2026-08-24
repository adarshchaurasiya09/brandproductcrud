package com.example.brandproductcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.brandproductcrud.entity.Brand;
import com.example.brandproductcrud.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public Brand addBrand(@RequestBody Brand brand) {
		return brandService.saveBrand(brand);
	}
	
	@GetMapping
	public List<Brand> getAllBrands(){
		return brandService.getAllBrands();
	}
	
	@GetMapping("/{id}")
	public Brand getBrandById(@PathVariable Integer id) {
		return brandService.getBrandById(id);
	}
	
	@PutMapping("/{id}")
	public Brand updateBrand(@PathVariable Integer id, @RequestBody Brand brand) {
		return brandService.updateBrand(id,brand);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBrand(@PathVariable Integer id) {
		brandService.deleteBrandById(id);
	}
	
	@GetMapping("/exists/{id}")
	public boolean isBrandExists(@PathVariable Integer id) {
		return brandService.existsById(id);
	}
	
	
	
}
