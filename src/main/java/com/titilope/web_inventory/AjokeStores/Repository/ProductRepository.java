package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAllByOrderByDescriptionAsc();

    @Query("SELECT p FROM Product p where p.packagingUnit.stockQuantity < 10")
    public List<Product> findLowStockedProducts();

    public List<Product> findByNameContainsAllIgnoreCase(String Name);
}
