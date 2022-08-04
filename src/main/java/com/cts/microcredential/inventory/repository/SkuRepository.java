package com.cts.microcredential.inventory.repository;

import com.cts.microcredential.inventory.model.SKU;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkuRepository extends CrudRepository<SKU, String> {
    public Optional<SKU> findBySku(String skuId);
}
