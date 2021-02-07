package com.ihubtest.test.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ihubtest.test.data.entity.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends MongoRepository<Product, String>{

	public Product findById(int id);
	
	public List<Product> findByName(String name);
	
	public List<Product> findAll();
	
	public Product findTopByOrderByIdDesc();
	
}
