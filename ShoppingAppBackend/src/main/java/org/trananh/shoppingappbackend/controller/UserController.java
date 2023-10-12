package org.trananh.shoppingappbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh.shoppingappbackend.exception.ResourceNotFoundException;
import org.trananh.shoppingappbackend.model.StructureValue;
import org.trananh.shoppingappbackend.model.User;
import org.trananh.shoppingappbackend.repository.StructureValueRepository;
import org.trananh.shoppingappbackend.repository.UserRepository;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponse;
import org.trananh.shoppingappbackend.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired(required = true)
	private UserRepository userRepository;
	
	@GetMapping("/")
	public MyHttpResponseArray getAllUser() {
		List<User> users = userRepository.findAll();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < users.size(); i++) {
			objects.add(users.get(i));
		}
		if (users!= null && users.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
		return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/{id}")
    public MyHttpResponse getUserById(@PathVariable(value = "id") String userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", user);
    }
	
	@GetMapping("/login_name/{login_name}")
	public MyHttpResponse getUserByLoginName(@PathVariable(value = "login_name") String loginName)
        throws ResourceNotFoundException {
        User user = userRepository.findByLoginName(loginName);
        if (user == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", user);
    }
	
	@GetMapping("/phoneNumber/{phone_number}")
	public MyHttpResponse getUserByPhoneNumber(@PathVariable(value = "phone_number") String phoneNumber)
        throws ResourceNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", user);
	}
	
	@PostMapping("/")
    public MyHttpResponse createUser(@Validated @RequestBody User user) {
    	
    	User user1 = userRepository.save(user);
    	
    	if (user1 == null) {
    		return new MyHttpResponse(404, "Thêm không thành công", null);
		}
		
        return new MyHttpResponse(200, "Thêm thành công" , user1);
    }
	
}
