package org.trananh.shoppingappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh.shoppingappbackend.model.UnitOfMeasure;
import org.trananh.shoppingappbackend.model.UnitOfMeasurePK;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, UnitOfMeasurePK>{
	@Query(value = "select top 1 * from unit_of_measures where product_id = ?1 and quantity = 1", nativeQuery=true)
	UnitOfMeasure findLowestByProductId(String productId);
	
	@Query(value = "select * from unit_of_measures where product_id = ?1", nativeQuery=true)
	List<UnitOfMeasure> findByProductId(String productId);
}
