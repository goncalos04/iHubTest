package com.ihubtest.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ihubtest.test.data.entity.Product;
import com.ihubtest.test.service.ProductService;

@Controller
public class ProductController {

	private final String URL_GET_ROOT 			= "/";
	private final String URL_GET_ALL_PRODUCTS 	= "/allproducts";
	private final String URL_GET_CREATE_PRODUCT = "/createproduct";
	private final String URL_GET_UPDATE_PRODUCT = "/updateproduct";
	private final String URL_GET_DELETE_PRODUCT = "/deleteproduct";
	private final String URL_POST_SAVE_PRODUCT 	= "/saveproduct";
	
	private final String URL_FOLDER 			= "/product";
	private final String URL_PRODUCTS_PAGE 		= "/ProductManagement";
	private final String URL_PRODUCT_FORM_PAGE 	= "/ManageProduct";
	private final String URL_HOME_PAGE 			= "/Home";
	
	private final String MODEL_LIST_PRODUCTS 	= "listProducts";
	private final String MODEL_PRODUCT 			= "product";
	private final String MODEL_HAS_ERROR 		= "hasErrorMessage";
	private final String MODEL_HAS_SUCCESS		= "hasSuccessMessage";
	private final String MODEL_MESSAGE			= "message";
	
	private final String MSG_SAVE_SUCESS	= "Product successfully saved.";
	private final String MSG_SAVE_ERROR		= "Error saving the product.";
	
	@Autowired
	private ProductService productService;
	
    @GetMapping(URL_GET_ROOT)
    public String productsPage(Model model) {
        return URL_HOME_PAGE;
    }
    
    @GetMapping(URL_GET_ALL_PRODUCTS)
    public String allProducts(Model model) {
        model.addAttribute(MODEL_LIST_PRODUCTS, productService.getProducts());
        return URL_FOLDER + URL_PRODUCTS_PAGE;
    }
    
    @GetMapping(URL_GET_UPDATE_PRODUCT)
    public String editProduct(Model model, @RequestParam("sku") int sku) {
    	
    	Product product = productService.getProductById(sku);
    	
        model.addAttribute(MODEL_PRODUCT, product);
        return URL_FOLDER + URL_PRODUCT_FORM_PAGE;
    }
    
    @GetMapping(URL_GET_CREATE_PRODUCT)
    public String createProduct(Model model) {
    	model.addAttribute(MODEL_PRODUCT, new Product());
        return URL_FOLDER + URL_PRODUCT_FORM_PAGE;
    }
    
    @GetMapping(URL_GET_DELETE_PRODUCT)
    public String deleteProduct(Model model, @RequestParam("sku") int sku) {
    	
    	productService.deleteProduct(sku);
		
        return "redirect:" + URL_GET_ALL_PRODUCTS;
    }
    
    @PostMapping(URL_POST_SAVE_PRODUCT)
    public String updateProduct(Model model, @ModelAttribute Product product) {
    	
    	if("".equals(product.getName())) {
    		model.addAttribute(MODEL_PRODUCT, product);
            return URL_FOLDER + URL_PRODUCT_FORM_PAGE;
    	}
    	
    	if(productService.saveProduct(product)) {
    		model.addAttribute(MODEL_HAS_SUCCESS, true);
    		model.addAttribute("message", MSG_SAVE_SUCESS);
    	} else {
    		model.addAttribute(MODEL_HAS_ERROR, true);
    		model.addAttribute(MODEL_MESSAGE, MSG_SAVE_ERROR);
    	}
    	
    	return URL_HOME_PAGE;
    }
    
}
