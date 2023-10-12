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
import org.trananh.shoppingappbackend.model.PromotionHeader;
import org.trananh.shoppingappbackend.repository.PromotionHeaderRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/promotion_header")
public class PromotionHeaderController {
	@Autowired(required = true)
	private PromotionHeaderRepository promotionHeaderRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<PromotionHeader> promotionHeaders = promotionHeaderRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < promotionHeaders.size(); i++) {
			objects.add(promotionHeaders.get(i));
		}
		if (promotionHeaders!= null && promotionHeaders.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String id)
        throws ResourceNotFoundException {
        PromotionHeader promotionHeader = promotionHeaderRepository.findById(id).orElse(null);
        if (promotionHeader == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", promotionHeader);
    }
	
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody PromotionHeader promotionHeader) {
    	
    	PromotionHeader promotionHeader1 = promotionHeaderRepository.save(promotionHeader);
    	
    	if (promotionHeader1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , promotionHeader1);
    }
}
