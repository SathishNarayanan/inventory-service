package com.cts.microcredential.inventory.service;

import com.cts.microcredential.inventory.model.SKU;
import com.cts.microcredential.inventory.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SqsSender sqsSender;

    public SKU getProduct(String sku) {
        System.out.println("SKU : " + sku);
        return skuRepository.findBySku(sku).get();
    }

    public SKU addProduct(SKU product) {
        product.setSku("urn:microcred:sku:" + UUID.randomUUID().toString());
        product.setCreate_user("admin");
        product.setCreate_tsamp(Instant.now().toString());

        skuRepository.save(product);
        sqsSender.send(product);

        return product;
    }

    public SKU updateInventory(SKU sku) {
        Optional<SKU> optnlSkuData = skuRepository.findById(sku.getSku());
        SKU skuData = null;
        if (optnlSkuData.isPresent()) {
            skuData = optnlSkuData.get();
            skuData.setQuantity(sku.getQuantity());
            skuData.setModify_user("admin");
            skuData.setModify_tstamp(Instant.now().toString());

            skuRepository.save(skuData);
            sqsSender.send(skuData);
        }
        return skuData;
    }
}
