package com.menu.demo.Controllers;

import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.ItemResponseDto;

import com.menu.demo.Services.ItemService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
	private final ItemService itemService; 
	
	
	@GetMapping("/category/{categoryName}")
	public List<ItemResponseDto> getItemByCategory(@PathVariable String categoryName){
		return itemService.getItemByCategory(categoryName);
	}
	
	
	
	@GetMapping
	public List<ItemResponseDto> getAllItems(){
		return itemService.getAllItems();
	}
	@GetMapping("/{id}")
	public ItemResponseDto getItemId(@PathVariable Long id) {
		return itemService.getItemById(id);
	}
	

}
