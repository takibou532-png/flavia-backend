package com.menu.demo.Controllers;



import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.ItemRequestDto;
import com.menu.demo.Dtos.ItemResponseDto;
import com.menu.demo.Services.ItemService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/items")
public class AdminItemController {
	private final ItemService itemService; 
	
	
	@GetMapping
	public List<ItemResponseDto> getAllItems(){
		return itemService.getAllItems();
	}
	@GetMapping("/{id}")
	public ItemResponseDto getCategoryById(@PathVariable Long id) {
		return itemService.getItemById(id);
	}
	
	@PostMapping
	public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto dto){
		return itemService.createItem(dto);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id,@RequestBody ItemRequestDto dto){
		return itemService.updateItem(id, dto);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id){
		return itemService.deleteItem(id);
	}
}
