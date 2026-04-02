package com.menu.demo.Controllers;

import java.util.List;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.CategoryResponseDto;
import com.menu.demo.Services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
	
	private final CategoryService categoryService; 
	
	
	@GetMapping("/categories")
	public List<CategoryResponseDto> getAllCategories(){
		return categoryService.getAllCategories();
	}
	@GetMapping("/{id}")
	public CategoryResponseDto getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
	

	

}
