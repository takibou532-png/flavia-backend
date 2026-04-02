package com.menu.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menu.demo.Dtos.CategoryRequestDto;
import com.menu.demo.Dtos.CategoryResponseDto;

import com.menu.demo.Exceptions.ResourceNotFoundException;
import com.menu.demo.Mappers.CategoryMapper;

import com.menu.demo.Models.Category;

import com.menu.demo.Repositories.CategoryRepository;



import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CategoryService {
	
	

	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;
	
	
	public List<CategoryResponseDto> getAllCategories(){
		return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
		
	}
	public CategoryResponseDto getCategoryById(Long id){
		return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found with id "+id)));
		
	}
	public ResponseEntity<CategoryResponseDto> createCategory(CategoryRequestDto dto){
		
		
		 Category category =Category.builder().name(dto.getName()).description(dto.getDescription()).imgUrl(dto.getImgUrl())
				 .build();
		 categoryRepository.save(category);
		 return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toDto(category));
		
		
	}
	
	public ResponseEntity<CategoryResponseDto> updateCategory(Long id,CategoryRequestDto dto){
		Category item =categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found with id :"+ id));

	    item.setName(dto.getName());
	    item.setDescription(dto.getDescription());
	    item.setImgUrl(dto.getImgUrl());
	   
	   
		categoryRepository.save(item);
		return ResponseEntity.ok(categoryMapper.toDto(item));
		
	}
	public ResponseEntity<Void> deleteCategory(Long id){
		Category category =categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found with id :"+ id));
		categoryRepository.delete(category);
		return ResponseEntity.ok(null);
	}
}
