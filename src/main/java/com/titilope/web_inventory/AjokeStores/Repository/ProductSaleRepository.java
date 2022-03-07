package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.ProductSale;
import com.titilope.web_inventory.AjokeStores.Rest.ProductSaleCount;
import groovy.lang.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Integer> {
    @Query(nativeQuery = true, value = "SELECT products.name as product_name, count(product_sales.id) AS sale_count FROM product_sales " +
            " inner join products where products.id = product_sales.product_id group by product_sales.product_id")
    List<Object[]> findProductSaleGroupByProduct();

}
