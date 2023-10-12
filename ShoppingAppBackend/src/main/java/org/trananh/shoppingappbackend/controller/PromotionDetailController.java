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
import org.trananh.shoppingappbackend.model.PromotionDetail;
import org.trananh.shoppingappbackend.repository.PromotionDetailRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/promotion_detail")
public class PromotionDetailController {
	@Autowired(required = true)
	private PromotionDetailRepository promotionDetailRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<PromotionDetail> promotionDetails = promotionDetailRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < promotionDetails.size(); i++) {
			objects.add(promotionDetails.get(i));
		}
		if (promotionDetails!= null && promotionDetails.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String id)
        throws ResourceNotFoundException {
        PromotionDetail promotionDetail = promotionDetailRepository.findById(id).orElse(null);
        if (promotionDetail == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", promotionDetail);
    }
	
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody PromotionDetail promotionDetail) {
    	
    	PromotionDetail promotionDetail1 = promotionDetailRepository.save(promotionDetail);
    	
    	if (promotionDetail1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , promotionDetail1);
    }
}
