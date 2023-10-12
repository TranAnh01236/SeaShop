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
import org.trananh.shoppingappbackend.model.PromotionLine;
import org.trananh.shoppingappbackend.repository.PromotionLineRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/promotion_line")
public class PromotionLineController {
	@Autowired(required = true)
	private PromotionLineRepository promotionLineRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<PromotionLine> promotionLines = promotionLineRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < promotionLines.size(); i++) {
			objects.add(promotionLines.get(i));
		}
		if (promotionLines!= null && promotionLines.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String id)
        throws ResourceNotFoundException {
        PromotionLine promotionLine = promotionLineRepository.findById(id).orElse(null);
        if (promotionLine == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", promotionLine);
    }
	
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody PromotionLine promotionLine) {
    	
    	PromotionLine promotionLine1 = promotionLineRepository.save(promotionLine);
    	
    	if (promotionLine1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , promotionLine1);
    }
}
