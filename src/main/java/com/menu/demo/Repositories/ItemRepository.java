package com.menu.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.demo.Models.Category;
import com.menu.demo.Models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	
 List<Item> findByCategory(Category category);
}
