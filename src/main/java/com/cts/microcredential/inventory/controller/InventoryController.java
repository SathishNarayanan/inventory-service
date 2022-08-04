package com.cts.microcredential.inventory.controller;

import com.cts.microcredential.inventory.model.SKU;
import com.cts.microcredential.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/product/{sku}")
    public ResponseEntity<SKU> getProduct(@PathVariable(name="sku", required = true) String sku) {
        return ResponseEntity.ok().body(inventoryService.getProduct(sku));
    }

    @PostMapping("/product")
    public ResponseEntity<SKU> addProduct(@RequestBody SKU SKU) {
        return ResponseEntity.ok().body(inventoryService.addProduct(SKU));
    }

    @PutMapping("/product/{sku}")
    public ResponseEntity<SKU> updateInventory(@RequestBody SKU SKU) {
        return ResponseEntity.ok().body(inventoryService.updateInventory(SKU));
    }

}
