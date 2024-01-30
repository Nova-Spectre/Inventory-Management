package com.shubham.dev.InventoryService.service;

import com.shubham.dev.InventoryService.dto.InventoryResponse;
import com.shubham.dev.InventoryService.repository.InventoryRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly=true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
            InventoryResponse.builder().skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity()>0)
                    .build()
        ).toList();

    }
}
