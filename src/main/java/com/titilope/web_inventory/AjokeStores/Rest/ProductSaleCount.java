package com.titilope.web_inventory.AjokeStores.Rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleCount {

    private String productName;

    private Long salesCount;


}
