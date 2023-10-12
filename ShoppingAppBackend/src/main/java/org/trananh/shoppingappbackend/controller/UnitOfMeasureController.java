package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh.shoppingappbackend.model.UnitOfMeasure;
import org.trananh.shoppingappbackend.repository.UnitOfMeasureRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/unit_of_measure")
public class UnitOfMeasureController {
	@Autowired(required = true)
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<UnitOfMeasure> donviquydoi = unitOfMeasureRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < donviquydoi.size(); i++) {
			objects.add(donviquydoi.get(i));
		}
		if (donviquydoi!= null && donviquydoi.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	
	@GetMapping("/lowest/product_id/{productId}")
	public MyHttpResponse getLowestUnitByProductId(@PathVariable("productId") String productId) {
		UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findLowestByProductId(productId);
		if (unitOfMeasure == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", unitOfMeasure);
	}
	
	@GetMapping("/product_id/{productId}")
	public MyHttpResponseArray getByProductId(@PathVariable("productId") String productId) {
		List<UnitOfMeasure> donviquydoi = unitOfMeasureRepository.findByProductId(productId);
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < donviquydoi.size(); i++) {
			objects.add(donviquydoi.get(i));
		}
		if (donviquydoi!= null && donviquydoi.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody UnitOfMeasure donviquydoi) {
		
    	UnitOfMeasure donviquydoi1 = unitOfMeasureRepository.save(donviquydoi);
    	
    	if (donviquydoi1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , donviquydoi1);
        
//        return null;
    }
}
