package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
import org.trananh.shoppingappbackend.model.PromotionHeader;
import org.trananh.shoppingappbackend.model.StructureValue;
import org.trananh.shoppingappbackend.model.UnitOfMeasure;
import org.trananh.shoppingappbackend.model.User;
import org.trananh.shoppingappbackend.repository.ProductRepository;
import org.trananh.shoppingappbackend.repository.UnitOfMeasureRepository;
import org.trananh.shoppingappbackend.ultilities.Constants;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;
import org.trananh.shoppingappbackend.ultilities.ResponseMap;
import org.trananh.shoppingappbackend.ultilities.ResponseMapArray;

import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired(required = true)
	private ProductRepository productRepository;
	
	@Autowired(required = true)
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	@GetMapping("/all")
	public MyHttpResponseArray getAll() {
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
	
	@GetMapping("/")
	public Map<String, Object> getAllproduct() {
		
		List<Product> products = productRepository.findAll();
		
		List<Map<String, Object>> lstUnit = unitOfMeasureRepository.findAllProduct();
		
		
		if (products!= null) {
			
			List<Map<String , Object>> lstMap = new ArrayList<Map<String,Object>>();
			
			for(Map<String, Object> mapUnit : lstUnit) {
				
				for(Product pro : products) {
					if (mapUnit.get("product_id").toString().trim().equals(pro.getId().trim())) {
						Map<String, Object> m = new HashMap<String, Object>();
						Map<String, Object> mCategory = new HashMap<String, Object>();
						
						m.put("id", pro.getId().toString().trim());
						m.put("name", pro.getName().toString().trim());
						m.put("description", pro.getDescription().toString().trim());
						m.put("imageUrl", pro.getImageUrl().toString().trim());
						m.put("category", pro.getCategory().getValue().toString().trim());
						m.put("baseUnitOfMeasure", mapUnit.get("base_unit_of_measure_id"));
						
						lstMap.add(m);
					}
				}
				
			}
			
			return new ResponseMapArray(0, "Successfully", lstMap);
			
		}
		
		return new ResponseMap(1, "Failed", null);
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
    public Map<String, Object> createProduct(@Validated @RequestBody Map<String, Object> map) {
    	
    	Product product = new Product();
    	product.setId(map.get("id").toString().trim());
    	product.setName(map.get("name").toString().trim());
    	product.setDescription(map.get("description").toString().trim());
    	product.setImageUrl(map.get("imageUrl").toString().trim());
    	product.setCreateUser(new User(map.get("createUser").toString().trim()));
    	product.setCategory(new StructureValue(map.get("category").toString().trim()));
    	String json = Constants.gson.toJson(map.get("unitOfMeasure"));
    	List<UnitOfMeasure> unitOfMeasures = Constants.gson.fromJson(json, new TypeToken<List<UnitOfMeasure>>(){}.getType());
    	
    	Product product1 = productRepository.findById(product.getId().trim()).orElse(null);
    	if (product1 != null) {
			return new ResponseMap(1, "Id is already in use", null);
		}
    	
    	Product product2 = productRepository.save(product);
    	if (product2 == null) {
    		return new ResponseMap(2, "Failed", null);
		}
    	
    	for(UnitOfMeasure u : unitOfMeasures) {
    		unitOfMeasureRepository.save(u);
    	}
    	
    	return new ResponseMap(0, "Successfully", null);
    	
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
