package com.example.brandproductcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.brandproductcrud.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
