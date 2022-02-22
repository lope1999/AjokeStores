package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAllByOrderByDescriptionAsc();
}
