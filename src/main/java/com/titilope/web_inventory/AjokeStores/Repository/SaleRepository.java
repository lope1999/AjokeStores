package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query( value = "select * from  sale where DATE(date_sold) = :date", nativeQuery = true)
    List<Sale> findByDateSold(@Param("date") String date);


    List<Sale> findByDateSoldBetween(Date startDate, Date endDate);

}
