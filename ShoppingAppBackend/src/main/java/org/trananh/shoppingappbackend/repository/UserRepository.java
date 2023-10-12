package org.trananh.shoppingappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh.shoppingappbackend.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	@Query(value = "select top 1 * from users where login_name = ?1", nativeQuery=true)
	User findByLoginName(String loginName);
	
	@Query(value = "select top 1 * from users where phone_number = ?1", nativeQuery=true)
	User findByPhoneNumber(String phoneNumber);
}
