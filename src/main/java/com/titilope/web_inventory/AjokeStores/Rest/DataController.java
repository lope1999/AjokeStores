package com.titilope.web_inventory.AjokeStores.Rest;

import com.titilope.web_inventory.AjokeStores.Repository.ProductSaleRepository;
import groovy.lang.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
public class DataController {

    @Autowired
    ProductSaleRepository productSaleRepository;

   @GetMapping("/data/find-productSales-by-product")
    public List<?> findProductSalesByProduct()
    {
        List<Object[]> queryResult = productSaleRepository.findProductSaleGroupByProduct();

        List<GraphData> values = new ArrayList<>();
        queryResult.forEach(tuple -> {
            String label = (String) tuple[0];
            Long count = ((BigInteger)tuple[1]).longValue();
            values.add(new GraphData(label, count));
        });

        return values;
    }


}
