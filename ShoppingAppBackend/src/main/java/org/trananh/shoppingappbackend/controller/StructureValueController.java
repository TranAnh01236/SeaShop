package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh.shoppingappbackend.exception.ResourceNotFoundException;
import org.trananh.shoppingappbackend.model.StructureValue;
import org.trananh.shoppingappbackend.repository.StructureValueRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/structure_value")
public class StructureValueController {
	@Autowired(required = true)
	private StructureValueRepository structureValueRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<StructureValue> structureValues = structureValueRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < structureValues.size(); i++) {
			objects.add(structureValues.get(i));
		}
		if (structureValues!= null && structureValues.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") String id)
        throws ResourceNotFoundException {
        StructureValue structureValue = structureValueRepository.findById(id).orElse(null);
        if (structureValue == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", structureValue);
    }
	
	@GetMapping("/type/{type}")
    public MyHttpResponseArray getByType(@PathVariable(value = "type") int type){
        List<StructureValue> structureValues = structureValueRepository.findByType(type);
        
        ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < structureValues.size(); i++) {
			objects.add(structureValues.get(i));
		}
		
		if (structureValues!= null && structureValues.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/parent_id/{parent_id}")
    public MyHttpResponseArray getByParentId(@PathVariable(value = "parent_id") String parentId){
        List<StructureValue> structureValues = structureValueRepository.findByparentId(parentId);
        
        ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < structureValues.size(); i++) {
			objects.add(structureValues.get(i));
		}
		
		if (structureValues!= null && structureValues.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/type/{type}/level/{level}")
	public MyHttpResponseArray getByTypeAndLevel(@PathVariable(value = "type") int type, @PathVariable(value = "level") int level) {
		List<StructureValue> structureValues = structureValueRepository.findByTypeAndLevel(type, level);
        
        ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < structureValues.size(); i++) {
			objects.add(structureValues.get(i));
		}
		
		if (structureValues!= null && structureValues.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody StructureValue structureValue) {
    	
    	StructureValue structureValue1 = structureValueRepository.save(structureValue);
    	
    	if (structureValue1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , structureValue1);
    }
	@DeleteMapping("/{id}")
	public MyHttpResponse delete(@PathVariable(value = "id") String id) {
		StructureValue structureValue = structureValueRepository.findById(id).orElse(null);
		if (structureValue == null) {
			return new MyHttpResponse(404, "Không tìm thấy", null);
		}else {
			structureValueRepository.delete(structureValue);
			return new MyHttpResponse(200, "Xóa thành công", null);
		}
	}
}
