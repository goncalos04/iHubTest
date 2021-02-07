package com.ihubtest.test.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihubtest.test.data.entity.Product;
import com.ihubtest.test.data.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductById(int sku){
		return productRepository.findById(sku);
	}
	
	public Product getLastId(){
		return productRepository.findTopByOrderByIdDesc();
	}
	
	public boolean deleteProduct(int sku) {
		
		Product product = getProductById(sku);
		
		if(product != null) {
			productRepository.delete(product);
		}
		
		return true;
	}
	
	public boolean saveProduct(Product product) {
		
		if(product.getId() == 0) {
			Product lastProduct = getLastId();
			
			if(lastProduct != null) {
				product.setId(lastProduct.getId() + 1);
			} else {
				product.setId(1000);
			}
			
			product.setDate(new Date());
		}
		
		product = productRepository.save(product);
		
		return product != null ? true : false;
	}
}
