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
import org.trananh.shoppingappbackend.model.HierarchyStructure;
import org.trananh.shoppingappbackend.repository.HierarchyStructureRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/hierarchy_structure")
public class HierarchyStructureController {
	@Autowired(required = true)
	private HierarchyStructureRepository hierarchyStructureRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAll() {
		List<HierarchyStructure> hierarchyStructures = hierarchyStructureRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < hierarchyStructures.size(); i++) {
			objects.add(hierarchyStructures.get(i));
		}
		if (hierarchyStructures!= null && hierarchyStructures.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getById(@PathVariable(value = "id") int id)
        throws ResourceNotFoundException {
        HierarchyStructure hierarchyStructure = hierarchyStructureRepository.findById(id).orElse(null);
        if (hierarchyStructure == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", hierarchyStructure);
    }
	
	@PostMapping("/")
    public MyHttpResponse create(@Validated @RequestBody HierarchyStructure hierarchyStructure) {
    	
    	HierarchyStructure hierarchyStructure1 = hierarchyStructureRepository.save(hierarchyStructure);
    	
    	if (hierarchyStructure1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , hierarchyStructure1);
    }
}
