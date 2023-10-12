package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh.shoppingappbackend.exception.ResourceNotFoundException;
import org.trananh.shoppingappbackend.model.HierarchyStructure;
import org.trananh.shoppingappbackend.model.StructureValue;
import org.trananh.shoppingappbackend.repository.StructureValueRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;
import org.trananh.shoppingappbackend.ultilities.ResponseMap;
import org.trananh.shoppingappbackend.ultilities.ResponseMapArray;

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
	
	@GetMapping("/address")
	public Map<String, Object> getAllAddress(){
		
		List<StructureValue> lst1 = structureValueRepository.findByTypeAndLevel(1, 1);
		List<StructureValue> lst2 = structureValueRepository.findByTypeAndLevel(1, 2);
		List<StructureValue> lst3 = structureValueRepository.findByTypeAndLevel(1, 3);
		
		if (lst1 != null && lst2 != null && lst3 != null) {
			ArrayList<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
			for(StructureValue t3 : lst3) {
				Map<String, Object> m = new HashMap<String, Object>();
				
				StructureValue t2 = new StructureValue();
				for(StructureValue strv : lst2) {
					if (strv.getId().toString().trim().equals(t3.getParentId().toString().trim())) {
						t2 = strv;
					}
				}
				
				StructureValue t1 = new StructureValue();
				for(StructureValue strv : lst1) {
					if (strv.getId().toString().trim().equals(t2.getParentId().toString().trim())) {
						t1 = strv;
					}
				}
				
				m.put("id", t3.getId().trim());
				m.put("value1", t1.getValue());
				m.put("value2", t2.getValue());
				m.put("value3", t3.getValue());
				map.add(m);
			}
			return new ResponseMapArray(0, "Successfully", map);
		}
		
		return new ResponseMap(1, "Failed", null);
		
	}
	
	@GetMapping("/category/level/{level}")
	public Map<String, Object> getProductTypeByLevel(@PathVariable(value = "level") int level){
		
		List<StructureValue> lstStructureValues = structureValueRepository.findByTypeAndLevel(2, level);
		
		if (lstStructureValues != null) {
			List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
			for(StructureValue str : lstStructureValues) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", str.getId().trim());
				m.put("value", str.getValue().trim());
				m.put("imageUrl", str.getImageUrl().trim());
				m.put("description", str.getDescription());
				
				map.add(m);
			}
			return new ResponseMapArray(0, "Successfully", map);
		}
		
		return new ResponseMap(1, "Failed", null);
		
	}
	
	@GetMapping("/categpry/parent_id/{parent_id}")
	public Map<String, Object> getProductTypeByParentId(@PathVariable(value = "parent_id") String parentId){
		List<StructureValue> lstStructureValues = structureValueRepository.findByparentId(parentId.trim());
		
		if (lstStructureValues != null) {
			List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
			for(StructureValue str : lstStructureValues) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", str.getId().trim());
				m.put("value", str.getValue().trim());
				m.put("imageUrl", str.getImageUrl().trim());
				m.put("description", str.getDescription());
				
				map.add(m);
			}
			return new ResponseMapArray(0, "Successfully", map);
		}
		
		return new ResponseMap(1, "Failed", null);
		
	}
	
	@PostMapping("/category")
    public Map<String, Object> createProductType(@Validated @RequestBody Map<String, Object> map) {
		
		StructureValue structureValue = new StructureValue();
		structureValue.setId(map.get("id").toString().trim());
		structureValue.setValue(map.get("value").toString().trim());
		structureValue.setImageUrl(map.get("imageUrl").toString().trim());
		structureValue.setDescription(map.get("description").toString().trim());
		structureValue.setLevel(Integer.parseInt(map.get("level").toString().trim()));
		structureValue.setParentId(map.get("parentId").toString());
		structureValue.setType(new HierarchyStructure(2));
		
		StructureValue structureValue1 = structureValueRepository.findById(structureValue.getId().trim()).orElse(null);
		if (structureValue1 != null) {
			return new ResponseMap(1, "Id is already in use", null);
		}
		
		StructureValue structureValue2 = structureValueRepository.save(structureValue);
		if (structureValue2 == null) {
			return new ResponseMap(2, "Failed", null);
		}
		
		map.put("type", structureValue.getType());
		
		return new ResponseMap(0, "Successfully", map);
		
	}
	
	@PutMapping("/category")
	public Map<String, Object> updateProductType(@Validated @RequestBody Map<String, Object> map) {
		StructureValue structureValue = new StructureValue();
		structureValue.setId(map.get("id").toString().trim());
		structureValue.setValue(map.get("value").toString().trim());
		structureValue.setImageUrl(map.get("imageUrl").toString().trim());
		structureValue.setDescription(map.get("description").toString().trim());
		structureValue.setLevel(Integer.parseInt(map.get("level").toString().trim()));
		structureValue.setParentId(map.get("parentId").toString());
		structureValue.setType(new HierarchyStructure(2));
		
		StructureValue structureValue2 = structureValueRepository.save(structureValue);
		if (structureValue2 == null) {
			return new ResponseMap(2, "Failed", null);
		}
		
		map.put("type", structureValue.getType());
		
		return new ResponseMap(0, "Successfully", map);
	}
		
	@DeleteMapping("/category/{id}")
	public Map<String, Object> deleteProducType(@PathVariable(value = "id") String id){
		StructureValue structureValue = structureValueRepository.findById(id).orElse(null);
		if (structureValue == null) {
			return new ResponseMap(1, "Product not avaible", null);
		}
		structureValueRepository.delete(structureValue);
		
		return new ResponseMap(0, "Delete successfully", null);
	}
	
}
