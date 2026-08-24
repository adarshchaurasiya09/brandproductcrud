package com.example.brandproductcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.brandproductcrud.entity.Brand;
import com.example.brandproductcrud.repository.BrandRepository;
import com.example.brandproductcrud.exception.BrandNotFoundException;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public Brand saveBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	public List<Brand> getAllBrands(){
		return brandRepository.findAll();
	}
	
	 public Brand getBrandById(Integer id) {
		 return brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Brand not found at id: "+id));
	 }
	
	 public void deleteBrandById(Integer id) {
		 brandRepository.deleteById(id);
	 }
	 
	 
	 public Brand updateBrand(Integer id, Brand brand) {
		 Brand existingBrand = brandRepository.findById(id).orElse(null);
		 
		 if(existingBrand != null) {
			 existingBrand.setBrandName(brand.getBrandName());
			 return brandRepository.save(existingBrand);
		 }
		 return null;
	 }
	 
	 public boolean existsById(Integer id) {
		 return brandRepository.existsById(id);
	 }
	
	 

}
