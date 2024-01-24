package com.shubham.dev.InventoryService;

import com.shubham.dev.InventoryService.model.Inventory;
import com.shubham.dev.InventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//
//			try {
//				Inventory inventory = new Inventory();
//				inventory.setSkuCode("iphone_13");
//				inventory.setQuantity(100);
//
//				Inventory inventory1 = new Inventory();
//				inventory1.setSkuCode("iphone_13_red");
//				inventory1.setQuantity(0);
//
//				inventoryRepository.save(inventory);
//				inventoryRepository.save(inventory1);
//				// Your existing code here
//			} catch (Exception e) {
//				e.printStackTrace(); // Log the exception
//			}
//
//		};
	}

