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
import org.trananh.shoppingappbackend.exception.ResourceNotFoundException;
import org.trananh.shoppingappbackend.model.BaseUnitOfMeasure;
import org.trananh.shoppingappbackend.repository.BaseUnitOfMeasureRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/base_unit_of_measure")
public class BaseUnitOfMeasureController {
	@Autowired(required = true)
	private BaseUnitOfMeasureRepository baseUnitOfMeasureRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<BaseUnitOfMeasure> donvicoban = baseUnitOfMeasureRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < donvicoban.size(); i++) {
			objects.add(donvicoban.get(i));
		}
		if (donvicoban!= null && donvicoban.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String gradeId)
        throws ResourceNotFoundException {
        BaseUnitOfMeasure donvicoban = baseUnitOfMeasureRepository.findById(gradeId).orElse(null);
        if (donvicoban == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", donvicoban);
    }
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody BaseUnitOfMeasure donvicoban) {
    	
    	BaseUnitOfMeasure donvicoban1 = baseUnitOfMeasureRepository.save(donvicoban);
    	
    	if (donvicoban1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , donvicoban1);
    }
}
