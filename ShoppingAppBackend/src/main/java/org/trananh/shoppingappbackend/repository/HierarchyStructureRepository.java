package org.trananh.shoppingappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trananh.shoppingappbackend.model.HierarchyStructure;

public interface HierarchyStructureRepository extends JpaRepository<HierarchyStructure, Integer>{
	
}
