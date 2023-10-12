package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh.shoppingappbackend.exception.ResourceNotFoundException;
import org.trananh.shoppingappbackend.model.Product;
import org.trananh.shoppingappbackend.repository.ProductRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired(required = true)
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAllproduct() {
		List<Product> products = productRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < products.size(); i++) {
			objects.add(products.get(i));
		}
		if (products!= null && products.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getProductById(@PathVariable(value = "id") String gradeId)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(gradeId).orElse(null);
        if (product == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", product);
    }
	
	@GetMapping("/structure_value_id/{structure_value_id}")
	public MyHttpResponseArray getByStructureValue(@PathVariable(value = "structure_value_id") String structureValueId) {
		List<Product> products = productRepository.findByStructurevalue(structureValueId);
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < products.size(); i++) {
			objects.add(products.get(i));
		}
		if (products!= null && products.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@PostMapping("/")
    public MyHttpResponse createProduct(@Validated @RequestBody Product product) {
    	
    	Product product1 = productRepository.save(product);
    	
    	if (product1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , product1);
    }
	
	@PutMapping("/")
	public MyHttpResponse update(@Validated @RequestBody Product product) {
		Product product1 = productRepository.findById(product.getId().trim()).orElse(null);
		if (product1 == null) {
			return new MyHttpResponse(404, "Không tìm thấy sản phẩm", null);
		}
		Product product2 = productRepository.save(product);
		if (product2 == null) {
    		return new MyHttpResponse(404, "Cập nhật không thành công", null);
		}
		
        return new MyHttpResponse(200, "Cập nhật thành công" , product1);
	}
}
