package com.menu.demo.Services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.menu.demo.Dtos.ItemRequestDto;
import com.menu.demo.Dtos.ItemResponseDto;
import com.menu.demo.Exceptions.ResourceNotFoundException;

import com.menu.demo.Models.Category;
import com.menu.demo.Models.Item;
import com.menu.demo.Repositories.CategoryRepository;
import com.menu.demo.Repositories.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;

	private final CategoryRepository categoryRepository;
	public List<ItemResponseDto> getItemByCategory( String categoryName){
		Category category=categoryRepository.findByName(categoryName).orElseThrow(()->new ResourceNotFoundException("category not found with name :"+categoryName));
	    return itemRepository.findByCategory(category).stream().map(this::toDto).toList();
	}
	
	
	public List<ItemResponseDto> getAllItems(){
		return itemRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
		
	}
	public ItemResponseDto getItemById(Long id){
		return toDto(itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("item not found with id "+id)));
		
	}
	public ResponseEntity<ItemResponseDto> createItem(ItemRequestDto dto){
		Category category=categoryRepository.findByName(dto.getCategoryName()).orElseThrow(()->new ResourceNotFoundException("category not found with name :"+dto.getCategoryName()));
		
		 Item item =Item.builder().Name(dto.getName()).description(dto.getDescription()).isAvailable(true).price(dto.getPrice())
				 .category(category).imgUrl(dto.getImgUrl()).build();
		 itemRepository.save(item);
		 return ResponseEntity.status(HttpStatus.CREATED).body(toDto(item));
		
		
	}
	
	public ResponseEntity<ItemResponseDto> updateItem(Long id,ItemRequestDto dto){
		Item item =itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("item not found with id :"+ id));
		Category category=categoryRepository.findByName(dto.getCategoryName()).orElseThrow(()->new ResourceNotFoundException("category not found with name :"+dto.getCategoryName()));
	    item.setName(dto.getName());
	    item.setDescription(dto.getDescription());
	    item.setImgUrl(dto.getImgUrl());
	    item.setAvailable(dto.isAvailable());
	    item.setCategory(category);
	    item.setPrice(dto.getPrice());
	   
		itemRepository.save(item);
		return ResponseEntity.ok(toDto(item));
		
	}
	public ResponseEntity<Void> deleteItem(Long id){
		Item item =itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("item not found with id :"+ id));
		itemRepository.delete(item);
		return ResponseEntity.ok(null);
	}
	
    private ItemResponseDto toDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemResponseDto itemResponseDto = new ItemResponseDto();

        itemResponseDto.setAvailable( item.isAvailable() );
        itemResponseDto.setCreatedAt( item.getCreatedAt() );
        itemResponseDto.setDescription( item.getDescription() );
        itemResponseDto.setId( item.getId() );
        itemResponseDto.setImgUrl( item.getImgUrl() );
        itemResponseDto.setName( item.getName() );
        itemResponseDto.setPrice( item.getPrice() );
        itemResponseDto.setUpdatedAt( item.getUpdatedAt() );
        itemResponseDto.setCategoryName(item.getCategory().getName());

        return itemResponseDto;
    }
}
	


