package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByDateSold(Date date);
    List<Sale> findByDateSoldBetween(Date startDate, Date endDate);
}
