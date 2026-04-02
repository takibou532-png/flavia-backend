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

import com.menu.demo.Dtos.CategoryRequestDto;
import com.menu.demo.Dtos.CategoryResponseDto;
import com.menu.demo.Services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {
	private final CategoryService categoryService; 
	
	
	@GetMapping
	public List<CategoryResponseDto> getAllCategories(){
		return categoryService.getAllCategories();
	}
	@GetMapping("/{id}")
	public CategoryResponseDto getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
	
	@PostMapping
	public ResponseEntity<CategoryResponseDto> createcategory(@RequestBody CategoryRequestDto dto){
		return categoryService.createCategory(dto);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponseDto> createcategory(@PathVariable Long id,@RequestBody CategoryRequestDto dto){
		return categoryService.updateCategory(id, dto);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
		return categoryService.deleteCategory(id);
	}

}
