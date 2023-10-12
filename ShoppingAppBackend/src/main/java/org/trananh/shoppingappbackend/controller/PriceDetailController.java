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
import org.trananh.shoppingappbackend.model.PriceDetail;
import org.trananh.shoppingappbackend.repository.PriceDetailRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/price_detail")
public class PriceDetailController {
	@Autowired(required = true)
	private PriceDetailRepository priceDetailRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<PriceDetail> priceHeaders = priceDetailRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < priceHeaders.size(); i++) {
			objects.add(priceHeaders.get(i));
		}
		if (priceHeaders!= null && priceHeaders.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String id)
        throws ResourceNotFoundException {
        PriceDetail priceDetail = priceDetailRepository.findById(id).orElse(null);
        if (priceDetail == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", priceDetail);
    }
	
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody PriceDetail priceDetail) {
    	
    	PriceDetail priceDetail1 = priceDetailRepository.save(priceDetail);
    	
    	if (priceDetail1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , priceDetail1);
    }
}
