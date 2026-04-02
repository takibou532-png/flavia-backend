package com.menu.demo.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menu.demo.Dtos.OrderItemRequest;
import com.menu.demo.Dtos.OrderItemResponseDto;
import com.menu.demo.Dtos.OrderRequestDto;
import com.menu.demo.Dtos.OrderResponseDto;
import com.menu.demo.Enums.OrderStatus;
import com.menu.demo.Exceptions.ResourceNotFoundException;

import com.menu.demo.Models.Item;
import com.menu.demo.Models.Order;
import com.menu.demo.Models.OrderItem;
import com.menu.demo.Repositories.ItemRepository;
import com.menu.demo.Repositories.OrderRepository;
import java.time.*;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final OrderRepository orderRepository;

	private final ItemRepository itemRepository;
	
	
	public List<OrderResponseDto> getAllOrders(){
		
		return orderRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
		
	}
	
	 public OrderResponseDto createOrder(OrderRequestDto request) {

	     Order order =Order.builder()
	    		 .address(request.getAddress())
	    		 .fullName(request.getFullName())
	    		 .phoneNumber(request.getPhoneNumber())
	    		 .orderStatus(OrderStatus.PENDING)
	    		 .build();

	        List<OrderItem> orderItems = new ArrayList<>();
	       BigDecimal totalPrice=BigDecimal.ZERO;
	        for (OrderItemRequest reqItem : request.getItems()) {

	            Item item = itemRepository.findById(reqItem.getItemId())
	                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id :"+reqItem.getItemId()));
	            totalPrice = totalPrice.add(
	            	    item.getPrice().multiply(BigDecimal.valueOf(reqItem.getQuentity())));
	            OrderItem orderItem = new OrderItem();
	            orderItem.setOrder(order);
	            orderItem.setItem(item);
	            orderItem.setQuantity(reqItem.getQuentity());
         
	            orderItems.add(orderItem);
	        }
            order.setTotalPrice(totalPrice); 
	        order.setOrderItems(orderItems);
	        orderRepository.save(order);

	        return toDto(order);
	    }
	 
	 public ResponseEntity<Void> setOrderDelivered(Long orderId) {
		 Order order =orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not found with Id : "+orderId));
         
		 if(order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
			  throw new RuntimeException("Order already delivered");
		 }
		 order.setOrderStatus(OrderStatus.DELIVERED);
		 
		
		  for (OrderItem orderItem : order.getOrderItems()) {
	            Item item = orderItem.getItem();

	            int qty = orderItem.getQuantity();
	            item.setNumberOfSales(item.getNumberOfSales() + qty);
	            

	            itemRepository.save(item);
	        }
		  orderRepository.save(order);
		return  ResponseEntity.ok(null);
		 
		 
		 
	 }
	 public ResponseEntity<Void> cancelOrder(Long orderId) {
	 Order order =orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not found with Id : "+orderId));
         
		 if(order.getOrderStatus().equals(OrderStatus.CANCELED)) {
			  throw new RuntimeException("Order already canceled");
		 }
		 order.setOrderStatus(OrderStatus.CANCELED);
		 orderRepository.save(order);
		 return ResponseEntity.ok(null);
	 }
	 
	 
	    private  OrderResponseDto toDto(Order order) {
	        if ( order == null ) {
	            return null;
	        }

	        OrderResponseDto orderResponseDto = new OrderResponseDto();

	        orderResponseDto.setAddress( order.getAddress() );
	        orderResponseDto.setCreatedAt( order.getCreatedAt() );
	        orderResponseDto.setFullName( order.getFullName() );
	        orderResponseDto.setId( order.getId() );
	        orderResponseDto.setOrderStatus( order.getOrderStatus() );
	        orderResponseDto.setPhoneNumber( order.getPhoneNumber() );
	        orderResponseDto.setTotalPrice( order.getTotalPrice() );
	        List<OrderItemResponseDto> itemDtos = order.getOrderItems()
	                .stream()
	                .map(orderItem -> {
	                    OrderItemResponseDto itemDto = new OrderItemResponseDto();

	                    itemDto.setItemId(orderItem.getItem().getId());
	                    itemDto.setName(orderItem.getItem().getName());
	                    itemDto.setPrice(orderItem.getItem().getPrice());
	                    itemDto.setQuantity(orderItem.getQuantity());

	                    return itemDto;
	                })
	                .toList();

	           orderResponseDto.setItems(itemDtos);

	        return orderResponseDto;
	    }
	    
	    
	    public BigDecimal getDailyProfit() {
	        LocalDateTime start = LocalDate.now().atStartOfDay();
	        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

	        return orderRepository.getProfitBetween(start, end);
	    }
	    public BigDecimal getWeeklyProfit() {
	        LocalDate today = LocalDate.now();
	        LocalDate startOfWeek = today.with(DayOfWeek.SUNDAY);

	        LocalDateTime start = startOfWeek.atStartOfDay();
	        LocalDateTime end = LocalDateTime.now();

	        return orderRepository.getProfitBetween(start, end);
	    }
	    public BigDecimal getMonthlyProfit() {
	        LocalDate today = LocalDate.now();
	        LocalDate firstDay = today.withDayOfMonth(1);

	        LocalDateTime start = firstDay.atStartOfDay();
	        LocalDateTime end = LocalDateTime.now();

	        return orderRepository.getProfitBetween(start, end);
	    }
	    public BigDecimal getAllTimeProfit() {
	        return orderRepository.getTotalProfit();
	    }
	
	
	 
	
		
	
		
	}
	


